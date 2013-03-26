package ramo.klevis.openrental.forms;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ramo.klevis.openrental.entity.Customer;

public class FormCustomer extends Composite {

	private DataBindingContext m_bindingContext;
	private ramo.klevis.openrental.entity.Customer customer = new ramo.klevis.openrental.entity.Customer();
	private Text companynameText;
	private Text draddressText;
	private Text drcityText;
	private Text drcountryText; 
	private Text dremailText;
	private Text drhphoneText;
	private Text drpasportidText;
	private Text pafirstnameText;
	private Text palastnameText;
	private Text workphoneText;
	private Text text_3;
	private Text text_5;

	public void addCardCrdencials() {
		Label lblNrKartes = new Label(this, SWT.NONE);
		lblNrKartes.setLayoutData(new RowData(SWT.DEFAULT, 23));
		lblNrKartes.setBounds(379, 66, 49, 13);
		lblNrKartes.setText("Nr Kartes");

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(434, 63, 91, 19);

		Label lblDataPerf = new Label(this, SWT.NONE);
		lblDataPerf.setBounds(531, 66, 49, 13);
		lblDataPerf.setText("Data Perf");

		text_5 = new Text(this, SWT.BORDER);
		text_5.setLayoutData(new RowData(122, SWT.DEFAULT));
		text_5.setBounds(586, 63, 79, 19);

		if (customer != null) {

			String cardnr = customer.getCardnr();
			if (cardnr != null)
				text_3.setText(cardnr);
			String cardexp = customer.getCardexp();
			if (cardexp != null)
				text_5.setText(cardexp);
		}
	}
	public FormCustomer(Composite parent, int style,
			ramo.klevis.openrental.entity.Customer newCustomer) {
		this(parent, style);
		setCustomer(newCustomer);
	}
 
