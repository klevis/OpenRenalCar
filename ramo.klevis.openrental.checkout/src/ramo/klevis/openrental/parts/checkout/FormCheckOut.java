package ramo.klevis.openrental.parts.checkout;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import ramo.klevis.openrental.utils.CarImageHolder;
import ramo.klevis.openrental.utils.Money;
import ramo.klevis.openrental.utils.RentalStatus;

public class FormCheckOut extends Composite {
	private DataBindingContext m_bindingContext;

	private List<String> money = new ArrayList<String>();

	private Label text;
	private Label text_1;
	private Label text_2;
	ICustomerPiketLLogjic piketLLogic;
	// ICustomerPiketLLogic piketLLogic = new CustomerPiketLLogic();

	private Car carSelected;
	private FormSearchPerson formSearchPerson;
	private Rental newRental = new Rental();
	private FormCustomer formCustomer;
	private Customer customerSelected;

	private FormMainDriver formMainDriver;
	private FormAdditionalDirver formAdditionalDirver;
	private Text text_4;
	private Text txtTotali;
	private Text txtGarancia;
	private Text txtNrditeve;
	private Text txtPagesaPerdite;
	private IRentalDao rentalDao;

	private Button btnNewButton;

	private Button btnKontrata;
	private Scale scale;

	private Combo comboMoney;
	private ICustomerDao customerDao;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @wbp.parser.constructor
	 */

	boolean rentalCall = false;

	List<Agent> listAgent = new ArrayList<Agent>();

	/**
	 * @wbp.parser.constructor
	 */

	private IAgentDao agentDao;

	/**
	 * @wbp.parser.constructor
	 */

	public FormCheckOut(Rental ren, Composite parent) {
		// TODO Auto-generated constructor stub

		this(parent, 0, ren.getCar());

		Car car = ren.getCar();

		if (car.getNrditeve() == 0) {
			car.setNrditeve(ren.getRentdays());
		}

		customerSelected = ren.getCustomer();
		if (ren.getRentalStatus() == RentalStatus.REZERVATION) {

			customerSelected = new Customer();
		}
		carImageHolder.setBounds(730, 390, 216, 135);
		setNewRental(ren);
		scale.setSelection(getNewRental().getKmout());
		formMainDriver.setRental(customerSelected, true);
		formAdditionalDirver.setRental(getNewRental(), true);
		formCustomer.setCustomer(customerSelected, true);

		if (ren.getRentalStatus() == RentalStatus.REZERVATION) {
			formCustomer.getPafirstnameText().setText(ren.getPafirstname());
			formCustomer.getPalastnameText().setText(ren.getPalastname());
			formCustomer.getDrhphoneText().setText(ren.getDrhphone());
			String cardnr = ren.getCardnr();
			if (cardnr != null)
				text_3.setText(cardnr);
			String cardexp = ren.getCardexp();
			if (cardexp != null)
				text_5.setText(cardexp);

		} else {

			// this.setEnabled(false);
		}

		initDataBindings();

		definePayForDay();
		btnBrendaShqiperise.setSelection(getNewRental().isInorout());

	}

	public void validate() {

	}

