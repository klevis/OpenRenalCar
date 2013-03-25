package ramo.klevis.national.buisnesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ramo.klevis.national.ibuisnesslogic.ICarsDao;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.Dokumentacioni;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.Expression;
import ramo.klevis.openrental.utils.Operator;
import ramo.klevis.openrental.utils.QueryBuilder;
import ramo.klevis.openrental.utils.RentalStatus;
import ramo.klevis.openrental.utils.UtilDate;

public class CarsDao implements ICarsDao {

	@Override
	public List<Car> getAllCars() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		List<Car> resultList = entityManager.createQuery("Select c from Car c")
				.getResultList();

		for (Car car : resultList) {

			Dokumentacioni find = entityManager.find(Dokumentacioni.class,
					car.getId());
			car.setDokumentacioni(find);
		}
		return resultList;

	}

	@Override
	public List<Rental> getCheckOutOnDay(Date date) {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}
		UtilDate utilDate = new UtilDate(date);
		Date firstDate = utilDate.getFirstDate();
		Date secondDate = utilDate.getSecondDate();

		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.starttime<=:second and r.starttime>=:first and r.rentalStatus=:v")
				.setParameter("second", secondDate)
				.setParameter("first", firstDate)
				.setParameter("v", RentalStatus.CHECK_OUT).getResultList();

		return resultList;

	}

	@Override
	public List<Rental> getCheckOutToDayCars() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}
		UtilDate utilDate = new UtilDate(new Date(), new Date());
		Date firstDate = utilDate.getFirstDate();
		Date secondDate = utilDate.getSecondDate();

		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.starttime<=:second and r.starttime>=:first and r.rentalStatus=:v")
				.setParameter("second", secondDate)
				.setParameter("first", firstDate)
				.setParameter("v", RentalStatus.CHECK_OUT).getResultList();

		return resultList;
	}

	@Override
	public List<Rental> getComingToDayCars() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}
		UtilDate utilDate = new UtilDate(new Date(), new Date());
		Date firstDate = utilDate.getFirstDate();
		Date secondDate = utilDate.getSecondDate();

		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.endtime<=:second and r.endtime>=:first and r.rentalStatus=:v")
				.setParameter("second", secondDate)
				.setParameter("first", firstDate)
				.setParameter("v", RentalStatus.CHECK_OUT).getResultList();

		return resultList;
	}

	public Car registerCar(Car c) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		if (transaction.isActive() == false) {

			transaction.begin();
		}

		Car merge = entityManager.merge(c);

		transaction.commit();
		entityManager.close();
		return merge;

	}

	@Override
	public Car getCar(String license) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}
		Car singleResult = (Car) entityManager
				.createQuery("Select c from Car c where c.license=:v")
				.setParameter("v", license).getSingleResult();

		Dokumentacioni find = entityManager.find(Dokumentacioni.class,
				singleResult.getId());
		singleResult.setDokumentacioni(find);
		return (Car) singleResult;
	}

	@Override
	public List<Car> getCar(String license, Class clazz) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		List<Expression> exp = new ArrayList<Expression>();
		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}
		if (license != null && license.isEmpty() == false) {

			exp.add(new Expression("license", license, Operator.EQ));
		}
		if (clazz != null) {

			exp.add(new Expression("classes", clazz, Operator.EQ));
		}

		if (exp.isEmpty() == false) {
			Query createQuery = new QueryBuilder().createQuery(entityManager,
					exp, null, null, "Car");

			List<Car> resultList = createQuery.getResultList();
			for (Car car : resultList) {

				Dokumentacioni find = entityManager.find(Dokumentacioni.class,
						car.getId());
				car.setDokumentacioni(find);
			}
			return resultList;
		} else {
			return new ArrayList<Car>();
		}
	}

	@Override
	public List<Car> getProblemsCar() {

		ArrayList<Car> arrayList = new ArrayList<Car>();

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}
		Query createQuery = entityManager.createQuery("Select c from Car c");

		List<Car> resultList = createQuery.getResultList();

		for (Car car : resultList) {
			boolean dirty = false;
			Dokumentacioni dokumentacioni = entityManager.find(
					Dokumentacioni.class, car.getId());
			car.setDokumentacioni(dokumentacioni);
			if (dokumentacioni != null) {
				Date kasko = dokumentacioni.getKasko();
				Date lejeNderkombetare = dokumentacioni.getLejeNderkombetare();
				Date sgs = dokumentacioni.getSgs();
				Date tpl = dokumentacioni.getTpl();
				Date date = new Date();

				if (kasko != null) {
					if (kasko.before(date)) {

						arrayList.add(car);
						dirty = true;
					}
				} else {

					arrayList.add(car);
					dirty = true;
				}

				if (!dirty)
					if (lejeNderkombetare != null) {
						if (lejeNderkombetare.before(date)) {
							arrayList.add(car);
							dirty = true;
						}
					} else {

						arrayList.add(car);
						dirty = true;
					}

				if (!dirty)
					if (sgs != null) {
						if (sgs.before(date)) {
							arrayList.add(car);
							dirty = true;
						}

					} else {
						arrayList.add(car);
						dirty = true;
					}

				if (!dirty)
					if (tpl != null) {
						if (tpl.before(date)) {
							arrayList.add(car);
							dirty = true;
						}
					} else {

						arrayList.add(car);
						dirty = true;
					}

			}
			Date date = new Date();

			if (!dirty)
				if (car != null) {
					Date nextservdate = car.getNextservdate();
					if (nextservdate != null)
						if (nextservdate.before(date)) {
							arrayList.add(car);
							dirty = true;
						}
				}

			if (!dirty)
				if (car != null)
					if (car.getMilage() - car.getvFLastKm() > car
							.getvFLimitKm()) {
						arrayList.add(car);
						dirty = true;

					} else {

					}

		}

		System.out.println(arrayList);
		return arrayList;

	}

}
