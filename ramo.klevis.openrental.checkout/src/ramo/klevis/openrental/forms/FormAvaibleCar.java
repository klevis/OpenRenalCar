package ramo.klevis.openrental.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.Class;
import ramo.klevis.openrental.entity.Location;
import ramo.klevis.openrental.iservice.IFreeCarsConsumer;
import org.eclipse.wb.swt.ResourceManager;

public class FormAvaibleCar extends Composite {

	private DataBindingContext m_bindingContext;
	public static ramo.klevis.national.beans.BeanAvaibleCar beanAvaibleCar = new ramo.klevis.national.beans.BeanAvaibleCar();
	private DateTime fromDateText;
	private DateTime toDateText;
	private FormCheckOut formCheckOut;
	List<ramo.klevis.openrental.entity.Class> itemClass = new ArrayList<ramo.klevis.openrental.entity.Class>();

	private List<Car> listCarsAvaible = new ArrayList<Car>();
	List<Location> itemLocation = new ArrayList<Location>();

	private Car selectedCar;

	private Label lblLocation;
	private Combo selectClazz;
	private ComboViewer comboViewer;
	private Combo selectLocation;
	private ComboViewer comboViewer_1;

	private Button buttonFreeCar;
	private Table table;
	private TableViewer tableViewer;
	private TableColumn tblclmnId;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tblclmnSfsdafsd;
	private TableViewerColumn tableViewerColumn_1;
	private TableColumn tblclmnNewColumn;
	private TableViewerColumn tableViewerColumn_2;
	private TableColumn tblclmnModel;
	private TableViewerColumn tableViewerColumn_3;
	private TableColumn tblclmnCmimi;
	private TableViewerColumn tableViewerColumn_4;
	private TableColumn tblclmnViti;
	private TableViewerColumn tableViewerColumn_5;
	private TableColumn tblclmnKlassi;
	private TableViewerColumn tableViewerColumn_6;
	private DateTime fromTime;
	private DateTime toTime;
	private Spinner spinnerNrDay;

	Shell shell;

	/**
	 * @wbp.parser.constructor
	 */
	// public FormAvaibleCar(Shell parent, int style,
	// ramo.klevis.national.beans.BeanAvaibleCar newBeanAvaibleCar) {
	// this(parent, style, classDao);
	// setText("Zgjidh Makinen");
	// setSize(650, 367);
	//
	// setBeanAvaibleCar(newBeanAvaibleCar);
	// itemLocation = locationDao.getAllLocation();
	//
	// }

	IEventBroker eventBroker;

	public void setEventBroker(IEventBroker eventBroker) {
		this.eventBroker = eventBroker;
	}

	private IFreeCarsConsumer freeCarsConsumer;

	public void setFreeCarConsumer(IFreeCarsConsumer freeCarsConsumer) {

		this.freeCarsConsumer = freeCarsConsumer;

	}

	public void fillFileds() {
		itemLocation = freeCarsConsumer.getLocationDao().getAllLocation();
		itemClass = freeCarsConsumer.getClassDao().getAllClasses();

		fillClassAndLocation();

	}

