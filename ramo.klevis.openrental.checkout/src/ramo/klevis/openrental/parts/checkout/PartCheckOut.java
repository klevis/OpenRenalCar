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
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.forms.FormAdditionalDirver;
import ramo.klevis.openrental.forms.FormCheckOut;
import ramo.klevis.openrental.forms.FormCustomer;
import ramo.klevis.openrental.forms.FormMainDriver;

public class PartCheckOut {

	public PartCheckOut() {
		// TODO Auto-generated constructor stub
	}

	@Inject
	EPartService partService;	
	@Inject
	@Optional
	public void showCSelectedCar(@EventTopic(value = "CarSelected") Car car

	, MTrimmedWindow mTrimmedWindow) {
		
		

		showPartWithId(mTrimmedWindow,
				"ramo.klevis.openrental.partsashcontainer.0", true);

	}

	private void showPartWithId(MTrimmedWindow mTrimmedWindow, String id,
			boolean show) {
		List<MWindowElement> children = mTrimmedWindow.getChildren();

		for (MWindowElement mWindowElement : children) {

			if (mWindowElement instanceof MPerspectiveStack)

			{
				MPerspectiveStack mPerspectiveStack = (MPerspectiveStack) mWindowElement;

				List<MPerspective> children2 = mPerspectiveStack.getChildren();

				for (MPerspective mPerspective : children2) {

					if (mPerspective.getElementId().equals(
							"Perspective CheckOut")) {

						List<MPartSashContainerElement> children3 = mPerspective
								.getChildren();
						for (MPartSashContainerElement mPartSashContainerElement : children3) {

							if (mPartSashContainerElement.getElementId()
									.equals(id)) {

								mPartSashContainerElement.setVisible(show);

							}
						}

						List<MWindow> windows = mPerspective.getWindows();
						for (MWindow mWindow : windows) {

							mWindow.setVisible(false);
						}
					}
				}

			}
		}
	}

	@Inject
	@PostConstruct
	public void createControls(Composite parent, MTrimmedWindow mTrimmedWindow) {
		parent.setLayout(null);

		FormCustomer formCustomer = new FormCustomer(parent, SWT.NONE);
		formCustomer.setBounds(10, 59, 702, 100);

		FormMainDriver formMainDriver = new FormMainDriver(parent, SWT.NONE);
		formMainDriver.setBounds(10, 202, 273, 231);

		Label label = new Label(parent, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(307, 202, 2, 361);

		FormAdditionalDirver formAdditionalDirver = new FormAdditionalDirver(
				parent, SWT.NONE);
		formAdditionalDirver.setBounds(327, 202, 265, 361);

		FormCheckOut formCheckOut = new FormCheckOut(parent, SWT.NONE);
		formCheckOut.setBounds(10, 594, 761, 231);

		showPartWithId(mTrimmedWindow,
				"ramo.klevis.openrental.partsashcontainer.0", false);

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