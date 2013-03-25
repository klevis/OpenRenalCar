package ramo.klevis.national.buisnesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;
import ramo.klevis.openrental.entity.Agent;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.Expression;
import ramo.klevis.openrental.utils.Operator;
import ramo.klevis.openrental.utils.QueryBuilder;

public class AgentDao implements IAgentDao {

	@Override
	public List<Rental> getAnPayedAgents(int id) {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.agentpayed=false and r.agent=:v group by r.agents")
				.setParameter("v", id).getResultList();

		return resultList;

	}

	@Override
	public List<Rental> getPayedAgents(int id) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.agentpayed=true and r.agent=:v group by r.agents ")
				.setParameter("v", id).getResultList();

		return resultList;

	}
	
	@Override
	public List<Rental> getAnPayedAgents() {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.agentpayed=false group by r.agents")
				.getResultList();

		return resultList;

	}

	@Override
	public List<Rental> getPayedAgents() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();
		List resultList = entityManager
				.createQuery(
						"Select r from Rental r where r.agentpayed=true group by r.agents")
				.getResultList();

		return resultList;

	}

	public List<Agent> getAllAgents() {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		List resultList = entityManager.createQuery("Select a from Agent a ")
				.getResultList();

		return resultList;

	}

	public void registerAgent(Agent agent) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();

		if (transaction.isActive() == false) {

			transaction.begin();
		}

		entityManager.merge(agent);
		transaction.commit();
		entityManager.close();
	}

	public List<Agent> searchFormAgent(String name, String surname,
			String kompani) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		ArrayList<Expression> exp = new ArrayList<Expression>();

		if (name != null)
			if (!name.trim().isEmpty()) {
				name = name + "%";
				exp.add(new Expression("salesperson", name, Operator.LIKE));

			}

		if (surname != null)
			if (!surname.trim().isEmpty()) {
				surname = surname + "%";
				exp.add(new Expression("surname", surname, Operator.LIKE));

			}

		if (kompani != null)
			if (!kompani.trim().isEmpty()) {
				kompani = kompani + "%";
				exp.add(new Expression("companyname", kompani, Operator.LIKE));

			}

		if (!exp.isEmpty()) {
			QueryBuilder queryBuilder = new QueryBuilder();
			List resultList = queryBuilder.createQuery(entityManager, exp,
					null, null, "Agent").getResultList();

			return resultList;
		} else
			return new ArrayList<Agent>();
	}
	
	public static void main(String[] args) {

		AgentDao agentDao = new AgentDao();
		List<Rental> anPayedAgents2 = agentDao.getAnPayedAgents();
		for (Rental rental : anPayedAgents2) {
			if(rental.getAgents()!=null){
				
				System.out.println(rental.getAgents().getNameSuranme());
			}
		}
		List<Rental> anPayedAgents = agentDao.getPayedAgents();
		for (Rental rental : anPayedAgents) {
			if(rental.getAgents()!=null){
				
				System.out.println("Payed"+rental.getAgents().getNameSuranme());
			}
		}
		
		System.out.println(anPayedAgents);
	}
}
