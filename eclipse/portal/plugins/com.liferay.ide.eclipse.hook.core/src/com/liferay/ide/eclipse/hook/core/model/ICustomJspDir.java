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
 * Contributors:
 *    Gregory Amerson - initial implementation
 ******************************************************************************/

package com.liferay.ide.eclipse.hook.core.model;

import com.liferay.ide.eclipse.hook.core.model.internal.DocrootRelativePathService;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Path;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.FileSystemResourceType;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.InitialValue;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.MustExist;
import org.eclipse.sapphire.modeling.annotations.Service;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.annotations.ValidFileSystemResourceType;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;

@GenerateImpl
public interface ICustomJspDir extends IModelElement
{

	ModelElementType TYPE = new ModelElementType( ICustomJspDir.class );

	// *** Value ***

	@Type( base = Path.class )
	@Label( standard = "Custom JSP Dir" )
	@XmlBinding( path = "" )
	@Service( impl = DocrootRelativePathService.class )
	@ValidFileSystemResourceType( FileSystemResourceType.FOLDER )
	@InitialValue( text = "/META-INF/custom_jsps" )
	@MustExist
	ValueProperty PROP_VALUE = new ValueProperty( TYPE, "Value" );

	Value<Path> getValue();

	void setValue( String value );

	void setValue( Path value );
}
