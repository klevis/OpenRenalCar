package ramo.klevis.openrental.forms;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.core.databinding.beans.PojoObservables;
import ramo.klevis.openrental.entity.Car;
import org.eclipse.swt.widgets.Text;

public class FormSelectedCar extends Composite {

	private Car selectedCar = new Car();
	private Text textPlate;
	private Text textMake;
	private Text textModel;
	private Text textYear;
	private Text textMilage;
	private Text textNrDays;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormSelectedCar(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label lblPlate = new Label(this, SWT.NONE);
		lblPlate.setAlignment(SWT.RIGHT);
		GridData gd_lblPlate = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblPlate.widthHint = 69;
		lblPlate.setLayoutData(gd_lblPlate);
		lblPlate.setText("Plate");

		textPlate = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textPlate.setEditable(false);
		textPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("Make");

		textMake = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textMake.setEditable(false);
		textMake.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblModel = new Label(this, SWT.NONE);
		lblModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblModel.setText("Model");

		textModel = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textModel.setEditable(false);
		textModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblYear = new Label(this, SWT.NONE);
		lblYear.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblYear.setText("Year");

		textYear = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textYear.setEditable(false);
		textYear.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblMilage = new Label(this, SWT.NONE);
		lblMilage.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblMilage.setText("Milage");

		textMilage = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textMilage.setEditable(false);
		textMilage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		
		Label lblNrDays = new Label(this, SWT.NONE);
		lblNrDays.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNrDays.setText("No. Days");
		
		textNrDays = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textNrDays.setEditable(false);
		textNrDays.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		initDataBindings();

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
		initDataBindings();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextPlateObserveWidget = WidgetProperties.text(SWT.Modify).observe(textPlate);
		IObservableValue licenseSelectedCarObserveValue = PojoObservables.observeValue(selectedCar, "license");
		bindingContext.bindValue(observeTextTextPlateObserveWidget, licenseSelectedCarObserveValue, null, null);
		//
		IObservableValue observeTextTextMakeObserveWidget = WidgetProperties.text(SWT.Modify).observe(textMake);
		IObservableValue makeSelectedCarObserveValue = PojoObservables.observeValue(selectedCar, "make");
		bindingContext.bindValue(observeTextTextMakeObserveWidget, makeSelectedCarObserveValue, null, null);
		//
		IObservableValue observeTextTextModelObserveWidget = WidgetProperties.text(SWT.Modify).observe(textModel);
		IObservableValue modelSelectedCarObserveValue = PojoObservables.observeValue(selectedCar, "model");
		bindingContext.bindValue(observeTextTextModelObserveWidget, modelSelectedCarObserveValue, null, null);
		//
		IObservableValue observeTextTextYearObserveWidget = WidgetProperties.text(SWT.Modify).observe(textYear);
		IObservableValue yearSelectedCarObserveValue = PojoObservables.observeValue(selectedCar, "year");
		bindingContext.bindValue(observeTextTextYearObserveWidget, yearSelectedCarObserveValue, null, null);
		//
		IObservableValue observeTextTextMilageObserveWidget = WidgetProperties.text(SWT.Modify).observe(textMilage);
		IObservableValue milageSelectedCarObserveValue = PojoObservables.observeValue(selectedCar, "milage");
		bindingContext.bindValue(observeTextTextMilageObserveWidget, milageSelectedCarObserveValue, null, null);
		//
		IObservableValue observeTextTextPlateObserveWidget_1 = WidgetProperties.text(SWT.Modify).observe(textNrDays);
		IObservableValue nrditeveSelectedCarObserveValue =PojoObservables.observeValue(selectedCar, "nrditeve"); 
		bindingContext.bindValue(observeTextTextPlateObserveWidget_1, nrditeveSelectedCarObserveValue, null, null);
		//
		return bindingContext;
	}
}
