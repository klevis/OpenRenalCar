package ramo.klevis.national.ibuisnesslogic;

import ramo.klevis.openrental.entity.Car;

public interface IAtomicChooserCar {

	boolean lockCar(Car car);

	void unlockCar(Car car);

}
