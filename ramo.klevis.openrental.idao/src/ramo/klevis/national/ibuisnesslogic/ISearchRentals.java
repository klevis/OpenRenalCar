package ramo.klevis.national.ibuisnesslogic;

import java.util.List;

import ramo.klevis.openrental.entity.Customer;
import ramo.klevis.openrental.entity.Rental;

public interface ISearchRentals {

	List<Rental> searchRentals(String name, String surname, String targa);

	List<Rental> searchRentals(Customer customer);

}
