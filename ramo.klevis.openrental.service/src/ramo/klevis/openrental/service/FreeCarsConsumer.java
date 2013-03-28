package ramo.klevis.openrental.service;

import ramo.klevis.national.ibuisnesslogic.IClassDao;
import ramo.klevis.national.ibuisnesslogic.IFreeCars;
import ramo.klevis.national.ibuisnesslogic.ILocationDao;
import ramo.klevis.openrental.iservice.IFreeCarsConsumer;

public class FreeCarsConsumer implements IFreeCarsConsumer {

	private ILocationDao locationDao;
	private IClassDao classDao;
	private IFreeCars freedao;

	/* (non-Javadoc)
	 * @see ramo.klevis.openrental.service.IFreeCarsConsumer#getClassDao()
	 */
	@Override
	public IClassDao getClassDao() {
		return classDao;
	}

	public void setClassDao(IClassDao classDao) {
		
		System.err.println("Ok i am setting");
		this.classDao = classDao;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.openrental.service.IFreeCarsConsumer#getFreedao()
	 */
	@Override
	public IFreeCars getFreedao() {
		return freedao;
	}

	public void setFreeCarsDao(IFreeCars freedao) {
		this.freedao = freedao;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.openrental.service.IFreeCarsConsumer#getLocationDao()
	 */
	@Override
	public ILocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(ILocationDao locationDao) {
		this.locationDao = locationDao;
	}

}
