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
import com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil;
import com.liferay.dl.cleaner.service.base.UnusedFileServiceBaseImpl;
import com.liferay.dl.cleaner.service.permission.UnusedFilePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;

/**
 * The implementation of the unused file remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.dl.cleaner.service.UnusedFileService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.base.UnusedFileServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.UnusedFileServiceUtil
 */
public class UnusedFileServiceImpl extends UnusedFileServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.dl.cleaner.service.UnusedFileServiceUtil} to access the
	 * unused file remote service.
	 */

	public void deleteUnusedFile(long userId, long groupId, long unusedFileId)
			throws PrincipalException, PortalException, SystemException {

		UnusedFilePermission.check(getPermissionChecker(), groupId, ActionKeys.DELETE_UNUSED_FILE);
		UnusedFileLocalServiceUtil.cleanUnusedFile(userId, unusedFileId);
	}
}