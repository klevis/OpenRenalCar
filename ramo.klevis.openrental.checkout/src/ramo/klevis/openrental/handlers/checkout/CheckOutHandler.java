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
package ramo.klevis.openrental.handlers.checkout;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import ramo.klevis.openrental.utils.PerspectiveSwitcher;

public class CheckOutHandler {

	@Inject
	EPartService partService;

	@Execute
	public void execute(

	MTrimmedWindow mTrimmedWindow, MHandledMenuItem mHandledMenuItem,IEventBroker eventBroker)
			throws InvocationTargetException, InterruptedException {

		eventBroker.send("ClearAllCheckOut", "OK");
		PerspectiveSwitcher.switchPerspective(mTrimmedWindow,
				mHandledMenuItem.getTags(), partService);

		// EntityManager entityManager = EntityManagerQuery.getEntityManager();

	}

}
