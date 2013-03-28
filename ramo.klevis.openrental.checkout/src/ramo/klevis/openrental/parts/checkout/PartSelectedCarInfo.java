package ramo.klevis.openrental.parts.checkout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.forms.FormSelectedCar;
import ramo.klevis.openrental.utils.CarImageHolder;

public class PartSelectedCarInfo {

	private FormSelectedCar formSelectedCar;
	Composite parent;

	public PartSelectedCarInfo() {
	}

	@Inject
	@Optional
	public void clearAll(@EventTopic("ClearAllCheckOut") String s) {

		if (formSelectedCar != null)
			formSelectedCar.setSelectedCar(new Car());
	}

	@Inject
	@Optional
	public void showCSelectedCar(@EventTopic(value = "CarSelected") Car car) {

		formSelectedCar.setSelectedCar(car);
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {

		this.parent = parent;
		formSelectedCar = new FormSelectedCar(parent, SWT.NONE);

		
		CarImageHolder carImageHolder = new CarImageHolder(parent, 0);
		carImageHolder.setBounds(1, 1, 300, 300);
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}

}
