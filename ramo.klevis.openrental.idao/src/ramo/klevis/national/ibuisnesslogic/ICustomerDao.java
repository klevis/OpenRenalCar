 package ramo.klevis.national.ibuisnesslogic;

import java.util.List;

import ramo.klevis.national.beans.PersonSearch;
import ramo.klevis.openrental.entity.Customer;

public interface ICustomerDao {

	List<Customer> searchCustomer(PersonSearch personSearch);

	boolean modifyCustomer(Customer customer);

	List<Customer> getCustomerBlackList();

	/**
	 * 
	 * @param customer
	 * @return
	 * 
	 * true ndodhet ne black list
	 * false nuk ndodhet
	 */
	boolean controlCustomer(Customer customer);

	Customer exitCustomer(Customer customer);

}
