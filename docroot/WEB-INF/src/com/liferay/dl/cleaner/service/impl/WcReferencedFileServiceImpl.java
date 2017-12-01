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

package com.liferay.dl.cleaner.service.impl;

import com.liferay.dl.cleaner.portlet.util.ActionKeys;
import com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil;
import com.liferay.dl.cleaner.service.base.WcReferencedFileServiceBaseImpl;
import com.liferay.dl.cleaner.service.permission.WcReferencedFilePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the wc referenced file remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.dl.cleaner.service.WcReferencedFileService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.base.WcReferencedFileServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.WcReferencedFileServiceUtil
 */
public class WcReferencedFileServiceImpl extends WcReferencedFileServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.dl.cleaner.service.WcReferencedFileServiceUtil} to access the wc referenced file remote service.
	 */
	
	public void deleteWcReferencedFile(long groupId, long wcReferencedFileId) throws PortalException, SystemException{
		WcReferencedFilePermission.check(getPermissionChecker(), groupId, ActionKeys.DELETE_WCREFERENCED_FILE);
		WcReferencedFileLocalServiceUtil.deleteWcReferencedFile(wcReferencedFileId);
	}
}