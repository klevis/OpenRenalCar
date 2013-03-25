package ramo.klevis.openrental.parts.checkout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ramo.klevis.national.ibuisnesslogic.IAgentDao;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Rental;

public class PartCheckOut {
	private TableViewer tableViewer;
	private Label label;

	@Inject
	public PartCheckOut(Composite parent, IWorkbench w,
			IEclipseContext eclipseContext, IAgentDao agentDao) {
		// TODO Your code hereti

		 new FormCheckOut(parent,0,new Car(),agentDao);

		parent.setLayout(new GridLayout());

	}

	@PostConstruct
	public void postConstruct() {
		// TODO Your code here

		

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