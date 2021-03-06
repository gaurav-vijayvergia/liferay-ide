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

package com.liferay.ide.eclipse.portlet.jsf.ui.wizard;

import com.liferay.ide.eclipse.portlet.jsf.core.JSFPortletUtil;
import com.liferay.ide.eclipse.portlet.jsf.core.operation.INewJSFPortletClassDataModelProperties;
import com.liferay.ide.eclipse.portlet.ui.wizard.NewPortletClassWizardPage;
import com.liferay.ide.eclipse.ui.util.SWTUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author Greg Amerson
 */
@SuppressWarnings("restriction")
public class NewJSFPortletClassWizardPage extends NewPortletClassWizardPage
	implements INewJSFPortletClassDataModelProperties {

	protected Button jsfPortletClassButton;

	protected Label jsfPortletClassLabel;

	protected Text jsfPortletClassText;

	public NewJSFPortletClassWizardPage(
		IDataModel model, String pageName, String pageDesc, String pageTitle, boolean fragment) {
		super(model, pageName, pageDesc, pageTitle, fragment);
	}

	@Override
	protected void createClassnameGroup(Composite parent) {
		// don't create classname group
	}

	protected void createCustomPortletClassGroup(Composite parent) {
		// portlet class
		jsfPortletClassLabel = new Label(parent, SWT.LEFT);
		jsfPortletClassLabel.setText("Portlet class:");
		jsfPortletClassLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		jsfPortletClassText = new Text(parent, SWT.BORDER);
		jsfPortletClassText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(jsfPortletClassText, JSF_PORTLET_CLASS, null);

		if (this.fragment) {
			SWTUtil.createLabel(parent, "", 1);
		}
		else {
			jsfPortletClassButton = new Button(parent, SWT.PUSH);
			jsfPortletClassButton.setText(J2EEUIMessages.BROWSE_BUTTON_LABEL);
			jsfPortletClassButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			jsfPortletClassButton.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					// Do nothing
				}

				public void widgetSelected(SelectionEvent e) {
					// handlePortletClassButtonPressed();
					handlePortletClassButtonSelected(jsfPortletClassText);
				}
			});
		}
	}

	@Override
	protected void createPackageGroup(Composite parent) {
		// don't create package group
	}

	@Override
	protected void createSuperclassGroup(Composite parent) {
		// instead of superclass field create our portlet class field
		createCustomPortletClassGroup(parent);
	}

	protected String[] getValidationPropertyNames() {
		List<String> validationPropertyNames = new ArrayList<String>();

		if (this.fragment) {
			return new String[] {
				IArtifactEditOperationDataModelProperties.COMPONENT_NAME,
				INewJavaClassDataModelProperties.JAVA_PACKAGE, JSF_PORTLET_CLASS
			};
		}
		else {
			Collections.addAll(validationPropertyNames, super.getValidationPropertyNames());
		}

		return validationPropertyNames.toArray(new String[0]);
	}

	protected void handlePortletClassButtonSelected(Control control) {
		handleClassButtonSelected(
			control, "javax.portlet.GenericPortlet", "Portlet Class Selection", "Choose a portlet class:");
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean projectValid = super.isProjectValid(project);

		if (!projectValid) {
			return false;
		}

		return JSFPortletUtil.isJSFProject(project);
	}

	@Override
	protected void setFocusOnClassText() {
		// dont set focus nothing really needs to be done on this page
	}

}
