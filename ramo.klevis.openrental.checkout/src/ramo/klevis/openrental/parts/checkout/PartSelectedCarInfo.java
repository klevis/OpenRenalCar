package ramo.klevis.openrental.parts.checkout;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ramo.klevis.national.ibuisnesslogic.ICarsDao;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.forms.FormSelectedCar;
import ramo.klevis.openrental.utils.CarImageHolder;

public class PartSelectedCarInfo {

	private FormSelectedCar formSelectedCar;
	Composite parent;
	private CarImageHolder carImageHolder;

	public PartSelectedCarInfo() {
	}

	@Inject
	@Optional
	public void clearAll(@EventTopic("ClearAllCheckOut") String s) {

		if (formSelectedCar != null)
			formSelectedCar.setSelectedCar(new Car());

		if (carImageHolder != null)
			carImageHolder.dispose();
		carImageHolder = new CarImageHolder(parent, 0);
		carImageHolder.setBounds(1, 1, 300, 300);
	}

	@Inject
	@Optional
	public void showSelectedCar(@EventTopic(value = "CarSelected") Car car) {

		formSelectedCar.setSelectedCar(car);

		showCarImage(car);

	}

	private void showCarImage(Car car) {
		if (car.getPic() != null) {

			if (car.getPic().length > 0) {
				InputStream in = new ByteArrayInputStream(car.getPic());
				try {
					BufferedImage bImageFromConvert = ImageIO.read(in);
					String extent = car.getExtent();
					String string = "c:/prov/image." + extent;
					if (extent != null) {
						ImageIO.write(bImageFromConvert, extent, new File(
								string));

						carImageHolder.addImage(string);
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {

		this.parent = parent;
		formSelectedCar = new FormSelectedCar(parent, SWT.NONE);

		carImageHolder = new CarImageHolder(parent, 0);
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
