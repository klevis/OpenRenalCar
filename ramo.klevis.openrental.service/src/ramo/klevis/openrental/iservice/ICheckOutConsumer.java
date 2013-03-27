package ramo.klevis.openrental.iservice;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;
import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.national.ibuisnesslogic.IRentalDao;

public interface ICheckOutConsumer {

	IRentalDao getRentalDao();

	ICustomerDao getCustomerDao();

	IAgentDao getAgentDao();

}
