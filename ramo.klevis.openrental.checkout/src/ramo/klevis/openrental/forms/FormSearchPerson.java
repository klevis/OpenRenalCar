package ramo.klevis.openrental.forms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import ramo.klevis.national.beans.PersonSearch;
import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.openrental.entity.Customer;

public class FormSearchPerson extends Composite {
	private DataBindingContext m_bindingContext;

	private PersonSearch personSearch = new PersonSearch();
	private Text text;
	private Text text_1;
	private Table table;
	public Customer customerSelected = new Customer();
	private ICustomerDao customerDao;

	List<Customer> listCustomer = new ArrayList<Customer>();
	private TableViewer tableViewer;

	@Override
	protected void checkSubclass() {
		// TODO Auto-generated method stub
	}

	// FormBlackList formBlackList;

	/**
	 * @wbp.parser.constructor
	 */
	@SuppressWarnings("restriction")
	// public FormSearchPerson(Shell shell, final FormBlackList formBlackList) {
	// // TODO Auto-generated constructor stub
	// super(shell, SWT.CLOSE | SWT.APPLICATION_MODAL);
	// setText("Zgjidh personin");
	// setSize(734, 300);
	// this.formBlackList = formBlackList;
	//
	// Label lblEmri = new Label(this, SWT.NONE);
	// lblEmri.setBounds(0, 10, 41, 15);
	// lblEmri.setText("Emri");
	//
	// text = new Text(this, SWT.BORDER);
	// text.setBounds(44, 10, 121, 21);
	//
	// Label lblMbiemri = new Label(this, SWT.NONE);
	// lblMbiemri.setBounds(191, 10, 55, 15);
	// lblMbiemri.setText("Mbiemri");
	//
	// text_1 = new Text(this, SWT.BORDER);
	// text_1.setBounds(261, 10, 121, 21);
	//
	// Button btnKerko = new Button(this, SWT.NONE);
	// btnKerko.setImage(SWTResourceManager.getImage(FormSearchPerson.class,
	// "/image/cearch.png"));
	// btnKerko.addSelectionListener(new SelectionAdapter() {
	//
	// @Override
	// public void widgetSelected(SelectionEvent e) {
	// listCustomer = customerDao.searchCustomer(getPersonSearch());
	//
	// if (listCustomer != null) {
	// WritableList writableList = new WritableList(listCustomer,
	// Customer.class);
	// tableViewer.setInput(writableList);
	// }
	//
	// }
	// });
	// btnKerko.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
	// btnKerko.setBounds(0, 46, 90, 25);
	// btnKerko.setText("Kerko");
	//
	// tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
	// table = tableViewer.getTable();
	// table.setLinesVisible(true);
	// table.setHeaderVisible(true);
	// table.setBounds(0, 77, 718, 135);
	//
	// TableViewerColumn tableViewerColumn = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnEmri = tableViewerColumn.getColumn();
	// tblclmnEmri.setWidth(100);
	// tblclmnEmri.setText("Emri");
	//
	// TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnMbiemri = tableViewerColumn_1.getColumn();
	// tblclmnMbiemri.setWidth(100);
	// tblclmnMbiemri.setText("Mbiemri");
	//
	// TableViewerColumn tableViewerColumn_7 = new
	// TableViewerColumn(tableViewer, SWT.NONE);
	// TableColumn tblclmnNrPikeve = tableViewerColumn_7.getColumn();
	// tblclmnNrPikeve.setWidth(100);
	// tblclmnNrPikeve.setText("Nr pikeve");
	//
	// TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
	// tblclmnNewColumn.setWidth(100);
	// tblclmnNewColumn.setText("Adresa");
	//
	// TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnPasaportNo = tableViewerColumn_3.getColumn();
	// tblclmnPasaportNo.setWidth(100);
	// tblclmnPasaportNo.setText("Tel");
	//
	// TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnPasaportNo_1 = tableViewerColumn_4.getColumn();
	// tblclmnPasaportNo_1.setWidth(100);
	// tblclmnPasaportNo_1.setText("Pasaport No");
	//
	// TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnShteti = tableViewerColumn_5.getColumn();
	// tblclmnShteti.setWidth(100);
	// tblclmnShteti.setText("Shteti");
	//
	// TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(
	// tableViewer, SWT.NONE);
	// TableColumn tblclmnQyteti = tableViewerColumn_6.getColumn();
	// tblclmnQyteti.setWidth(100);
	// tblclmnQyteti.setText("Qyteti");
	//
	// Button btnZgjidhKlientin = new Button(this, SWT.NONE);
	// btnZgjidhKlientin.setImage(SWTResourceManager.getImage(
	// FormSearchPerson.class, "/image/Sign-Select-icon.png"));
	// btnZgjidhKlientin.addSelectionListener(new SelectionAdapter() {
	// @Override
	// public void widgetSelected(SelectionEvent e) {
	//
	// int selectionIndex = table.getSelectionIndex();
	//
	// ISelection selection = tableViewer.getSelection();
	//
	// if (selection.isEmpty() == false) {
	// customerSelected = (Customer) tableViewer
	// .getElementAt(selectionIndex);
	//
	// FormCustomer formCustomer = formBlackList.getFormCustomer();
	// formCustomer.dispose();
	// // formBlackList.getFormAdditionalDirver().dispose();
	// // formBlackList.getFormMainDriver().dispose();
	// if (FormSearchPerson.this.isDisposed() == false) {
	// FormSearchPerson.this.dispose();
	// }
	// setSelected(true);
	// } else {
	// setSelected(false);
	// }
	//
	// }
	// });
	// btnZgjidhKlientin.setFont(SWTResourceManager.getFont("Segoe UI", 10,
	// SWT.BOLD | SWT.ITALIC));
	// btnZgjidhKlientin.setBounds(0, 218, 159, 40);
	// btnZgjidhKlientin.setText("Zgjidh Klientin");
	// m_bindingContext = initDataBindings();
	//
	// }
	public void clear() {
		listCustomer.clear();
		personSearch = new PersonSearch();
		initDataBindings();
	}

