package ramo.klevis.openrental.parts.checkout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import ramo.klevis.openrental.forms.FormAvaibleCar;
import ramo.klevis.openrental.iservice.IFreeCarsConsumer;

public class SelectCarCheckOut {

	public SelectCarCheckOut() {
	}


	@Inject
	IFreeCarsConsumer freeCarsConsumer;
	/**
	 * Create contents of the view part.
	 */
	
	@Inject
	IEventBroker eventBroker;
	
	
	@PostConstruct
	public void createControls(Composite parent
			) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		FormAvaibleCar formAvaibleCar = new FormAvaibleCar(parent, SWT.NONE);

        formAvaibleCar.setEventBroker(eventBroker);
		formAvaibleCar.setFreeCarConsumer(freeCarsConsumer);
		formAvaibleCar.fillFileds();

	
		

		

	}
	

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}

}
