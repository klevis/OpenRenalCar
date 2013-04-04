package ramo.kelvis.openrental.p2.handler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipselabs.p2.rcpupdate.utils.handler.InstallNewSoftwareHandler;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import ramo.klevis.openrental.utils.PerspectiveSwitcher;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class InstallNewModule {

	boolean firstTime = false;

	@Execute
	public void execute(Shell shell, IEventBroker eventBroker,
			MTrimmedWindow mTrimmedWindow, EPartService partService) {
		// TODO Your code goes here

		List<String> tags = new ArrayList<String>();
		tags.add("Default");
		PerspectiveSwitcher
				.switchPerspective(mTrimmedWindow, tags, partService);
		if (firstTime == false)
			PlatformUI.createAndRunWorkbench(shell.getDisplay(),
					new ApplicationWorkbenchAdvisor());
		firstTime = true;
		InstallNewSoftwareHandler installNewSoftwareHandler = new InstallNewSoftwareHandler();

		installNewSoftwareHandler.execute(null);
	}

}