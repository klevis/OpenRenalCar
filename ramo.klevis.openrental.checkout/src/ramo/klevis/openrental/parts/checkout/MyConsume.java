package ramo.klevis.openrental.parts.checkout;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;

public class MyConsume {

	
	private IAgentDao agentDao;

	public IAgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(IAgentDao agentDao) {
		
		System.out.println("U inicializuar");
		this.agentDao = agentDao;
	} 
}
