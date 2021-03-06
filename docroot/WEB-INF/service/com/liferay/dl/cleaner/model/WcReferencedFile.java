/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.dl.cleaner.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the WcReferencedFile service. Represents a row in the &quot;DlCleaner_WcReferencedFile&quot; database table, with each column mapped to a property of this class.
 *
 * @author guywandji
 * @see WcReferencedFileModel
 * @see com.liferay.dl.cleaner.model.impl.WcReferencedFileImpl
 * @see com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl
 * @generated
 */
public interface WcReferencedFile extends WcReferencedFileModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}