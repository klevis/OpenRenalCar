package ramo.klevis.national.ibuisnesslogic;

import java.util.List;

import ramo.klevis.openrental.entity.Agent;
import ramo.klevis.openrental.entity.Rental;

public interface IAgentDao {

	void registerAgent(Agent agent);

	List<Agent> searchFormAgent(String name, String surname, String kompani);

	List<Agent> getAllAgents();

	List<Rental> getPayedAgents();

	List<Rental> getAnPayedAgents();

	List<Rental> getAnPayedAgents(int id);

	List<Rental> getPayedAgents(int id);

}
