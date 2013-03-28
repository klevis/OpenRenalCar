package ramo.klevis.openrental.iservice;

import ramo.klevis.national.ibuisnesslogic.IClassDao;
import ramo.klevis.national.ibuisnesslogic.IFreeCars;
import ramo.klevis.national.ibuisnesslogic.ILocationDao;

public interface IFreeCarsConsumer {

	public abstract IClassDao getClassDao();

	public abstract IFreeCars getFreedao();

	public abstract ILocationDao getLocationDao();

}