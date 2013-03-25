package ramo.klevis.national.ibuisnesslogic;

import java.util.List;

import ramo.klevis.national.beans.BeanAvaibleCar;
import ramo.klevis.openrental.entity.Car;

public interface IFreeCars {

	List<Car> searchForFreeCars(BeanAvaibleCar avaibleCar);

}
