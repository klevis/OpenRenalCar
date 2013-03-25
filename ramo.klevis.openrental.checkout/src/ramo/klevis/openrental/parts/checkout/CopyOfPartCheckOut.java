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

import ramo.klevis.openrental.entity.EntityManagerQuery;

public class CopyOfPartCheckOut {
	private TableViewer tableViewer;
	private Label label;

	@Inject
	public CopyOfPartCheckOut(Composite parent, IWorkbench w,
			IEclipseContext eclipseContext) {
		// TODO Your code hereti
		

		
		
		System.out.println("U ekzec" + w + "   " + eclipseContext);
		parent.setLayout(new GridLayout());

		label = new Label(parent, SWT.NONE);
		label.setText("Sample tablexxxxxxxxxxxxxxxx");

		tableViewer = new TableViewer(parent);
		tableViewer.add("Sample item 1");
		tableViewer.add("Sample item 2");
		tableViewer.add("Sample item 3");
		tableViewer.add("Sample item 4");
		tableViewer.add("Sample item 5");
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		System.out.println("Inicialization........");

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