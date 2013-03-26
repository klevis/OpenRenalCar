package ramo.klevis.national.buisnesslogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import notifier.NotificationType;
import notifier.NotifierDialog;
import ramo.klevis.national.beans.PersonSearch;
import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.openrental.entity.Customer;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.utils.CustomerStatus;

public class CustomerDao implements ICustomerDao {

	public boolean controlCustomer(Customer customer) {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		Query createQuery = entityManager
				.createQuery("Select c from Customer c where (c.pafirstname=:nam and c.palastname=:surname and c.status=:statusi) or (c.drpasportid=:pasport and c.status=:statusi ) or (c.drlicnr=:patent and c.status=:statusi) ");

		createQuery.setParameter("nam", customer.getPafirstname());
		createQuery.setParameter("surname", customer.getPalastname());
		createQuery.setParameter("pasport", customer.getDrpasportid());
		createQuery.setParameter("statusi", CustomerStatus.NOT_OK);
		createQuery.setParameter("patent", customer.getDrlicnr());
		List<Customer> resultList = createQuery.getResultList();
		if (resultList != null) {
			if (resultList.isEmpty()) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}

	}

	@Override
	public List<Customer> searchCustomer(PersonSearch personSearch) {

		EntityManager createEntityManager = EntityManagerQuery
				.getEntityManager();

		String sql = "Select c from Customer c where ";
		boolean name = false;
		boolean surname = false;

		if (personSearch.getName() != null) {
			if (personSearch.getName().isEmpty() == false) {
				sql = sql + " c.pafirstname like :v1";
				name = true;
			}
		}

		if (personSearch.getSurname() != null) {

			if (personSearch.getSurname().isEmpty() == false) {
				if (name == true) {
					sql = sql + " and ";
				}
				sql = sql + " c.palastname like :v2";
				surname = true;
			}
		}

		if (name == true || surname == true) {
			Query createQuery = createEntityManager.createQuery(sql);

			if (surname == true) {
				createQuery.setParameter("v2", personSearch.getSurname() + "%");
			}

			if (name == true) {
				createQuery.setParameter("v1", personSearch.getName() + "%");
			}

			List resultList = createQuery.getResultList();

			
			
			System.out.println("Size "+resultList.size());
			
			for (Object object : resultList) {
				
				System.out.println("object  "+object);
				
			}
			return resultList;
		} else {
			return null;
		}

	}

	@Override
	public List<Customer> getCustomerBlackList() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		Query setParameter = entityManager.createQuery(
				"Select c from Customer c where c.status=:value").setParameter(
				"value", CustomerStatus.NOT_OK);

		return setParameter.getResultList();
	}

	@Override
	public boolean modifyCustomer(Customer customer) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();
		String drfirstname = customer.getPafirstname();
		String drlastname = customer.getPalastname();
		String drlicnr = customer.getDrlicnr();

		if (drfirstname != null) {

			if (drfirstname.isEmpty()) {

				NotifierDialog.notify("Njoftim",
						"Fusha emri nuk mund te jete bosh!",
						NotificationType.ERROR);
				return false;
			}
		} else {

			NotifierDialog
					.notify("Njoftim", "Fusha emri nuk mund te jete bosh!",
							NotificationType.ERROR);
			return false;
		}
		if (drlastname != null) {

			if (drlastname.isEmpty()) {
				NotifierDialog.notify("Njoftim",
						"Fusha mbiemri nuk mund te jete bosh!",
						NotificationType.ERROR);
				return false;
			}
		} else {

			NotifierDialog.notify("Njoftim",
					"Fusha mbiemri nuk mund te jete bosh!",
					NotificationType.ERROR);
			return false;
		}
		if (drlicnr != null) {

			if (drlicnr.isEmpty()) {

				NotifierDialog.notify("Njoftim",
						"Fusha nr patentes nuk mund te jete bosh!",
						NotificationType.ERROR);
				return false;
			}
		} else {
			NotifierDialog.notify("Njoftim",
					"Fusha nr patentes nuk mund te jete bosh!",
					NotificationType.ERROR);
			return false;
		}

		try {
			entityManager.merge(customer);
			entityManager.getTransaction().commit();
		} catch (Exception e) {

			// TODO: handle exception

			e.printStackTrace();
			NotifierDialog.notify("Njoftim",
					"Fusha pasporte id nuk mund te jete bosh!",
					NotificationType.ERROR);
			return false;
		}
		NotifierDialog.notify("Njoftim", "Klienti u modfikua me sukses",
				NotificationType.SUCCESS);

		entityManager.close();
		return true;
	}

	@Override
	public Customer exitCustomer(Customer customer) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		Query setParameter = entityManager.createQuery(
				"Select m from Customer m where m.drpasportid=:v")
				.setParameter("v", customer.getDrpasportid());
		Customer singleResult = null;
		try {
			singleResult = (Customer) setParameter.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
		return singleResult;

	}

}
