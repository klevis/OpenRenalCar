package ramo.klevis.national.buisnesslogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ramo.klevis.national.ibuisnesslogic.ILoginDao;
import ramo.klevis.national.ibuisnesslogic.IUserDao;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.User;

public class UserDao implements IUserDao {

	ILoginDao loginDao = new LoginDao();

	@Override
	public List<User> getAllUsers() {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		Query createQuery = entityManager
				.createQuery("Select  new ramo.klevis.national.entity.User(count(u.id),u.username) from Rental u group by u.username");

		List<User> resultList = createQuery.getResultList();

		List<User> resultList2 = entityManager.createQuery(
				"Select u from User u").getResultList();
		for (User user : resultList2) {
			System.out.println("Perdoruesi "+user.getUsername());
			for (User userCount : resultList) {

				System.out.println("Persoruesesit me kontrata"+userCount.getUsername());
				if (user.getUsername().equalsIgnoreCase(userCount.getUsername())) {

					user.setNrOfRentals(userCount.getNrOfRentals());
				}
			}

		}

		return resultList2;
	}

	@Override
	public User modifyUser(User user) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();

		User merge = entityManager.merge(user);
		entityManager.getTransaction().commit();
		return merge;

	}

	@Override
	public User craeteUser(User user) {

		User existUser = loginDao.existUsername(user.getUsername());

		if (existUser == null) {

			EntityManager entityManager = EntityManagerQuery.getEntityManager();
			if (entityManager.getTransaction().isActive() == false)
				entityManager.getTransaction().begin();

			User merge = entityManager.merge(user);
			entityManager.getTransaction().commit();
			return merge;

		} else {
			return null;
		}

	}

	@Override
	public User searchUser(String username) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		User setParameter = null;
		try {
			setParameter = (User) entityManager
					.createQuery("Select u from User u where u.username=:v1")
					.setParameter("v1", username).getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception

			return null;
		}
		return setParameter;

	}

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		userDao.getAllUsers();
	}
}
