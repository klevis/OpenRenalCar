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
package ramo.klevis.openrental.handlers.checkin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import ramo.klevis.openrental.utils.PerspectiveSwitcher;

public class CheckInHandler {

	@Inject
	EPartService partService;

	@Execute
	public void execute(IWorkbench workbench, IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			MTrimmedWindow mTrimmedWindow, MHandledMenuItem mHandledMenuItem)
			throws InvocationTargetException, InterruptedException {

		// PerspectiveSwitcher.switchPerspective(mTrimmedWindow,
		// mHandledToolItem);

		
		PerspectiveSwitcher.switchPerspective(mTrimmedWindow,
				mHandledMenuItem.getTags(), partService);
		
		// partService.switchPerspective(perspective)
	}
}
