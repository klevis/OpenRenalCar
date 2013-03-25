package ramo.klevis.national.buisnesslogic;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ramo.klevis.national.beans.BeanAvaibleCar;
import ramo.klevis.national.ibuisnesslogic.IFreeCars;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.RentalStatus;
import ramo.klevis.openrental.utils.UtilDate;

public class FreeCars implements IFreeCars {

	public List<Rental> getBusyCarsOnMonth(Date from, Date to) {

		return null;
	}

	public void regRezervationPassed() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();

		UtilDate utilDate = new UtilDate(new Date());
		Date firstDate = utilDate.getFirstDate();
		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.starttime<:today and r.rentalStatus=:stat")
				.setParameter("today", utilDate.getSecondDate())
				.setParameter("stat", RentalStatus.REZERVATION).getResultList();
		for (Object object : resultList) {

			Rental rent = (Rental) object;
			rent.setRentalStatus(RentalStatus.REZERVATION_CANCEL);
			entityManager.merge(rent);
		}
		entityManager.getTransaction().commit();

		entityManager.close();

	}

	@Override
	public List<Car> searchForFreeCars(BeanAvaibleCar avaibleCar) {

		regRezervationPassed();
		String sql = "Select ca from Car ca where ";
		String sql2 = "  ca.id not in ( Select r.vehicle from Rental r   where ";
		EntityManager createEntityManager = EntityManagerQuery
				.getEntityManager();

		EntityTransaction transaction = createEntityManager.getTransaction();
		if (transaction.isActive() == false) {
			transaction.begin();
		}
		boolean clazz = false;
		boolean locat = false;

		System.out.println(avaibleCar.getFromDate());
		System.out.println(avaibleCar.getToDate());
		if (avaibleCar.getClazz() != null) {

			sql = sql + "ca.classes=:value1 ";
			clazz = true;

		}

		// if (avaibleCar.getLocation() != null) {
		// locat = true;
		// sql = sql + " and ca.vehbr=:value2 ";
		// }

		if (locat == true || clazz == true) {
			sql = sql + " and ";
		}
		sql2 = sql2
				+ "  (r.starttime between :value3 and :value4 or r.endtime between :value3 and :value4 or (r.starttime>=:value3 and r.endtime<=:value4) ) and r.endtime>=:today and (r.rentalStatus=:stat or r.rentalStatus=:stat2) ";

		sql = sql + sql2 + " )";
		// sql = sql + "  and  r.car.id not in (Select c.id from Car c) ";
		Query createQuery2 = createEntityManager.createQuery(sql);

		UtilDate utilDate = new UtilDate(new Date());
		System.out.println(utilDate.getFirstDate() + " first");
		createQuery2.setParameter("value3", avaibleCar.getFromDate());
		createQuery2.setParameter("value4", avaibleCar.getToDate());
		createQuery2.setParameter("stat", RentalStatus.CHECK_OUT);
		createQuery2.setParameter("stat2", RentalStatus.REZERVATION);
		createQuery2.setParameter("today", utilDate.getFirstDate());
		if (clazz == true) {
			createQuery2.setParameter("value1", avaibleCar.getClazz());
		}

		if (locat == true) {
			createQuery2.setParameter("value2", avaibleCar.getLocation()
					.getLoc());

		}

		System.out.println(sql);

		List resultList = createQuery2.getResultList();

		// Query createQuery = createEntityManager
		// .createQuery(" Select r from Rental r   where     (r.starttime>=:value3 and r.endtime>=:value4) or (r.starttime<=:value3 and r.endtime>=:value4) or (r.starttime>=:value3 and r.endtime<=:value4) or (r.starttime<=:value3 and r.endtime<=:value4) and (r.rentalStatus=:stat or r.rentalStatus=:stat2)  ");
		// createQuery.setParameter("value3", avaibleCar.getFromDate());
		// createQuery.setParameter("value4", avaibleCar.getToDate());
		// createQuery.setParameter("stat", RentalStatus.CHECK_OUT);
		// createQuery.setParameter("stat2", RentalStatus.REZERVATION);
		// List<Rental> resultList2 = createQuery.getResultList();
		// System.out.println(resultList2.size()+"sss");
		// ;
		// for (Rental rental : resultList2) {
		//
		// System.out.println(rental.getStarttime() + "   "
		// + rental.getEndtime());
		// }

		transaction.commit();
		createEntityManager.close();
		return resultList;

	}
}
