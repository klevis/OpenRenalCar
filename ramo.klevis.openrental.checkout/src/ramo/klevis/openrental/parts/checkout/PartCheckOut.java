package ramo.klevis.openrental.parts.checkout;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainerElement;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.Customer;
import ramo.klevis.openrental.forms.FormAdditionalDirver;
import ramo.klevis.openrental.forms.FormCheckOut;
import ramo.klevis.openrental.forms.FormCustomer;
import ramo.klevis.openrental.forms.FormMainDriver;
import ramo.klevis.openrental.iservice.ICheckOutConsumer;
import ramo.klevis.openrental.utils.PerspectiveSwitcher;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PartCheckOut {

	public PartCheckOut() {
		// TODO Auto-generated constructor stub
	}

	private FormCheckOut formCheckOut;

	private Composite parent;
	private Car selectedCar;

	private ICheckOutConsumer checkOutConsumer;

	private FormCustomer formCustomer;

	private FormMainDriver formMainDriver;

	private FormAdditionalDirver formAdditionalDirver;

	Customer customerSelected;
	MPerspective mPerspective;

	@Inject
	EPartService partService;
	
	@Inject
	EModelService modelService;
	
	

	@Inject
	@PostConstruct
	public void createControls(Composite parent,
			ICheckOutConsumer checkOutConsumer, MPerspective mPerspective) {
		parent.setLayout(null);

		this.mPerspective = mPerspective;
		this.checkOutConsumer = checkOutConsumer;
		this.parent = parent;

		// ScrolledComposite scrolledComposite = new ScrolledComposite(parent,
		// SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		// scrolledComposite.setExpandHorizontal(true);
		// scrolledComposite.setExpandVertical(true);
		// scrolledComposite.setAlwaysShowScrollBars(true);
		// loadCheckOutInfo(parent);

	}

	@Inject
	@Optional
	public void clearAll(@EventTopic("ClearAllCheckOut") String s) {

		loadCheckOutInfo(parent);

		PerspectiveSwitcher.showWindowWithId(modelService,mPerspective, "Car Selection");
		PerspectiveSwitcher.disposeWindowWithId(modelService,mPerspective,
				"Customer Selection");
	}

	@Inject
	@Optional
	public void showSelectedCar(@EventTopic(value = "CarSelected") Car car

	, MPerspective mPerspective) {

		PerspectiveSwitcher.disposeWindowWithId(modelService,mPerspective, "Car Selection");
		this.selectedCar = car;

		loadCheckOutInfo(parent);

	}

	@Inject
	@Optional
	public void showSelectedcustomer(
			@EventTopic(value = "CustomerSelected") Customer customer,
			MPerspective mPerspective) {

		PerspectiveSwitcher.disposeWindowWithId(modelService,mPerspective,
				"Customer Selection");
		this.customerSelected = customer;

		formCustomer.setCustomer(customer);

		formMainDriver.setCustomer(customer);
	}

	private void loadCheckOutInfo(Composite parent) {

		if (formCustomer != null) {
			formCustomer.dispose();
		}
		formCustomer = new FormCustomer(parent, SWT.NONE);
		formCustomer.setBounds(10, 31, 702, 92);

		if (formMainDriver != null) {
			formMainDriver.dispose();
		}
		formMainDriver = new FormMainDriver(parent, SWT.NONE);
		formMainDriver.setBounds(10, 148, 273, 231);

		Label label = new Label(parent, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(308, 148, 2, 361);

		if (formAdditionalDirver != null) {
			formAdditionalDirver.dispose();
		}
		formAdditionalDirver = new FormAdditionalDirver(parent, SWT.NONE);
		formAdditionalDirver.setBounds(327, 148, 265, 361);

		if (formCheckOut != null) {
			formCheckOut.dispose();
		}
		formCheckOut = new FormCheckOut(parent, SWT.NONE);
		formCheckOut.setCheckOutConsumer(checkOutConsumer);
		formCheckOut.setCarSelected(selectedCar);
		formCheckOut.setBounds(10, 515, 761, 231);

		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Ubuntu", 12, SWT.BOLD));
		lblNewLabel.setBounds(10, 0, 98, 17);
		lblNewLabel.setText("Customer");

		Label lblMainDriver = new Label(parent, SWT.NONE);
		lblMainDriver.setFont(SWTResourceManager
				.getFont("Ubuntu", 12, SWT.BOLD));
		lblMainDriver.setBounds(10, 129, 113, 17);
		lblMainDriver.setText("Main Driver");

		Label lblAdditionalDriver = new Label(parent, SWT.NONE);
		lblAdditionalDriver.setText("Additional Driver");
		lblAdditionalDriver.setFont(SWTResourceManager.getFont("Ubuntu", 12,
				SWT.BOLD));
		lblAdditionalDriver.setBounds(310, 125, 135, 17);
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				System.err.println("////////////////");
				PerspectiveSwitcher.showWindowWithId(modelService,mPerspective,
						"Customer Selection");

			}
		});
		btnNewButton.setImage(ResourceManager.getPluginImage(
				"ramo.klevis.openrental", "icons/cearch.png"));
		btnNewButton.setFont(SWTResourceManager.getFont("Ubuntu", 9, SWT.BOLD));
		btnNewButton.setBounds(112, 2, 157, 25);
		btnNewButton.setText("Search for customers");
		parent.layout();

	}

	@PreDestroy
	public void preDestroy() {
		// TODO Your code here
	}

	@Focus
	public void onFocus() {
		// TODO Your code here

	}

	@Persist
	public void save() {
		// TODO Your code here

	}
}