	public FormCheckOut(final Composite parent, int style, Car seCar,
			IAgentDao agentDao) {

		super(parent, SWT.BORDER);
		setAgentDao(agentDao);

		if (customerSelected == null) {
			customerSelected = new Customer();
		}
		listAgent = agentDao.getAllAgents();
		this.carSelected = seCar;
		carImageHolder = new CarImageHolder(parent, 0);
		carImageHolder.setBounds(720, 80, 216, 135);

		if (this.carSelected.getPic() != null) {

			if (carSelected.getPic().length > 0) {
				InputStream in = new ByteArrayInputStream(
						this.carSelected.getPic());
				try {
					BufferedImage bImageFromConvert = ImageIO.read(in);
					String extent = carSelected.getExtent();
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

		Label lblKlassi = new Label(this, SWT.NONE);
		lblKlassi.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblKlassi.setBounds(10, 3, 49, 13);
		lblKlassi.setText("Klassi :");

		Label lblRegNo = new Label(this, SWT.NONE);
		lblRegNo.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblRegNo.setBounds(183, 3, 49, 13);
		lblRegNo.setText("Reg No :");

		Label lblDetaje = new Label(this, SWT.NONE);
		lblDetaje.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblDetaje.setBounds(361, 3, 49, 13);
		lblDetaje.setText("Detaje:");

		text = new Label(this, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		text.setBounds(65, 3, 112, 19);

		text_1 = new Label(this, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		text_1.setBounds(238, 3, 109, 19);

		text_2 = new Label(this, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		text_2.setBounds(416, 3, 112, 19);

		Button btnClientSearch = new Button(this, SWT.NONE);
		btnClientSearch.setImage(SWTResourceManager.getImage(
				FormCheckOut.class, "/image/cearch.png"));

		btnClientSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				getFormCustomer().dispose();
				getFormMainDriver().dispose();
				setFormSearchPerson(new FormSearchPerson(parent.getShell(),
						FormCheckOut.this));

				getFormSearchPerson().addDisposeListener(new DisposeListener() {

					@Override
					public void widgetDisposed(DisposeEvent arg0) {
						// TODO Auto-generated method stub

						setCustomerSelected(getFormSearchPerson().customerSelected);

						System.out.println(getCustomerSelected()
								.getPafirstname());
						setFormCustomer(new FormCustomer(FormCheckOut.this,
								SWT.NONE, getCustomerSelected()));
						setFormMainDriver(new FormMainDriver(FormCheckOut.this,
								SWT.NONE, getCustomerSelected()));

						getFormMainDriver().setBounds(10, 197, 260, 245);
						setFormAdditionalDirver(new FormAdditionalDirver(
								FormCheckOut.this, SWT.NONE, getNewRental()));

						addCardCrdencials();

						getFormAdditionalDirver().setBounds(313, 197, 260, 269);
						getFormCustomer().setBounds(0, 66, 665, 89);

					}
				});
				getFormSearchPerson().open();
				getFormSearchPerson().setBounds(100, 100, 600, 600);
			}
		});
		btnClientSearch.setBounds(0, 35, 33, 25);
		btnClientSearch.setText("...");

		setFormCustomer(new FormCustomer(this, SWT.NONE));
		getFormCustomer().setBounds(0, 66, 687, 89);

		addCardCrdencials();

		Label label = new Label(this, SWT.BORDER | SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setBounds(10, 161, 677, 2);

		Label label_1 = new Label(this, SWT.BORDER | SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setBounds(0, 27, 677, 2);

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD
				| SWT.ITALIC));
		lblNewLabel.setBounds(288, 35, 110, 19);
		lblNewLabel.setText("Klienti");

		setFormMainDriver(new FormMainDriver(this, SWT.NONE,
				formCustomer.getCustomer()));
		getFormMainDriver().setBounds(10, 197, 260, 245);

		Label label_2 = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(288, 169, 2, 300);

		setFormAdditionalDirver(new FormAdditionalDirver(this, SWT.NONE,
				getNewRental()));
		getFormAdditionalDirver().setBounds(313, 197, 260, 269);

		Label lblShoferiKryesor = new Label(this, SWT.NONE);
		lblShoferiKryesor.setFont(SWTResourceManager.getFont("Tahoma", 11,
				SWT.BOLD | SWT.ITALIC));
		lblShoferiKryesor.setBounds(87, 172, 145, 19);
		lblShoferiKryesor.setText("Shoferi kryesor");

		Label lblShoferiTjeter = new Label(this, SWT.NONE);
		lblShoferiTjeter.setFont(SWTResourceManager.getFont("Tahoma", 11,
				SWT.BOLD | SWT.ITALIC));
		lblShoferiTjeter.setBounds(416, 172, 114, 23);
		lblShoferiTjeter.setText("Shoferi tjeter");

		Label label_3 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(10, 475, 677, 2);

		Label lblKarburantiNeLitra = new Label(this, SWT.NONE);
		lblKarburantiNeLitra.setBounds(10, 483, 95, 13);
		lblKarburantiNeLitra.setText("Karburanti ne litra");

		Label lblOdometer = new Label(this, SWT.NONE);
		lblOdometer.setBounds(216, 483, 49, 13);
		lblOdometer.setText("Odometer");

		text_4 = new Text(this, SWT.BORDER);
		// text_4.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		//
		// });
		text_4.setBounds(276, 483, 145, 19);
		text_4.setText("" + carSelected.getMilage());
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);

		Label lblTotali = new Label(this, SWT.NONE);
		lblTotali.setBounds(392, 516, 33, 13);
		lblTotali.setText("Totali");

		txtTotali = new Text(this, SWT.BORDER);
		// txtTotali.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtTotali.setBounds(431, 513, 109, 19);

		Label lblGarancia = new Label(this, SWT.NONE);
		lblGarancia.setBounds(378, 549, 49, 13);
		lblGarancia.setText("Garancia");

		txtGarancia = new Text(this, SWT.BORDER);
		// text_6.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtGarancia.setBounds(431, 546, 109, 19);

		Label label_4 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(10, 508, 677, 2);

		Label lblNrDiteve = new Label(this, SWT.NONE);
		lblNrDiteve.setBounds(10, 516, 49, 13);
		lblNrDiteve.setText("Nr Diteve");

		txtNrditeve = new Text(this, SWT.BORDER);

		// txtNrditeve.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtNrditeve.setBounds(75, 516, 76, 19);

		Label lblX = new Label(this, SWT.NONE);
		lblX.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblX.setBounds(157, 516, 12, 13);
		lblX.setText("X");

		Label lblPagesaPerDite = new Label(this, SWT.NONE);
		lblPagesaPerDite.setBounds(170, 516, 87, 13);
		lblPagesaPerDite.setText("Pagesa per dite");

		txtPagesaPerdite = new Text(this, SWT.BORDER);
		// txtPagesaPerdite.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent arg0) {
		//
		// validateDigit(arg0);
		// }
		// });
		txtPagesaPerdite.setBounds(263, 516, 95, 19);

		ComboViewer comboViewer = new ComboViewer(this, SWT.NONE);
		comboMoney = comboViewer.getCombo();
		comboMoney.setBounds(560, 513, 93, 21);

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
		button.setBounds(361, 511, 20, 23);
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

					customerSelected = getFormCustomer().getCustomer();
					Customer customer = getFormMainDriver().getRental();

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

					getNewRental().setKmout(Integer.parseInt(text_4.getText()));
					int kmout = getNewRental().getKmout();

					System.out.println("Vlera e kilometrave eshte :" + kmout);
					getNewRental().getCar()
							.setMilage(getNewRental().getKmout());

					System.out.println(">>>>>>>>>>>>>>> "
							+ getNewRental().getCustomer().getDrlicnr() + "  "
							+ getNewRental().getCustomer().getDrfirstname());
					getNewRental().getCustomer().setCardnr(text_3.getText());
					getNewRental().getCustomer().setCardexp(text_5.getText());
					getNewRental().setCardexp(text_5.getText());

					boolean controlCustomer = getCustomerDao().controlCustomer(
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

					if (text_3.isEnabled())
						getNewRental().setCardnr(text_3.getText());
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
					Rental registerRental = getRentalDao().registerRental(
							getNewRental());
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
		getBtnNewButton().setBounds(10, 540, 191, 52);
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

		m_bindingContext = initDataBindings();
		definePayForDay();

		txtGarancia.setText("" + carSelected.getDeposit());
		text_4.setText("" + carSelected.getMilage());
		scale = new Scale(this, SWT.NONE);
		scale.setPageIncrement(1);
		scale.setMaximum(6);
		scale.setBounds(100, 475, 121, 42);
		scale.setSelection(6);

		ComboViewer comboViewer_1 = new ComboViewer(this, SWT.NONE);
		comboMoneyGurantee = comboViewer_1.getCombo();
		comboMoneyGurantee.setBounds(560, 546, 93, 21);
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
		btnBrendaShqiperise.setBounds(447, 481, 121, 16);
		btnBrendaShqiperise.setText("Jashte Shqiperise");
		setTabList(new Control[] { btnClientSearch, formCustomer,
				formMainDriver, formAdditionalDirver, text_4, txtTotali,
				txtGarancia, txtNrditeve, txtPagesaPerdite, comboMoney, button,
				btnNewButton, btnKontrata, scale });

		Label lblAgjentet = new Label(this, SWT.NONE);
		lblAgjentet.setBounds(207, 549, 42, 13);
		lblAgjentet.setText("Agjentet");
		comboViewerAgents = new ComboViewer(this, SWT.NONE);
		Combo combo = comboViewerAgents.getCombo();
		combo.setBounds(263, 549, 95, 21);
		Label labelRemoceCar = new Label(this, SWT.NONE);
		labelRemoceCar.addMouseListener(new MouseAdapter() {
			IAtomicChooserCar atomicChooserCar;

			@Override
			public void mouseDown(MouseEvent e) {

				atomicChooserCar.unlockCar(carSelected);
				FormCheckOut.this.dispose();

			}
		});
		labelRemoceCar.setImage(SWTResourceManager.getImage(FormCheckOut.class,
				"/image/Remove-icon.png"));
		labelRemoceCar.setBounds(538, 0, 55, 26);

		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(
				listContentProvider.getKnownElements(), Agent.class,
				"nameSuranme");
		comboViewerAgents.setLabelProvider(new ObservableMapLabelProvider(
				observeMap));
		comboViewerAgents.setContentProvider(listContentProvider);
		//
		IListProperty selfList = Properties.selfList(Agent.class);
		IObservableList observe = selfList.observe(listAgent);
		comboViewerAgents.setInput(observe);

	}

	public void addCardCrdencials() {
		Label lblNrKartes = new Label(formCustomer, SWT.NONE);
		lblNrKartes.setBounds(379, 66, 49, 13);
		lblNrKartes.setText("Nr Kartes");

		text_3 = new Text(formCustomer, SWT.BORDER);
		text_3.setBounds(434, 63, 91, 19);

		Label lblDataPerf = new Label(formCustomer, SWT.NONE);
		lblDataPerf.setBounds(531, 66, 49, 13);
		lblDataPerf.setText("Data Perf");

		text_5 = new Text(formCustomer, SWT.BORDER);
		text_5.setBounds(586, 63, 79, 19);

		if (customerSelected != null) {

			String cardnr = customerSelected.getCardnr();
			if (cardnr != null)
				text_3.setText(cardnr);
			String cardexp = customerSelected.getCardexp();
			if (cardexp != null)
				text_5.setText(cardexp);
		}
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

	private void definePayForDay() {

		int rentdays = 0;
		try {
			rentdays = Integer.parseInt(txtNrditeve.getText());
		} catch (Exception e) {
			// TODO: handle exception

			rentdays = 0;
		}
		if (rentdays >= 1 && rentdays <= 2) {

			txtPagesaPerdite.setText("" + carSelected.get_drate2());
		} else if (rentdays >= 3 && rentdays <= 6) {

			txtPagesaPerdite.setText("" + carSelected.get_drate3());

		} else if (rentdays >= 7 && rentdays <= 11) {

			txtPagesaPerdite.setText("" + carSelected.get_drate4());
		} else if (rentdays >= 12 && rentdays <= 25) {

			txtPagesaPerdite.setText("" + carSelected.get_drate5());

		} else if (rentdays >= 26 && rentdays <= 35) {

			txtPagesaPerdite.setText("" + carSelected.get_drate6());

		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setCarSelected(Car carSelected) {
		this.carSelected = carSelected;
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
		this.formCustomer = formCustomer;
	}

	public FormCustomer getFormCustomer() {
		return formCustomer;
	}

	public void setFormMainDriver(FormMainDriver formMainDriver) {
		this.formMainDriver = formMainDriver;
	}

	public FormMainDriver getFormMainDriver() {
		return formMainDriver;
	}

	public void setFormAdditionalDirver(
			FormAdditionalDirver formAdditionalDirver) {
		this.formAdditionalDirver = formAdditionalDirver;
	}

	public FormAdditionalDirver getFormAdditionalDirver() {
		return formAdditionalDirver;
	}

	public void setFormSearchPerson(FormSearchPerson formSearchPerson) {
		this.formSearchPerson = formSearchPerson;
	}

	public FormSearchPerson getFormSearchPerson() {
		return formSearchPerson;
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
		btnNewButton.setImage(SWTResourceManager.getImage(FormCheckOut.class,
				"/image/rentcar.png"));
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
	private Text text_3;
	private Text text_5;

	private CarImageHolder carImageHolder;

	private ComboViewer comboViewerAgents;

	public void addValidatorNumber(IObservableValue v, IObservableValue v2,
			DataBindingContext binding) {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		updateValueStrategy.setAfterGetValidator(iValidator);
		Binding bindValue = binding.bindValue(v, v2, updateValueStrategy, null);

		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables
				.observeText(text);
		IObservableValue carSelectedClass_ObserveValue = PojoObservables
				.observeValue(carSelected, "class_");
		bindingContext.bindValue(textObserveTextObserveWidget,
				carSelectedClass_ObserveValue, null, null);
		//
		IObservableValue text_1ObserveTextObserveWidget = SWTObservables
				.observeText(text_1);
		IObservableValue carSelectedLicenseObserveValue = PojoObservables
				.observeValue(carSelected, "license");
		bindingContext.bindValue(text_1ObserveTextObserveWidget,
				carSelectedLicenseObserveValue, null, null);
		//
		IObservableValue text_2ObserveTextObserveWidget = SWTObservables
				.observeText(text_2);
		IObservableValue carSelectedModelObserveValue = PojoObservables
				.observeValue(carSelected, "model");
		bindingContext.bindValue(text_2ObserveTextObserveWidget,
				carSelectedModelObserveValue, null, null);
		//
		IObservableValue txtNrditeveObserveTextObserveWidget = SWTObservables
				.observeText(txtNrditeve, SWT.Modify);
		IObservableValue carSelectedNrditeveObserveValue = PojoObservables
				.observeValue(carSelected, "nrditeve");
		addValidatorNumber(txtNrditeveObserveTextObserveWidget,
				carSelectedNrditeveObserveValue, bindingContext);
		//
		IObservableValue txtTotaliObserveTextObserveWidget = SWTObservables
				.observeText(txtTotali, SWT.Modify);
		IObservableValue newRentalAmountObserveValue = PojoObservables
				.observeValue(newRental, "amount");
		addValidatorNumber(txtTotaliObserveTextObserveWidget,
				newRentalAmountObserveValue, bindingContext);

		//
		IObservableValue text_4ObserveTextObserveWidget = SWTObservables
				.observeText(text_4, SWT.Modify);
		IObservableValue newRentalKmoutObserveValue = PojoObservables
				.observeValue(newRental, "kmout");
		UpdateValueStrategy strategy = new UpdateValueStrategy();

		strategy.setAfterGetValidator(iValidator);

		Binding bindValue = bindingContext.bindValue(
				text_4ObserveTextObserveWidget, newRentalKmoutObserveValue,
				strategy, null);
		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);

		//
		IObservableValue text_6ObserveTextObserveWidget = SWTObservables
				.observeText(txtGarancia, SWT.Modify);
		IObservableValue newRentalGuranteeObserveValue = PojoObservables
				.observeValue(newRental, "gurantee");
		addValidatorNumber(text_6ObserveTextObserveWidget,
				newRentalGuranteeObserveValue, bindingContext);

		//
		return bindingContext;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

		carImageHolder.dispose();
		super.dispose();

	}

	public IRentalDao getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(IRentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public IAgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(IAgentDao agentDao) {

		System.err.println("U be set");
		this.agentDao = agentDao;
	}
}
