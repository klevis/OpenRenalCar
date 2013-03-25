package ramo.klevis.national.ibuisnesslogic;

import java.util.Date;
import java.util.List;

import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.Rental;

public interface ICarsDao {

	Car getCar(String license);

	List<Car> getCar(String license, Class clazz);

	List<Car> getProblemsCar();

	Car registerCar(Car c);

	List<Rental> getComingToDayCars();

	List<Rental> getCheckOutToDayCars();

	List<Car> getAllCars();

	List<Rental> getCheckOutOnDay(Date date);

}
