/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package ramo.klevis.openrental.parts.checkin;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PartCheckIn {

	private Label label;
	private TableViewer tableViewer;

	@Inject
	public  PartCheckIn(Composite parent) {
		parent.setLayout(new GridLayout());

	
		label = new Label(parent, SWT.NONE);
		label.setText("Sample table 2");

		tableViewer = new TableViewer(parent);
		tableViewer.add("Sample item 1.2");
		tableViewer.add("Sample item 2.2");
		tableViewer.add("Sample item 3.2");
		tableViewer.add("Sample item 4.2");
		tableViewer.add("Sample item 5.2");
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	
	}

	@Focus
	public void setFocus() {
		System.out.println("On focus"+tableViewer);
		tableViewer.getTable().setFocus();
	}
}
