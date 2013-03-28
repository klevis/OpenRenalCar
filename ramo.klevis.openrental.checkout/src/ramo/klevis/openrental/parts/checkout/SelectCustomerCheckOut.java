package ramo.klevis.openrental.parts.checkout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;

import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.openrental.forms.FormCustomer;
import ramo.klevis.openrental.forms.FormSearchPerson;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;

public class SelectCustomerCheckOut {

	public SelectCustomerCheckOut() {
	}

	@Inject
	IEventBroker eventBroker;
	private FormSearchPerson formSearchPerson;

	@Inject
	@Optional
	public void clearAll(@EventTopic("ClearAllCheckOut") String s) {
		formSearchPerson.clear();
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent, ICustomerDao customerDao) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		formSearchPerson = new FormSearchPerson(parent, 0);

		formSearchPerson.setEventBroker(eventBroker);
		formSearchPerson.setCustomerDao(customerDao);

	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}

}