	public FormCustomer(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(new RowLayout(SWT.HORIZONTAL));

		Label label = new Label(this, SWT.NONE);
		label.setText("Emri Kompanise");

		companynameText = new Text(this, SWT.BORDER | SWT.SINGLE);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("Adresa");

		draddressText = new Text(this, SWT.BORDER | SWT.SINGLE);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("Qyteti");

		drcityText = new Text(this, SWT.BORDER | SWT.SINGLE);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("Shteti");

		drcountryText = new Text(this, SWT.BORDER | SWT.SINGLE);

		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("Email");

		dremailText = new Text(this, SWT.BORDER | SWT.SINGLE);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setText("Tel/Shpi");

		setDrhphoneText(new Text(this, SWT.BORDER | SWT.SINGLE));

		Label label_7 = new Label(this, SWT.NONE);
		label_7.setText("Pasaport id");

		drpasportidText = new Text(this, SWT.BORDER | SWT.SINGLE);

		Label label_8 = new Label(this, SWT.NONE);
		label_8.setText("Emri");

		setPafirstnameText(new Text(this, SWT.BORDER | SWT.SINGLE));

		Label label_9 = new Label(this, SWT.NONE);
		label_9.setText("Mbiemri");

		setPalastnameText(new Text(this, SWT.BORDER | SWT.SINGLE));

		Label label_10 = new Label(this, SWT.NONE);
		label_10.setText("Tel/Pune");

		workphoneText = new Text(this, SWT.BORDER | SWT.SINGLE);

		if (customer != null) {
			m_bindingContext = initDataBindings();
		}
		
		addCardCrdencials();
	}

	
	public void addCardCrdencials(Customer c) {

		if (c != null) {

			String cardnr = c.getCardnr();
			if (cardnr != null)
				text_3.setText(cardnr);
			String cardexp = c.getCardexp();
			if (cardexp != null)
				text_5.setText(cardexp);
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ramo.klevis.openrental.entity.Customer getCustomer() {
		return customer;
	}

	public void setCustomer(ramo.klevis.openrental.entity.Customer newCustomer) {
		setCustomer(newCustomer, true);
	}

	public void setCustomer(ramo.klevis.openrental.entity.Customer newCustomer,
			boolean update) {
		customer = newCustomer;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null; 
			}
			if (customer != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue companynameObserveWidget = SWTObservables.observeText(
				companynameText, SWT.Modify);
		IObservableValue companynameObserveValue = PojoObservables
				.observeValue(customer, "companyname");
		bindingContext.bindValue(companynameObserveWidget,
				companynameObserveValue, null, null);
		//
		IObservableValue draddressObserveWidget = SWTObservables.observeText(
				draddressText, SWT.Modify);
		IObservableValue draddressObserveValue = PojoObservables.observeValue(
				customer, "draddress");
		bindingContext.bindValue(draddressObserveWidget, draddressObserveValue,
				null, null);
		//
		IObservableValue drcityObserveWidget = SWTObservables.observeText(
				drcityText, SWT.Modify);
		IObservableValue drcityObserveValue = PojoObservables.observeValue(
				customer, "drcity");
		bindingContext.bindValue(drcityObserveWidget, drcityObserveValue, null,
				null);
		//
		IObservableValue drcountryObserveWidget = SWTObservables.observeText(
				drcountryText, SWT.Modify);
		IObservableValue drcountryObserveValue = PojoObservables.observeValue(
				customer, "drcountry");
		bindingContext.bindValue(drcountryObserveWidget, drcountryObserveValue,
				null, null);
		//
		IObservableValue dremailObserveWidget = SWTObservables.observeText(
				dremailText, SWT.Modify);
		IObservableValue dremailObserveValue = PojoObservables.observeValue(
				customer, "dremail");
		bindingContext.bindValue(dremailObserveWidget, dremailObserveValue,
				null, null);
		//
		IObservableValue drhphoneObserveWidget = SWTObservables.observeText(
				getDrhphoneText(), SWT.Modify);
		IObservableValue drhphoneObserveValue = PojoObservables.observeValue(
				customer, "drhphone");
		bindingContext.bindValue(drhphoneObserveWidget, drhphoneObserveValue,
				null, null);
		//
		IObservableValue drpasportidObserveWidget = SWTObservables.observeText(
				drpasportidText, SWT.Modify);
		IObservableValue drpasportidObserveValue = PojoObservables
				.observeValue(customer, "drpasportid");
		bindingContext.bindValue(drpasportidObserveWidget,
				drpasportidObserveValue, null, null);
		//
		IObservableValue pafirstnameObserveWidget = SWTObservables.observeText(
				getPafirstnameText(), SWT.Modify);
		IObservableValue pafirstnameObserveValue = PojoObservables
				.observeValue(customer, "pafirstname");
		bindingContext.bindValue(pafirstnameObserveWidget,
				pafirstnameObserveValue, null, null);
		//
		IObservableValue palastnameObserveWidget = SWTObservables.observeText(
				getPalastnameText(), SWT.Modify);
		IObservableValue palastnameObserveValue = PojoObservables.observeValue(
				customer, "palastname");
		bindingContext.bindValue(palastnameObserveWidget,
				palastnameObserveValue, null, null);
		//
		IObservableValue workphoneObserveWidget = SWTObservables.observeText(
				workphoneText, SWT.Modify);
		IObservableValue workphoneObserveValue = PojoObservables.observeValue(
				customer, "workphone");
		bindingContext.bindValue(workphoneObserveWidget, workphoneObserveValue,
				null, null);
		//
		return bindingContext;
	}

	public Text getPafirstnameText() {
		return pafirstnameText;
	}

	public void setPafirstnameText(Text pafirstnameText) {
		this.pafirstnameText = pafirstnameText;
	}

	public Text getPalastnameText() {
		return palastnameText;
	}

	public void setPalastnameText(Text palastnameText) {
		this.palastnameText = palastnameText;
	}

	public Text getDrhphoneText() {
		return drhphoneText;
	}

	public void setDrhphoneText(Text drhphoneText) {
		this.drhphoneText = drhphoneText;
	}
}
