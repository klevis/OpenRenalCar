package ramo.klevis.national.buisnesslogic;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import notifier.NotificationType;
import notifier.NotifierDialog;
import ramo.klevis.national.ibuisnesslogic.ILoginDao;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.User;

public class LoginDao implements ILoginDao {

	@Override
	public User login(String username, String pass) {

		EntityManager createEntityManager = EntityManagerQuery
				.getEntityManager();

		Query setParameter = createEntityManager
				.createQuery(
						"Select u from User u where u.username =:v1 and u.password =:v2 ")
				.setParameter("v1", username).setParameter("v2", pass);

		User singleResul = null;

		try {
			singleResul = (User) setParameter.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception

			NotifierDialog.notify("", e.getMessage(), NotificationType.ERROR);
			
			return null;
		}

		createEntityManager.close();
		return singleResul;

	}

	@Override
	public User existUser(String username, String password) {
		EntityManager em = EntityManagerQuery.getEntityManager();
		if (em.getTransaction().isActive() == false) {

			em.getTransaction().begin();
		}
		Query createQuery = em
				.createQuery("Select u from User u where u.username=:username and u.password=:password");
		createQuery.setParameter("username", username);
		createQuery.setParameter("password", password);
		User singleResult = null;
		try {
			singleResult = (User) createQuery.getSingleResult();
		} catch (NoResultException ex) {

			System.out.println("Nuk kemi nje user");
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {

			return null;
		}
		em.close();
		return singleResult;

	}

	private boolean isUsernameUnik(String username) {

		EntityManager em = EntityManagerQuery.getEntityManager();
		if (em.getTransaction().isActive() == false) {

			em.getTransaction().begin();
		}
		Query createQuery = em
				.createQuery("Select u from User u where u.username=:username");
		createQuery.setParameter("username", username);
		User singleResult = null;
		try {
			singleResult = (User) createQuery.getSingleResult();
		} catch (NoResultException ex) {

			return true;
		} catch (Exception ex) {
			em.close();
			return true;
		}
		em.close();
		return false;

	}

	@Override
	public User existUsername(String username) {
		EntityManager em = EntityManagerQuery.getEntityManager();
		if (em.getTransaction().isActive() == false) {

			em.getTransaction().begin();
		}
		Query createQuery = em
				.createQuery("Select u from User u where u.username=:username");
		createQuery.setParameter("username", username);
		User singleResult = null;
		try {
			singleResult = (User) createQuery.getSingleResult();
		} catch (NoResultException ex) {
			em.close();
			return null;
		} catch (Exception ex) {
			em.close();
			return null;
		}
		em.close();
		return singleResult;

	}

	@Override
	public User changePasswordUser(String username, String oldPassword,
			String password) {
		EntityManager em = EntityManagerQuery.getEntityManager();
		User existUsername = existUser(username, oldPassword);

		if (existUsername != null) {

			if (em.getTransaction().isActive() == false) {

				em.getTransaction().begin();
			}

			existUsername.setUsername(username);
			existUsername.setPassword(password);

			User merge = em.merge(existUsername);

			em.getTransaction().commit();

			em.close();
			return merge;
		} else {
			em.close();
			return null;
		}
	}

}
