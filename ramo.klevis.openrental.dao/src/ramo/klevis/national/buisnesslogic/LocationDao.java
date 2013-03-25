package ramo.klevis.national.buisnesslogic;

import java.util.List;

import javax.persistence.EntityManager;

import ramo.klevis.national.ibuisnesslogic.ILocationDao;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Location;

public class LocationDao implements ILocationDao {

	@Override
	public List<Location> getAllLocation() {

		EntityManager em = EntityManagerQuery.getEntityManager();
		List resultList = em.createQuery("Select l from Location l")
				.getResultList();

		
		return resultList;
	}

}
