package ramo.klevis.national.buisnesslogic;

import java.util.List;

import javax.persistence.EntityManager;

import ramo.klevis.national.ibuisnesslogic.IRolesDao;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Role;

public class RolesDao implements IRolesDao {

	@Override
	public List<Role> getRoles() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		List<Role> resultList = entityManager.createQuery(
				"Select r from Role r").getResultList();

		
		return resultList;
	}

}
