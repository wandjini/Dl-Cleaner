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

package com.liferay.dl.cleaner.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UnusedFileService}.
 *
 * @author guywandji
 * @see UnusedFileService
 * @generated
 */
public class UnusedFileServiceWrapper implements UnusedFileService,
	ServiceWrapper<UnusedFileService> {
	public UnusedFileServiceWrapper(UnusedFileService unusedFileService) {
		_unusedFileService = unusedFileService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _unusedFileService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_unusedFileService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _unusedFileService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void deleteUnusedFile(long userId, long groupId, long unusedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		_unusedFileService.deleteUnusedFile(userId, groupId, unusedFileId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UnusedFileService getWrappedUnusedFileService() {
		return _unusedFileService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUnusedFileService(UnusedFileService unusedFileService) {
		_unusedFileService = unusedFileService;
	}

	@Override
	public UnusedFileService getWrappedService() {
		return _unusedFileService;
	}

	@Override
	public void setWrappedService(UnusedFileService unusedFileService) {
		_unusedFileService = unusedFileService;
	}

	private UnusedFileService _unusedFileService;
}