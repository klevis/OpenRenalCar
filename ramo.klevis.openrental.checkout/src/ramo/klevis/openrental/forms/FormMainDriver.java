package ramo.klevis.openrental.forms;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ramo.klevis.openrental.entity.Customer;

public class FormMainDriver extends Composite {

	private DataBindingContext m_bindingContext;
	private Customer rental = new Customer();
	private DateTime datelindja;
	private Text drfirstnameText;
	private Text drlastnameText;
	private Text drlexpdateText;
	private Text drliccatText;
	private Text drlicnrText;
	private Text drplaceText;

	public FormMainDriver(Composite parent, int style) {
		super(parent, style);
//		this.rental = customer;
		System.err.println("rantali ne init" + this.rental);
		GridLayout gridLayout = new GridLayout(2, false);
		setLayout(gridLayout);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("Emri");

		drfirstnameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		drfirstnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("Mbiemri");

		drlastnameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		drlastnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
 
		Label label = new Label(this, SWT.NONE);
		label.setText("Datelindja");

		datelindja = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN);
		datelindja.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label lblPatentaPerf = new Label(this, SWT.NONE);
		lblPatentaPerf.setText("Patenta perfundim");

		drlexpdateText = new Text(this, SWT.BORDER | SWT.SINGLE);
		drlexpdateText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label lblKategoriaPatent = new Label(this, SWT.NONE);
		lblKategoriaPatent.setText("Kategoria Patent");

		drliccatText = new Text(this, SWT.BORDER | SWT.SINGLE);
		drliccatText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Nr Patent");

		drlicnrText = new Text(this, SWT.BORDER | SWT.SINGLE);
		drlicnrText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Vendi lindjes");

		drplaceText = new Text(this, SWT.BORDER | SWT.SINGLE);
		drplaceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

			m_bindingContext = initDataBindings();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Customer getRental() {
		return rental;
	}

	public void setCustomer(Customer customer) {
		setCustomer(customer, true);
	}

	public void setCustomer(Customer custmer,
			boolean update) {
		rental = custmer;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (rental != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue drfirstnameObserveWidget = SWTObservables.observeText(drfirstnameText, SWT.Modify);
		IObservableValue drfirstnameObserveValue = PojoObservables.observeValue(rental, "drfirstname");
		bindingContext.bindValue(drfirstnameObserveWidget, drfirstnameObserveValue, null, null);
		//
		IObservableValue drlastnameObserveWidget = SWTObservables.observeText(drlastnameText, SWT.Modify);
		IObservableValue drlastnameObserveValue = PojoObservables.observeValue(rental, "drlastname");
		bindingContext.bindValue(drlastnameObserveWidget, drlastnameObserveValue, null, null);
		//
		IObservableValue drlexpdateObserveWidget = SWTObservables.observeText(drlexpdateText, SWT.Modify);
		IObservableValue drlexpdateObserveValue = PojoObservables.observeValue(rental, "drlexpdate");
		bindingContext.bindValue(drlexpdateObserveWidget, drlexpdateObserveValue, null, null);
		//
		IObservableValue drliccatObserveWidget = SWTObservables.observeText(drliccatText, SWT.Modify);
		IObservableValue drliccatObserveValue = PojoObservables.observeValue(rental, "drliccat");
		bindingContext.bindValue(drliccatObserveWidget, drliccatObserveValue, null, null);
		//
		IObservableValue drlicnrObserveWidget = SWTObservables.observeText(drlicnrText, SWT.Modify);
		IObservableValue drlicnrObserveValue = PojoObservables.observeValue(rental, "drlicnr");
		bindingContext.bindValue(drlicnrObserveWidget, drlicnrObserveValue, null, null);
		//
		IObservableValue drplaceObserveWidget = SWTObservables.observeText(drplaceText, SWT.Modify);
		IObservableValue drplaceObserveValue = PojoObservables.observeValue(rental, "drplace");
		bindingContext.bindValue(drplaceObserveWidget, drplaceObserveValue, null, null);
		//
		IObservableValue datelindjaObserveSelectionObserveWidget = SWTObservables.observeSelection(datelindja);
		IObservableValue rentalDrbdateObserveValue = PojoObservables.observeValue(rental, "drbdate");
		bindingContext.bindValue(datelindjaObserveSelectionObserveWidget, rentalDrbdateObserveValue, null, null);
		//
		return bindingContext;
	}
}
