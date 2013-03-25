package ramo.klevis.national.buisnesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ramo.klevis.national.ibuisnesslogic.ISearchRentals;
import ramo.klevis.openrental.entity.Customer;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.Expression;
import ramo.klevis.openrental.utils.Operator;
import ramo.klevis.openrental.utils.QueryBuilder;
import ramo.klevis.openrental.utils.RentalStatus;

public class SearchRentals implements ISearchRentals {

	@Override
	public List<Rental> searchRentals(String name, String surname, String targa) {

		List<Expression> exp = new ArrayList<Expression>();

		EntityManager createEntityManager = EntityManagerQuery
				.getEntityManager();

		if(createEntityManager.getTransaction().isActive()==false)
		createEntityManager.getTransaction().begin();

		if (name != null) {

			if (name.isEmpty() == false) {
				exp.add(new Expression("customer.pafirstname", name,
						Operator.LIKE));

			}
		}

		if (surname != null) {
			if (surname.isEmpty() == false) {

				exp.add(new Expression("customer.palastname", surname,
						Operator.LIKE));

			}
		}

		if (targa != null) {
			if (targa.isEmpty() == false) {
				exp.add(new Expression("car.license", targa, Operator.EQ));

			}
		}

		exp.add(new Expression("rentalStatus", RentalStatus.CHECK_OUT,
				Operator.EQ));

		QueryBuilder build = new QueryBuilder();

		List resultList = build.createQuery(createEntityManager, exp, null,
				null, "Rental").getResultList();

		createEntityManager.getTransaction().commit();
		createEntityManager.close();
		return resultList;

	}

	public List<Rental> searchRentals(Customer customer) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		List resultList = entityManager
				.createQuery("Select r from Rental r where r.client2=:value")
				.setParameter("value", customer.getDrpasportid())
				.getResultList();
		System.out.println(resultList);

		return resultList;
	}

}
