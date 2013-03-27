package ramo.klevis.openrental.forms;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;
import ramo.klevis.national.ibuisnesslogic.IAtomicChooserCar;
import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.national.ibuisnesslogic.ICustomerPiketLLogjic;
import ramo.klevis.national.ibuisnesslogic.IRentalDao;
import ramo.klevis.openrental.entity.Agent;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.Customer;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.iservice.ICheckOutConsumer;
import ramo.klevis.openrental.utils.CarImageHolder;
import ramo.klevis.openrental.utils.Money;
import ramo.klevis.openrental.utils.RentalStatus;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class FormCheckOut extends Composite {
	private DataBindingContext m_bindingContext;

	private List<String> money = new ArrayList<String>();
	ICustomerPiketLLogjic piketLLogic;
	// ICustomerPiketLLogic piketLLogic = new CustomerPiketLLogic();

	private Car carSelected;
	private Rental newRental = new Rental();
	private Customer customerSelected;

	private Text textMilage;
	private Text txtTotali;
	private Text txtGarancia;
	private Text txtNrditeve;
	private Text txtPagesaPerdite;

	private Button btnNewButton;

	private Button btnKontrata;
	private Scale scale;

	private Combo comboMoney;

	boolean rentalCall = false;

	List<Agent> listAgent = new ArrayList<Agent>();
	private ICheckOutConsumer checkOutConsumer;

	public void validate() {

	}

	public void fillFields() {
		listAgent = getCheckOutConsumer().getAgentDao().getAllAgents();
		addAgents();
	}

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @wbp.parser.constructor
	 */

	public FormCheckOut(final Composite parent, int style) {

		super(parent, 0);

		if (customerSelected == null) {
			customerSelected = new Customer();
		}
		// listAgent = agentDao.getAllAgents();
		// this.carSelected = seCar;
		// carImageHolder = new CarImageHolder(parent, 0);
		// carImageHolder.setBounds(720, 80, 216, 135);
		//
		// if (this.carSelected.getPic() != null) {
		//
		// if (carSelected.getPic().length > 0) {
		// InputStream in = new ByteArrayInputStream(
		// this.carSelected.getPic());
		// try {
		// BufferedImage bImageFromConvert = ImageIO.read(in);
		// String extent = carSelected.getExtent();
		// String string = "c:/prov/image." + extent;
		// if (extent != null) {
		// ImageIO.write(bImageFromConvert, extent, new File(
		// string));
		//
		// carImageHolder.addImage(string);
		// }
		//
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }
		// }
		// getFormMainDriver().setBounds(10, 197, 260, 245);

		Label lblKarburantiNeLitra = new Label(this, SWT.NONE);
		lblKarburantiNeLitra.setBounds(10, 31, 141, 19);
		lblKarburantiNeLitra.setText("Karburanti ne litra");

		Label lblOdometer = new Label(this, SWT.NONE);
		lblOdometer.setBounds(296, 31, 76, 19);
		lblOdometer.setText("Odometer");

		textMilage = new Text(this, SWT.BORDER);
		// text_4.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		//
		// });
		textMilage.setBounds(378, 31, 145, 19);
		if (carSelected == null) {
			carSelected = new Car();
		}
		textMilage.setText("" + carSelected.getMilage());
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);

		Label lblTotali = new Label(this, SWT.NONE);
		lblTotali.setBounds(418, 76, 33, 19);
		lblTotali.setText("Totali");

		txtTotali = new Text(this, SWT.BORDER);
		// txtTotali.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtTotali.setBounds(457, 73, 109, 19);

		Label lblGarancia = new Label(this, SWT.NONE);
		lblGarancia.setBounds(243, 106, 59, 16);
		lblGarancia.setText("Garancia");

		txtGarancia = new Text(this, SWT.BORDER);
		// text_6.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtGarancia.setBounds(321, 106, 109, 19);

		Label lblNrDiteve = new Label(this, SWT.NONE);
		lblNrDiteve.setBounds(10, 73, 71, 19);
		lblNrDiteve.setText("Nr Diteve");

		txtNrditeve = new Text(this, SWT.BORDER);

		// txtNrditeve.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtNrditeve.setBounds(87, 73, 76, 19);

		Label lblX = new Label(this, SWT.NONE);
		lblX.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblX.setBounds(169, 73, 12, 13);
		lblX.setText("X");

		Label lblPagesaPerDite = new Label(this, SWT.NONE);
		lblPagesaPerDite.setBounds(182, 73, 109, 19);
		lblPagesaPerDite.setText("Pagesa per dite");

		txtPagesaPerdite = new Text(this, SWT.BORDER);
		// txtPagesaPerdite.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtPagesaPerdite.setBounds(296, 73, 87, 19);

		ComboViewer comboViewer = new ComboViewer(this, SWT.NONE);
		comboMoney = comboViewer.getCombo();
		comboMoney.setBounds(586, 73, 129, 29);

		comboMoney.add(Money.DOLLAR.getType());
		comboMoney.add(Money.EURO.getType());
		comboMoney.add(Money.LEK.getType());

		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					int text2 = Integer.parseInt(txtNrditeve.getText());
					int text3 = Integer.parseInt(txtPagesaPerdite.getText()
							.replace(".", ""));

					String s = "" + (text2 * text3);
					txtTotali.setText(s);
				} catch (Exception e2) {

					NotifierDialog
							.notify("NJOFTIM",
									"Fushat NR DITEVE dhe PAGESA per dite duhet te jen numra",
									NotificationType.WARN);
					return;
				}

			}
		});
		button.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.BOLD));
		button.setBounds(392, 73, 20, 23);
		button.setText("=");
		comboMoney.select(1);

		setBtnNewButton(new Button(this, SWT.NONE));
		getBtnNewButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean openQuestion = MessageDialog.openQuestion(
						parent.getShell(), "Question", "Jeni te sigurt?");

				if (openQuestion == true) {

					getNewRental().setCar(carSelected);

					// customerSelected = getFormCustomer().getCustomer();
					// Customer customer = getFormMainDriver().getRental();

					System.err.println("pasport id "
							+ customerSelected.getDrpasportid());
					getNewRental().setLocation(
							FormAvaibleCar.beanAvaibleCar.getLocation());
					getNewRental().setCustomer(customerSelected);

					getNewRental().setStarttime(
							FormAvaibleCar.beanAvaibleCar.getFromDate());
					getNewRental().setRentdays(
							Integer.parseInt(txtNrditeve.getText()));
					getNewRental().setEndtime(
							FormAvaibleCar.beanAvaibleCar.getToDate());
					// getNewRental().setUsername(National.user.getUsername());
					getNewRental().setRentalStatus(RentalStatus.CHECK_OUT);

					getNewRental().setAmountpaid(getNewRental().getAmount());
					getNewRental().setGurantee(
							Integer.parseInt(txtGarancia.getText().replace(".",
									"")));

					String text2 = comboMoney.getText();
					String text3 = comboMoneyGurantee.getText();

					if (text2.equals(Money.DOLLAR.getType())) {
						getNewRental().setCurency("USD");

					} else if (text2.equals(Money.EURO.getType())) {

						getNewRental().setCurency("EUR");
					} else {

						getNewRental().setCurency("LEK");
					}

					if (text3.equals(Money.DOLLAR.getType())) {
						getNewRental().setCurencyg("USD");

					} else if (text3.equals(Money.EURO.getType())) {

						getNewRental().setCurencyg("EUR");
					} else {

						getNewRental().setCurencyg("LEK");
					}

					int selection = scale.getSelection();
					System.out.println("Selection " + selection);
					getNewRental().setKmout(selection);

					getNewRental().setInorout(
							btnBrendaShqiperise.getSelection());
					int selectionIndex = comboViewerAgents.getCombo()
							.getSelectionIndex();

					if (comboViewerAgents.getSelection().isEmpty() == false) {
						getNewRental().setAgents(listAgent.get(selectionIndex));

						getNewRental().setAgentpayed(false);
						getNewRental().setAgentpercent(
								listAgent.get(selectionIndex).getCommrate());

					} else {
						getNewRental().setAgents(null);
						getNewRental().setAgentpayed(false);
					}

					getNewRental().setKmout(
							Integer.parseInt(textMilage.getText()));
					int kmout = getNewRental().getKmout();

					System.out.println("Vlera e kilometrave eshte :" + kmout);
					getNewRental().getCar()
							.setMilage(getNewRental().getKmout());

					System.out.println(">>>>>>>>>>>>>>> "
							+ getNewRental().getCustomer().getDrlicnr() + "  "
							+ getNewRental().getCustomer().getDrfirstname());
					// getNewRental().getCustomer().setCardnr(text_3.getText());
					// getNewRental().getCustomer().setCardexp(text_5.getText());
					// getNewRental().setCardexp(text_5.getText());

					boolean controlCustomer = getCheckOutConsumer()
							.getCustomerDao().controlCustomer(
									getNewRental().getCustomer());

					if (controlCustomer) {
						boolean resultQ = MessageDialog.openQuestion(
								parent.getShell(), "Question",
								"Personi ndodhet ne BLACK LIST! Jeni te sigur qe doni te vazhdoni?");

						if (!resultQ) {
							return;
						}
					} else {

					}

					// if (text_3.isEnabled())
					// getNewRental().setCardnr(text_3.getText());
					// eshte bere comment sa te zgjidhet google api
					// new Thread(new Runnable() {
					//
					// @Override
					// public void run() {
					// // TODO Auto-generated method stub
					//
					// try {
					// CalendarSample.initConnection();
					// } catch (Exception e1) {
					// // TODO Auto-generated catch block
					// e1.printStackTrace();
					// }
					//
					// com.google.api.services.calendar.model.CalendarListEntry
					// calendar = null;
					// try {
					//
					// calendar = CalendarSample
					// .getCalendar("Today Rent Car");
					// } catch (IOException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					//
					// String description = ""
					// + newRental.getCustomer().getPafirstname()
					// + "\n"
					// + newRental.getCustomer().getPalastname()
					// + "\n Nr tel  "
					// + newRental.getCustomer().getDrhphone()
					// + "\nEmail "
					// + newRental.getCustomer().getDremail()
					// + "\n Nr diteve " + newRental.getRentdays();
					//
					// CalendarSample.addEvent(
					// calendar.getId(),
					// description,
					// new DateTime(new Date(), TimeZone
					// .getTimeZone("UTC")),
					// new DateTime(new Date(), TimeZone
					// .getTimeZone("UTC")), newRental
					// .getCar().getDescription());
					//
					// }
					// }).start();

					Rental llogAtCheckOut = piketLLogic
							.llogAtCheckOut(getNewRental());
					setNewRental(llogAtCheckOut);
					Rental registerRental = getCheckOutConsumer()
							.getRentalDao().registerRental(getNewRental());
					if (registerRental != null) {

						getNewRental().setId(registerRental.getId());
						// new CreatePdf().createPdf(registerRental);

						FormCheckOut.this.dispose();

						NotifierDialog.notify("Njoftim",
								"Regjistrimi me sukses",
								NotificationType.SUCCESS);
					} else {
						return;
					}
				} else {

				}

			}
		});
		getBtnNewButton()
				.setFont(
						SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD
								| SWT.ITALIC));
		getBtnNewButton().setBounds(10, 169, 191, 52);
		getBtnNewButton().setText("REGJISTRO");

		setBtnKontrata(new Button(this, SWT.NONE));
		getBtnKontrata().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// CreatePdf cr = new CreatePdf();
				// cr.createPdf(getNewRental());

			}
		});
		getBtnKontrata()
				.setFont(
						SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD
								| SWT.ITALIC));
		getBtnKontrata().setBounds(60, 684, 213, 25);
		getBtnKontrata().setText("Printo Kontraten");
		getBtnKontrata().setVisible(false);

		// m_bindingContext = initDataBindings();

		// txtGarancia.setText("" + carSelected.getDeposit());
		// textMilage.setText("" + carSelected.getMilage());
		scale = new Scale(this, SWT.NONE);
		scale.setPageIncrement(1);
		scale.setMaximum(6);
		scale.setBounds(157, 21, 121, 42);
		scale.setSelection(6);

		ComboViewer comboViewer_1 = new ComboViewer(this, SWT.NONE);
		comboMoneyGurantee = comboViewer_1.getCombo();
		comboMoneyGurantee.setBounds(457, 106, 156, 29);
		comboMoneyGurantee.add(Money.DOLLAR.getType());
		comboMoneyGurantee.add(Money.EURO.getType());
		comboMoneyGurantee.add(Money.LEK.getType());
		comboMoneyGurantee.select(1);

		btnBrendaShqiperise = new Button(this, SWT.CHECK);
		btnBrendaShqiperise.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnBrendaShqiperise.setBounds(532, 31, 121, 16);
		btnBrendaShqiperise.setText("Jashte Shqiperise");

		Label lblAgjentet = new Label(this, SWT.NONE);
		lblAgjentet.setBounds(10, 106, 42, 26);
		lblAgjentet.setText("Agjentet");
		comboViewerAgents = new ComboViewer(this, SWT.NONE);
		Combo combo = comboViewerAgents.getCombo();
		combo.setBounds(87, 106, 129, 29);
		setTabList(new Control[] { textMilage, txtTotali, txtGarancia,
				txtNrditeve, txtPagesaPerdite, comboMoney, button,
				btnNewButton, btnKontrata, scale });

		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(
				listContentProvider.getKnownElements(), Agent.class,
				"nameSuranme");
		comboViewerAgents.setLabelProvider(new ObservableMapLabelProvider(
				observeMap));
		comboViewerAgents.setContentProvider(listContentProvider);
		m_bindingContext = initDataBindings();
		//

	}

	private void addAgents() {
		IListProperty selfList = Properties.selfList(Agent.class);
		IObservableList observe = selfList.observe(listAgent);
		comboViewerAgents.setInput(observe);
	}

	// public void addCardCrdencials() {
	//
	// if (customerSelected != null) {
	//
	// String cardnr = customerSelected.getCardnr();
	// if (cardnr != null)
	// text_3.setText(cardnr);
	// String cardexp = customerSelected.getCardexp();
	// if (cardexp != null)
	// text_5.setText(cardexp);
	// }
	// }
	//
	// public void addCardCrdencials(Customer c) {
	//
	// if (c != null) {
	//
	// String cardnr = c.getCardnr();
	// if (cardnr != null)
	// text_3.setText(cardnr);
	// String cardexp = c.getCardexp();
	// if (cardexp != null)
	// text_5.setText(cardexp);
	// }
	// }

	private void definePayForDay(Car car) {

		int rentdays = car.getNrditeve();

		if (rentdays >= 1 && rentdays <= 2) {

			carSelected.setPayForDay("" + carSelected.get_drate2());
		} else if (rentdays >= 3 && rentdays <= 6) {

			carSelected.setPayForDay("" + carSelected.get_drate3());

		} else if (rentdays >= 7 && rentdays <= 11) {

			carSelected.setPayForDay("" + carSelected.get_drate4());
		} else if (rentdays >= 12 && rentdays <= 25) {

			carSelected.setPayForDay("" + carSelected.get_drate5());

		} else if (rentdays >= 26 && rentdays <= 35) {

			carSelected.setPayForDay("" + carSelected.get_drate6());

		}

		String payForDay = carSelected.getPayForDay();
		System.err.println(payForDay);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setCarSelected(Car carSelected) {
		this.carSelected = carSelected;

		if(carSelected!=null)
		definePayForDay(carSelected);
		fillFields();
		initDataBindings();

	}

	public Car getCarSelected() {
		return carSelected;
	}

	public void setM_bindingContext(DataBindingContext m_bindingContext) {
		this.m_bindingContext = m_bindingContext;
	}

	public DataBindingContext getM_bindingContext() {
		return m_bindingContext;
	}

	public void setFormCustomer(FormCustomer formCustomer) {
	}

	public void setNewRental(Rental newRental) {
		this.newRental = newRental;
	}

	public Rental getNewRental() {
		return newRental;
	}

	public void setCustomerSelected(Customer customerSelected) {
		this.customerSelected = customerSelected;
	}

	public Customer getCustomerSelected() {
		return customerSelected;
	}

	public void setBtnNewButton(Button btnNewButton) {
		this.btnNewButton = btnNewButton;
		btnNewButton.setImage(ResourceManager.getPluginImage(
				"ramo.klevis.openrental", "icons/rentcar.png"));
	}

	public Button getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnKontrata(Button btnKontrata) {
		this.btnKontrata = btnKontrata;
	}

	public Button getBtnKontrata() {
		return btnKontrata;
	}

	IValidator iValidator = new IValidator() {

		@Override
		public IStatus validate(Object arg0) {
			// TODO Auto-generated method stub
			String s = (String) arg0;

			if (s.matches("\\d*"))
				return ValidationStatus.ok();
			if (s.contains(".")) {
				System.out.println("ok");
				return ValidationStatus.ok();

			}
			return ValidationStatus.error("Nuk eshte numer");
		}
	};

	private Combo comboMoneyGurantee;

	private Button btnBrendaShqiperise;

	private CarImageHolder carImageHolder;

	private ComboViewer comboViewerAgents;

	public void addValidatorNumber(IObservableValue v, IObservableValue v2,
			DataBindingContext binding) {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		updateValueStrategy.setAfterGetValidator(iValidator);
		Binding bindValue = binding.bindValue(v, v2, updateValueStrategy, null);

		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

		// carImageHolder.dispose();
		super.dispose();

	}

	public ICheckOutConsumer getCheckOutConsumer() {
		return checkOutConsumer;
	}

	public void setCheckOutConsumer(ICheckOutConsumer checkOutConsumer) {
		this.checkOutConsumer = checkOutConsumer;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue text_4ObserveTextObserveWidget = SWTObservables
				.observeText(textMilage, SWT.Modify);
		IObservableValue newRentalKmoutObserveValue = PojoObservables
				.observeValue(newRental, "kmout");
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		bindingContext.bindValue(text_4ObserveTextObserveWidget,
				newRentalKmoutObserveValue, strategy, null);
		//
		IObservableValue observeTextTxtNrditeveObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(txtNrditeve);
		IObservableValue nrditeveCarSelectedObserveValue = PojoObservables
				.observeValue(carSelected, "nrditeve");
		bindingContext.bindValue(observeTextTxtNrditeveObserveWidget,
				nrditeveCarSelectedObserveValue, null, null);
		//
		IObservableValue observeTextTxtPagesaPerditeObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(txtPagesaPerdite);
		IObservableValue payForDayCarSelectedObserveValue = PojoObservables
				.observeValue(carSelected, "payForDay");
		bindingContext.bindValue(observeTextTxtPagesaPerditeObserveWidget,
				payForDayCarSelectedObserveValue, null, null);
		//
		IObservableValue observeTextTxtGaranciaObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(txtGarancia);
		IObservableValue depositCarSelectedObserveValue = PojoObservables
				.observeValue(carSelected, "deposit");
		bindingContext.bindValue(observeTextTxtGaranciaObserveWidget,
				depositCarSelectedObserveValue, null, null);
		//
		IObservableValue observeTextTextMilageObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(textMilage);
		IObservableValue milageCarSelectedObserveValue = PojoObservables
				.observeValue(carSelected, "milage");
		bindingContext.bindValue(observeTextTextMilageObserveWidget,
				milageCarSelectedObserveValue, null, null);
		//
		return bindingContext;
	}
}
