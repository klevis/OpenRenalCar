package ramo.klevis.openrental.parts.checkout;

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

import ramo.klevis.openrental.entity.Rental;

public class FormAdditionalDirver extends Composite {

	private DataBindingContext m_bindingContext;
	private Rental rental = new Rental();
	private Text dr2addrText;
	private DateTime datelindja;
	private Text dr2cityText;
	private Text dr2fnameText;
	private Text dr2lastnameText;
	private Text dr2liccatText;
	private DateTime lickDataExp;
	private Text dr2licnrText;
	private Text dr2phoneText;
	private Text dr2placeText;
	private Text dr2stateText;

	public FormAdditionalDirver(Composite parent, int style,
			Rental newRental) {
		this(parent, style);
		setRental(newRental);
	}

	public FormAdditionalDirver(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label label = new Label(this, SWT.NONE);
		label.setText("Emri");

		dr2fnameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2fnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("Mbiemri");

		dr2lastnameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2lastnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Adresa");

		dr2addrText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2addrText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Ditelindja");

		datelindja = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN);
		datelindja
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("Shteti");

		dr2stateText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2stateText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Qyteti");

		dr2cityText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2cityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("Vendlindja");

		dr2placeText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2placeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label lblNrPatent = new Label(this, SWT.NONE);
		lblNrPatent.setText("Nr Patent");

		dr2licnrText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2licnrText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label lblKategoriaPatent = new Label(this, SWT.NONE);
		lblKategoriaPatent.setText("Kategoria Patent");

		dr2liccatText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2liccatText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label lblPatentPerfundim = new Label(this, SWT.NONE);
		lblPatentPerfundim.setText("Patent perfundim");

		lickDataExp = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN);
		lickDataExp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		Label lblTel = new Label(this, SWT.NONE);
		lblTel.setText("Tel");

		dr2phoneText = new Text(this, SWT.BORDER | SWT.SINGLE);
		dr2phoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		if (rental != null) {
			m_bindingContext = initDataBindings();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental newRental) {
		setRental(newRental, true);
	}

	public void setRental(Rental newRental,
			boolean update) {
		rental = newRental;
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
		IObservableValue dr2addrObserveWidget = SWTObservables.observeText(dr2addrText, SWT.Modify);
		IObservableValue dr2addrObserveValue = PojoObservables.observeValue(rental, "dr2addr");
		bindingContext.bindValue(dr2addrObserveWidget, dr2addrObserveValue, null, null);
		//
		IObservableValue dr2cityObserveWidget = SWTObservables.observeText(dr2cityText, SWT.Modify);
		IObservableValue dr2cityObserveValue = PojoObservables.observeValue(rental, "dr2city");
		bindingContext.bindValue(dr2cityObserveWidget, dr2cityObserveValue, null, null);
		//
		IObservableValue dr2fnameObserveWidget = SWTObservables.observeText(dr2fnameText, SWT.Modify);
		IObservableValue dr2fnameObserveValue = PojoObservables.observeValue(rental, "dr2fname");
		bindingContext.bindValue(dr2fnameObserveWidget, dr2fnameObserveValue, null, null);
		//
		IObservableValue dr2lastnameObserveWidget = SWTObservables.observeText(dr2lastnameText, SWT.Modify);
		IObservableValue dr2lastnameObserveValue = PojoObservables.observeValue(rental, "dr2lastname");
		bindingContext.bindValue(dr2lastnameObserveWidget, dr2lastnameObserveValue, null, null);
		//
		IObservableValue dr2licnrObserveWidget = SWTObservables.observeText(dr2licnrText, SWT.Modify);
		IObservableValue dr2licnrObserveValue = PojoObservables.observeValue(rental, "dr2licnr");
		bindingContext.bindValue(dr2licnrObserveWidget, dr2licnrObserveValue, null, null);
		//
		IObservableValue dr2liccatObserveWidget = SWTObservables.observeText(dr2liccatText, SWT.Modify);
		IObservableValue dr2liccatObserveValue = PojoObservables.observeValue(rental, "dr2liccat");
		bindingContext.bindValue(dr2liccatObserveWidget, dr2liccatObserveValue, null, null);
		//
		IObservableValue dr2phoneObserveWidget = SWTObservables.observeText(dr2phoneText, SWT.Modify);
		IObservableValue dr2phoneObserveValue = PojoObservables.observeValue(rental, "dr2phone");
		bindingContext.bindValue(dr2phoneObserveWidget, dr2phoneObserveValue, null, null);
		//
		IObservableValue dr2placeObserveWidget = SWTObservables.observeText(dr2placeText, SWT.Modify);
		IObservableValue dr2placeObserveValue = PojoObservables.observeValue(rental, "dr2place");
		bindingContext.bindValue(dr2placeObserveWidget, dr2placeObserveValue, null, null);
		//
		IObservableValue dr2stateObserveWidget = SWTObservables.observeText(dr2stateText, SWT.Modify);
		IObservableValue dr2stateObserveValue = PojoObservables.observeValue(rental, "dr2state");
		bindingContext.bindValue(dr2stateObserveWidget, dr2stateObserveValue, null, null);
		//
		IObservableValue datelindjaObserveSelectionObserveWidget = SWTObservables.observeSelection(datelindja);
		IObservableValue rentalDr2bdateObserveValue = PojoObservables.observeValue(rental, "dr2bdate");
		bindingContext.bindValue(datelindjaObserveSelectionObserveWidget, rentalDr2bdateObserveValue, null, null);
		//
		IObservableValue lickDataExpObserveSelectionObserveWidget = SWTObservables.observeSelection(lickDataExp);
		IObservableValue rentalDr2licexpdateObserveValue = PojoObservables.observeValue(rental, "dr2licexpdate");
		bindingContext.bindValue(lickDataExpObserveSelectionObserveWidget, rentalDr2licexpdateObserveValue, null, null);
		//
		return bindingContext;
	}
}
