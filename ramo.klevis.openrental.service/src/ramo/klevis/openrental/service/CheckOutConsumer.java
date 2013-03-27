package ramo.klevis.openrental.service;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;
import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.national.ibuisnesslogic.IRentalDao;
import ramo.klevis.openrental.iservice.ICheckOutConsumer;

public class CheckOutConsumer implements ICheckOutConsumer {

	private IRentalDao rentalDao;
	private ICustomerDao customerDao;
	private IAgentDao agentDao;

	@Override
	public IRentalDao getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(IRentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	@Override
	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public IAgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(IAgentDao agentDao) {
		this.agentDao = agentDao;
	}

}
