/*******************************************************************************
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/

package com.liferay.ide.eclipse.server.ui.action;

import com.liferay.ide.eclipse.server.core.ILiferayServer;
import com.liferay.ide.eclipse.server.ui.LiferayServerUIPlugin;

import java.net.URL;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.ui.internal.ServerUIPlugin;

/**
 * @author Greg Amerson
 */
@SuppressWarnings("restriction")
public class OpenPortalAction extends AbstractServerRunningAction {

	public OpenPortalAction() {
		super();
	}

	public void run(IAction action) {
		if (selectedServer != null) {
			ILiferayServer portalServer = (ILiferayServer) selectedServer.loadAdapter(ILiferayServer.class, null);
			
			URL portalHome = portalServer.getPortalHomeUrl();
			
			if (portalHome == null) {
				MessageDialog.openError(
					getActiveShell(), "Open Portal Home",
					"Could not determine portal home URL. Please make sure the server is properly configured.");
				return;
			}

			try {
				IWorkbenchBrowserSupport browserSupport =
					ServerUIPlugin.getInstance().getWorkbench().getBrowserSupport();
				
				IWebBrowser browser =
					browserSupport.createBrowser(IWorkbenchBrowserSupport.LOCATION_BAR |
						IWorkbenchBrowserSupport.NAVIGATION_BAR, null, "Liferay Portal", null);
				
				browser.openURL(portalHome);
			}
			catch (Exception e) {
				LiferayServerUIPlugin.logError(e);
			}
		}
	}

	@Override
	protected int getRequiredServerState() {
		return IServer.STATE_STARTED;
	}

}
