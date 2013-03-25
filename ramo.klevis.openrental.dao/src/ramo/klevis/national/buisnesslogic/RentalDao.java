package ramo.klevis.national.buisnesslogic;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import notifier.NotificationType;
import notifier.NotifierDialog;
import ramo.klevis.national.ibuisnesslogic.IRentalDao;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.RentalStatus;
import ramo.klevis.openrental.utils.UtilDate;

public class RentalDao implements IRentalDao {

	@Override
	public List<Rental> getCheckOuts() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();

		Date firstDate = new UtilDate(new Date()).getFirstDate();

		List list = entityManager
				.createQuery(
						"Select r from Rental r where r.rentalStatus=:v and r.endtime>=:today order by r.endtime desc")
				.setParameter("v", RentalStatus.CHECK_OUT)
				.setParameter("today", firstDate).getResultList();

		return list;
	}

	@Override
	public List<Rental> getRezervations() {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();

		List list = entityManager
				.createQuery(
						"Select r from Rental r where r.rentalStatus=:v order by r.endtime desc")
				.setParameter("v", RentalStatus.REZERVATION).getResultList();

		return list;
	}

	@Override
	public List<Rental> getToDayRezervation() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		Query createQuery = entityManager
				.createQuery("Select r from Rental r where r.starttime>=:v1 and r.starttime<=:v2 and r.rentalStatus=:stat");
		Date date1 = new Date();
		Date date2 = new Date();
		UtilDate utilDate = new UtilDate(date1, date2);

		createQuery.setParameter("stat", RentalStatus.REZERVATION);
		createQuery.setParameter("v1", utilDate.getFirstDate());
		createQuery.setParameter("v2", utilDate.getSecondDate());

		return createQuery.getResultList();

	}

	@Override
	public List<Rental> getRentalsInrageDate(Date first, Date second) {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where  r.starttime between :st and :en or r.endtime between :st and :en  order by r.starttime")
				.setParameter("st", first).setParameter("en", second)
				.getResultList();

		CarsDao carsDao = new CarsDao();

		resultList.addAll(carsDao.getCheckOutToDayCars());
		return resultList;

	}

	public List<Rental> getRentalsBetween(Date first, Date second) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where  r.starttime<=:v2 and r.endtime>=:v1 and r.endtime<=:v2 and r.starttime>=:v1 order by r.starttime")
				.setParameter("v1", first).setParameter("v2", second)
				.getResultList();

		return resultList;

	}

	@Override
	public Rental registerRezarvation(Rental rental) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();
		Rental merge = entityManager.merge(rental);

		entityManager.getTransaction().commit();

		return merge;
	}

	public Rental registerRental(Rental rental) {

		EntityManager createEntityManager = EntityManagerQuery
				.getEntityManager();
		if (createEntityManager.getTransaction().isActive() == false) {
			createEntityManager.getTransaction().begin();
		}

		String drfirstname = rental.getCustomer().getPafirstname();
		String drlastname = rental.getCustomer().getPalastname();
		String drlicnr = rental.getCustomer().getDrlicnr();
		String drhphone = rental.getCustomer().getDrhphone();
		String dremail = rental.getCustomer().getDremail();

		if (dremail != null) {

			if (dremail.isEmpty()) {
				NotifierDialog.notify("Njoftim",
						"Fusha email nuk mund te jete bosh!",
						NotificationType.ERROR);

				return null;
			}
		} else {
			NotifierDialog.notify("Njoftim",
					"Fusha email  nuk mund te jete bosh!",
					NotificationType.ERROR);

			return null;
		}

		if (drhphone != null) {

			if (drhphone.isEmpty()) {
				NotifierDialog.notify("Njoftim",
						"Fusha Numri telefonit nuk mund te jete bosh!",
						NotificationType.ERROR);

				return null;
			}
		} else {
			NotifierDialog.notify("Njoftim",
					"Fusha Numri telefonit nuk mund te jete bosh!",
					NotificationType.ERROR);

			return null;
		}

		if (drfirstname != null) {

			if (drfirstname.isEmpty()) {

				NotifierDialog.notify("Njoftim",
						"Fusha emri nuk mund te jete bosh!",
						NotificationType.ERROR);
				return null;
			}
		} else {

			NotifierDialog
					.notify("Njoftim", "Fusha emri nuk mund te jete bosh!",
							NotificationType.ERROR);
			return null;
		}
		if (drlastname != null) {

			if (drlastname.isEmpty()) {
				NotifierDialog.notify("Njoftim",
						"Fusha mbiemri nuk mund te jete bosh!",
						NotificationType.ERROR);
				return null;
			}
		} else {

			NotifierDialog.notify("Njoftim",
					"Fusha mbiemri nuk mund te jete bosh!",
					NotificationType.ERROR);
			return null;
		}
		if (drlicnr != null) {

			if (drlicnr.isEmpty()) {

				NotifierDialog.notify("Njoftim",
						"Fusha nr patentes nuk mund te jete bosh!",
						NotificationType.ERROR);
				return null;
			}
		} else {
			NotifierDialog.notify("Njoftim",
					"Fusha nr patentes nuk mund te jete bosh!",
					NotificationType.ERROR);
			return null;
		}

		createEntityManager.merge(rental.getCustomer());
		rental.getCar().setChoosen("" + rental.getCar().getId());

		createEntityManager.merge(rental.getCar());

		System.out.println(rental.getId());
		Rental merge = createEntityManager.merge(rental);

		try {
			createEntityManager.getTransaction().commit();
		} catch (Exception e) {

			// TODO: handle exception

			e.printStackTrace();
			NotifierDialog.notify("Njoftim",
					"Fusha pasporte id nuk mund te jete bosh!",
					NotificationType.ERROR);

			return null;
		}
		createEntityManager.close();
		return merge;
	}

}
