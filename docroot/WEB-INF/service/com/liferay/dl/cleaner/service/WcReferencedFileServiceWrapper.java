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
 * Provides a wrapper for {@link WcReferencedFileService}.
 *
 * @author guywandji
 * @see WcReferencedFileService
 * @generated
 */
public class WcReferencedFileServiceWrapper implements WcReferencedFileService,
	ServiceWrapper<WcReferencedFileService> {
	public WcReferencedFileServiceWrapper(
		WcReferencedFileService wcReferencedFileService) {
		_wcReferencedFileService = wcReferencedFileService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _wcReferencedFileService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wcReferencedFileService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wcReferencedFileService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public void deleteWcReferencedFile(long groupId, long wcReferencedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wcReferencedFileService.deleteWcReferencedFile(groupId,
			wcReferencedFileId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WcReferencedFileService getWrappedWcReferencedFileService() {
		return _wcReferencedFileService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWcReferencedFileService(
		WcReferencedFileService wcReferencedFileService) {
		_wcReferencedFileService = wcReferencedFileService;
	}

	@Override
	public WcReferencedFileService getWrappedService() {
		return _wcReferencedFileService;
	}

	@Override
	public void setWrappedService(
		WcReferencedFileService wcReferencedFileService) {
		_wcReferencedFileService = wcReferencedFileService;
	}

	private WcReferencedFileService _wcReferencedFileService;
}