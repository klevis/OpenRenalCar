package ramo.klevis.national.buisnesslogic;

import java.util.List;

import javax.persistence.EntityManager;

import ramo.klevis.national.ibuisnesslogic.IClassDao;
import ramo.klevis.openrental.entity.EntityManagerQuery;
public class ClassDao implements IClassDao {

	public List<ramo.klevis.openrental.entity.Class> getAllClasses() {

		EntityManager createEntityManager = EntityManagerQuery
				.getEntityManager();

		List resultList = createEntityManager.createQuery(
				"Select c from Class c").getResultList();

		System.out.println("rezult   "+resultList.size());
		return resultList;

	}

}