	private IEventBroker eventBroker;

	public FormSearchPerson(Composite parent, int i) {
		// TODO Auto-generated constructor stub
		super(parent, i);
		setLayout(null);

		Label lblEmri = new Label(this, SWT.NONE);
		lblEmri.setBounds(0, 10, 41, 15);
		lblEmri.setText("Emri");

		text = new Text(this, SWT.BORDER);
		text.setBounds(44, 10, 121, 21);

		Label lblMbiemri = new Label(this, SWT.NONE);
		lblMbiemri.setBounds(191, 10, 55, 15);
		lblMbiemri.setText("Mbiemri");

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(261, 10, 121, 21);

		Button btnKerko = new Button(this, SWT.NONE);
		btnKerko.setBounds(0, 46, 90, 25);
		btnKerko.setImage(ResourceManager.getPluginImage(
				"ramo.klevis.openrental", "/image/cearch.png"));
		btnKerko.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				listCustomer = getCustomerDao().searchCustomer(
						getPersonSearch());

				System.err.println("Kerkoi " + listCustomer);
				if (listCustomer != null) {
					WritableList writableList = new WritableList(listCustomer,
							Customer.class);
					tableViewer.setInput(writableList);
				}

			}
		});
		btnKerko.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		btnKerko.setText("Kerko");

		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(0, 77, 801, 135);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnEmri = tableViewerColumn.getColumn();
		tblclmnEmri.setWidth(100);
		tblclmnEmri.setText("Emri");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnMbiemri = tableViewerColumn_1.getColumn();
		tblclmnMbiemri.setWidth(100);
		tblclmnMbiemri.setText("Mbiemri");

		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNrPikeve = tableViewerColumn_7.getColumn();
		tblclmnNrPikeve.setWidth(100);
		tblclmnNrPikeve.setText("Nr pikeve");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Adresa");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnPasaportNo = tableViewerColumn_3.getColumn();
		tblclmnPasaportNo.setWidth(100);
		tblclmnPasaportNo.setText("Tel");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnPasaportNo_1 = tableViewerColumn_4.getColumn();
		tblclmnPasaportNo_1.setWidth(100);
		tblclmnPasaportNo_1.setText("Pasaport No");

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnShteti = tableViewerColumn_5.getColumn();
		tblclmnShteti.setWidth(100);
		tblclmnShteti.setText("Shteti");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnQyteti = tableViewerColumn_6.getColumn();
		tblclmnQyteti.setWidth(100);
		tblclmnQyteti.setText("Qyteti");

		Button btnZgjidhKlientin = new Button(this, SWT.NONE);
		btnZgjidhKlientin.setBounds(0, 218, 190, 45);
		btnZgjidhKlientin.setImage(ResourceManager.getPluginImage(
				"ramo.klevis.openrental", "/image/Sign-Select-icon.png"));
		btnZgjidhKlientin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int selectionIndex = table.getSelectionIndex();

				ISelection selection = tableViewer.getSelection();

				if (selection.isEmpty() == false) {
					customerSelected = (Customer) tableViewer
							.getElementAt(selectionIndex);

					eventBroker.send("CustomerSelected", customerSelected);
					setSelected(true);
				} else {
					setSelected(false);
				}

			}
		});
		btnZgjidhKlientin.setFont(SWTResourceManager.getFont("Segoe UI", 10,
				SWT.BOLD | SWT.ITALIC));
		btnZgjidhKlientin.setText("Zgjidh Klientin");
		m_bindingContext = initDataBindings();

	}

	private boolean selected = false;

	public void setPersonSearch(PersonSearch personSearch) {
		this.personSearch = personSearch;
	}

	public PersonSearch getPersonSearch() {
		return personSearch;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
				listContentProvider.getKnownElements(), Customer.class,
				new String[] { "pafirstname", "palastname", "nrPikeve",
						"draddress", "drhphone", "drpasportid", "drcountry",
						"drcity" });
		tableViewer
				.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		WritableList writableList = new WritableList(listCustomer,
				Customer.class);
		tableViewer.setInput(writableList);
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables
				.observeText(text, SWT.Modify);
		IObservableValue personSearchNameObserveValue = PojoObservables
				.observeValue(getPersonSearch(), "name");
		bindingContext.bindValue(textObserveTextObserveWidget,
				personSearchNameObserveValue, null, null);
		//
		IObservableValue text_1ObserveTextObserveWidget = SWTObservables
				.observeText(text_1, SWT.Modify);
		IObservableValue personSearchSurnameObserveValue = PojoObservables
				.observeValue(getPersonSearch(), "surname");
		bindingContext.bindValue(text_1ObserveTextObserveWidget,
				personSearchSurnameObserveValue, null, null);
		//
		return bindingContext;
	}

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public IEventBroker getEventBroker() {
		return eventBroker;
	}

	public void setEventBroker(IEventBroker eventBroker) {
		this.eventBroker = eventBroker;
	}
}
