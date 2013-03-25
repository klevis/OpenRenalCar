package ramo.klevis.national.ibuisnesslogic;

import java.util.Date;
import java.util.List;

import ramo.klevis.openrental.entity.Rental;

public interface IRentalDao {

	Rental registerRental(Rental rental);

	List<Rental> getRentalsBetween(Date first, Date second);

	Rental registerRezarvation(Rental rental);

	List<Rental> getToDayRezervation();

	List<Rental> getRentalsInrageDate(Date first, Date second);

	List<Rental> getCheckOuts();

	List<Rental> getRezervations();

}