	public FormAvaibleCar(final Composite parent, int style) {

		super(parent, SWT.CLOSE | SWT.APPLICATION_MODAL);
		this.setSize(422, 369);
		setLayout(null);

		Label lblNgaData = new Label(this, SWT.NONE);
		lblNgaData.setBounds(5, 9, 55, 15);
		lblNgaData.setText("Nga data");

		fromDateText = new DateTime(this, SWT.DROP_DOWN | SWT.DATE);
		fromDateText.setBounds(65, 5, 208, 24);

		Label lblTekData = new Label(this, SWT.NONE);
		lblTekData.setBounds(5, 38, 41, 15);
		lblTekData.setText("Tek data");

		toDateText = new DateTime(this, SWT.DROP_DOWN);
		toDateText.setBounds(65, 34, 208, 24);

		Label lblClass = new Label(this, SWT.NONE);
		lblClass.setBounds(33, 68, 27, 15);
		lblClass.setText("Class");

		comboViewer = new ComboViewer(this, SWT.NONE);
		selectClazz = comboViewer.getCombo();
		selectClazz.setBounds(65, 64, 208, 23);

		buttonFreeCar = new Button(this, SWT.NONE);
		buttonFreeCar.setImage(ResourceManager.getPluginImage("ramo.klevis.openrental", "icons/Car Search.png"));
		buttonFreeCar.setFont(SWTResourceManager.getFont("Segoe UI", 10,
				SWT.BOLD | SWT.ITALIC));
		buttonFreeCar.setBounds(278, 64, 208, 48);
		buttonFreeCar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int hours = toTime.getHours();
				int minutes = toTime.getMinutes();
				int seconds = toTime.getSeconds();
				beanAvaibleCar.getToDate().setHours(hours);
				beanAvaibleCar.getToDate().setMinutes(minutes);
				beanAvaibleCar.getToDate().setSeconds(seconds);

				int fromhours = fromTime.getHours();
				int fromminutes = fromTime.getMinutes();
				int fromseconds = fromTime.getSeconds();

				beanAvaibleCar.getFromDate().setHours(fromhours);
				beanAvaibleCar.getFromDate().setMinutes(fromminutes);
				beanAvaibleCar.getFromDate().setSeconds(fromseconds);

				System.out.println(beanAvaibleCar.getFromDate().getDay());
				// nr i idteve
				long milliseconds1 = beanAvaibleCar.getFromDate().getTime();
				long milliseconds2 = beanAvaibleCar.getToDate().getTime();
				long diff = milliseconds2 - milliseconds1;
				long diffSeconds = diff / 1000;
				long diffMinutes = diff / (60 * 1000);
				long diffHours = diff / (60 * 60 * 1000);
				long diffDays = diff / (24 * 60 * 60 * 1000);

				String s = "" + diffDays;

				getListCarsAvaible().clear();
				setListCarsAvaible(freeCarsConsumer.getFreedao()
						.searchForFreeCars(beanAvaibleCar));

				WritableList writableList_2 = new WritableList(
						getListCarsAvaible(), Car.class);

				spinnerNrDay.setSelection(Integer.parseInt(s));
				tableViewer.setInput(writableList_2);

				if (getListCarsAvaible() != null) {
					if (getListCarsAvaible().isEmpty()) {

						NotifierDialog.notify("Njoftim",
								"Nuk ka asnje makine te lire ne keto data",
								NotificationType.WARN);
					}
				} else {

					NotifierDialog.notify("Njoftim",
							"Nuk ka asnje makine te lire ne keto data",
							NotificationType.WARN);
				}

			}
		});
		buttonFreeCar.setText("Merr makinat e lira");

		lblLocation = new Label(this, SWT.NONE);
		lblLocation.setBounds(14, 97, 46, 15);
		lblLocation.setText("Location");

		comboViewer_1 = new ComboViewer(this, SWT.NONE);
		selectLocation = comboViewer_1.getCombo();
		selectLocation.setEnabled(false);
		selectLocation.setBounds(65, 93, 208, 23);

		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(0, 144, 643, 146);

		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnId = tableViewerColumn.getColumn();
		tblclmnId.setWidth(85);
		tblclmnId.setText("Nr");

		tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnSfsdafsd = tableViewerColumn_1.getColumn();
		tblclmnSfsdafsd.setWidth(100);
		tblclmnSfsdafsd.setText("License");

		tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Marka");

		tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnModel = tableViewerColumn_3.getColumn();
		tblclmnModel.setWidth(91);
		tblclmnModel.setText("Model");

		tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnCmimi = tableViewerColumn_4.getColumn();
		tblclmnCmimi.setWidth(84);
		tblclmnCmimi.setText("Cmimi");

		tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnViti = tableViewerColumn_5.getColumn();
		tblclmnViti.setWidth(81);
		tblclmnViti.setText("Viti");

		tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnKlassi = tableViewerColumn_6.getColumn();
		tblclmnKlassi.setWidth(98);
		tblclmnKlassi.setText("Klassi");

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setImage(ResourceManager.getPluginImage("ramo.klevis.openrental", "icons/Symbol_OK.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				ISelection selection = tableViewer.getSelection();

				

				Car elementAt = (Car) tableViewer.getElementAt(table
						.getSelectionIndex());

				if (selection.isEmpty() == false) {

					elementAt.setNrditeve(spinnerNrDay.getSelection());
					// setFormCheckOut(new FormCheckOut(shell, 0, (Car)
					// elementAt,null));

//					getFormCheckOut().setBounds(10, 80, 700, 610);

//					shell.layout();

					boolean send = eventBroker.send("CarSelected", elementAt);
					System.err.println("Send or not "+send);
					
					// FormAvaibleCar.this.close();

				} else {
					NotifierDialog.notify("Njoftim",
							"Nuk keni zgjedhur asnje makine",
							NotificationType.WARN);
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 11,
				SWT.BOLD | SWT.ITALIC));
		btnNewButton.setBounds(5, 294, 217, 41);
		btnNewButton.setText("Zgjidh makinen");

		fromTime = new DateTime(this, SWT.TIME);
		fromTime.setBounds(279, 5, 95, 24);

		toTime = new DateTime(this, SWT.TIME);
		toTime.setBounds(279, 29, 95, 24);

		spinnerNrDay = new Spinner(this, SWT.BORDER);
		spinnerNrDay.setBounds(395, 6, 47, 22);

		if (beanAvaibleCar != null) {
			m_bindingContext = initDataBindings();

			selectLocation.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub

					ISelection selection = comboViewer_1.getSelection();

				}

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub

				}
			});

		}
		Date date = new Date();
		beanAvaibleCar.setFromDate(date);
		fromDateText.setData(date);
		fromTime.setTime(date.getHours(), date.getMinutes(), date.getSeconds());
		// UtilDate utilDate = new UtilDate(beanAvaibleCar.getFromDate(), date);

		beanAvaibleCar.setToDate(date);
		fromDateText.setData(date);
		toTime.setTime(date.getHours(), date.getMinutes(),
				date.getSeconds() + 1);
		// toTime.setTime(beanAvaibleCar.getToDate().getHours(), beanAvaibleCar
		// .getToDate().getMinutes(), beanAvaibleCar.getToDate()
		// .getSeconds());

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ramo.klevis.national.beans.BeanAvaibleCar getBeanAvaibleCar() {
		return beanAvaibleCar;
	}

	public void setBeanAvaibleCar(
			ramo.klevis.national.beans.BeanAvaibleCar newBeanAvaibleCar) {
		setBeanAvaibleCar(newBeanAvaibleCar, true);
	}

	public void setBeanAvaibleCar(
			ramo.klevis.national.beans.BeanAvaibleCar newBeanAvaibleCar,
			boolean update) {
		beanAvaibleCar = newBeanAvaibleCar;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (beanAvaibleCar != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}

	public void setListCarsAvaible(List<Car> listCarsAvaible) {
		this.listCarsAvaible = listCarsAvaible;
	}

	public List<Car> getListCarsAvaible() {
		return listCarsAvaible;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public FormCheckOut getFormCheckOut() {
		return formCheckOut;
	}

	public void setFormCheckOut(FormCheckOut formCheckOut) {
		this.formCheckOut = formCheckOut;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		fillClassAndLocation();
		//
		IObservableValue comboViewerObserveSingleSelection = ViewersObservables
				.observeSingleSelection(comboViewer);
		IObservableValue beanAvaibleCarClazzObserveValue = PojoObservables
				.observeValue(beanAvaibleCar, "clazz");
		bindingContext.bindValue(comboViewerObserveSingleSelection,
				beanAvaibleCarClazzObserveValue, null, null);
		//
		IObservableValue comboViewer_1ObserveSingleSelection = ViewersObservables
				.observeSingleSelection(comboViewer_1);
		IObservableValue beanAvaibleCarLocationObserveValue = PojoObservables
				.observeValue(beanAvaibleCar, "location");
		bindingContext.bindValue(comboViewer_1ObserveSingleSelection,
				beanAvaibleCarLocationObserveValue, null, null);
		//
		IObservableValue fromDateTextObserveSelectionObserveWidget = SWTObservables
				.observeSelection(fromDateText);
		IObservableValue beanAvaibleCarFromDateObserveValue = PojoObservables
				.observeValue(beanAvaibleCar, "fromDate");
		bindingContext.bindValue(fromDateTextObserveSelectionObserveWidget,
				beanAvaibleCarFromDateObserveValue, null, null);
		//
		IObservableValue toDateTextObserveSelectionObserveWidget = SWTObservables
				.observeSelection(toDateText);
		IObservableValue beanAvaibleCarToDateObserveValue = PojoObservables
				.observeValue(beanAvaibleCar, "toDate");
		bindingContext.bindValue(toDateTextObserveSelectionObserveWidget,
				beanAvaibleCarToDateObserveValue, null, null);
		//
		ObservableListContentProvider listContentProvider_2 = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider_2);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
				listContentProvider_2.getKnownElements(), Car.class,
				new String[] { "id", "license", "make", "model", "rate",
						"year", "classes.class_" });
		tableViewer
				.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableList writableList_2 = new WritableList(getListCarsAvaible(),
				Car.class);
		tableViewer.setInput(writableList_2);
		//
		return bindingContext;
	}

	private void fillClassAndLocation() {
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		comboViewer.setContentProvider(listContentProvider);
		//
		IObservableMap observeMap = PojoObservables.observeMap(
				listContentProvider.getKnownElements(), Class.class, "class_");
		comboViewer
				.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		//
		WritableList writableList = new WritableList(itemClass, Class.class);
		comboViewer.setInput(writableList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		comboViewer_1.setContentProvider(listContentProvider_1);
		//
		IObservableMap observeMap_1 = PojoObservables
				.observeMap(listContentProvider_1.getKnownElements(),
						Location.class, "loc");
		comboViewer_1.setLabelProvider(new ObservableMapLabelProvider(
				observeMap_1));
		//
		WritableList writableList_1 = new WritableList(itemLocation,
				Location.class);
		comboViewer_1.setInput(writableList_1);
	}
}
