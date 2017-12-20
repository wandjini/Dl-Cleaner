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

package com.liferay.dl.cleaner.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * The extended model implementation for the WcReferencedFile service. Represents a row in the &quot;DlCleaner_WcReferencedFile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.dl.cleaner.model.WcReferencedFile} interface.
 * </p>
 *
 * @author guywandji
 */
public class WcReferencedFileImpl extends WcReferencedFileBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a wc referenced file model instance should use the {@link com.liferay.dl.cleaner.model.WcReferencedFile} interface instead.
	 */
	public WcReferencedFileImpl() {
	}
	public String getGroup() {
		String groupName = StringPool.BLANK;
		
		try {
			Group group = GroupLocalServiceUtil.getGroup(this.getGroupId());
			groupName = group.getName();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupName;
	}
}