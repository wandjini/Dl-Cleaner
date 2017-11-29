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
 * Provides a wrapper for {@link LostFileLocalService}.
 *
 * @author guywandji
 * @see LostFileLocalService
 * @generated
 */
public class LostFileLocalServiceWrapper implements LostFileLocalService,
	ServiceWrapper<LostFileLocalService> {
	public LostFileLocalServiceWrapper(
		LostFileLocalService lostFileLocalService) {
		_lostFileLocalService = lostFileLocalService;
	}

	/**
	* Adds the lost file to the database. Also notifies the appropriate model listeners.
	*
	* @param lostFile the lost file
	* @return the lost file that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile addLostFile(
		com.liferay.dl.cleaner.model.LostFile lostFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.addLostFile(lostFile);
	}

	/**
	* Creates a new lost file with the primary key. Does not add the lost file to the database.
	*
	* @param lostFileId the primary key for the new lost file
	* @return the new lost file
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile createLostFile(long lostFileId) {
		return _lostFileLocalService.createLostFile(lostFileId);
	}

	/**
	* Deletes the lost file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lostFileId the primary key of the lost file
	* @return the lost file that was removed
	* @throws PortalException if a lost file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile deleteLostFile(long lostFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.deleteLostFile(lostFileId);
	}

	/**
	* Deletes the lost file from the database. Also notifies the appropriate model listeners.
	*
	* @param lostFile the lost file
	* @return the lost file that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile deleteLostFile(
		com.liferay.dl.cleaner.model.LostFile lostFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.deleteLostFile(lostFile);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lostFileLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.dl.cleaner.model.LostFile fetchLostFile(long lostFileId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.fetchLostFile(lostFileId);
	}

	/**
	* Returns the lost file with the primary key.
	*
	* @param lostFileId the primary key of the lost file
	* @return the lost file
	* @throws PortalException if a lost file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile getLostFile(long lostFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.getLostFile(lostFileId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the lost files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lost files
	* @param end the upper bound of the range of lost files (not inclusive)
	* @return the range of lost files
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.dl.cleaner.model.LostFile> getLostFiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.getLostFiles(start, end);
	}

	/**
	* Returns the number of lost files.
	*
	* @return the number of lost files
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLostFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.getLostFilesCount();
	}

	/**
	* Updates the lost file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lostFile the lost file
	* @return the lost file that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile updateLostFile(
		com.liferay.dl.cleaner.model.LostFile lostFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.updateLostFile(lostFile);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _lostFileLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_lostFileLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _lostFileLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* <p>This method is used to add a new lost file</p>
	*
	* @param fileEntryId
	* @param userId
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile addLostFile(long userId,
		long fileEntryId, long dlFileVersionId, java.lang.String comment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.addLostFile(userId, fileEntryId,
			dlFileVersionId, comment);
	}

	/**
	* <p>This method is used to retrieve File by groupId and state</p>
	*
	* @param groupId
	* @param deleted
	* @param start
	* @param end
	* @return
	* @throws SystemException
	*/
	@Override
	public java.util.List<com.liferay.dl.cleaner.model.LostFile> getLostFilesByGroupAndState(
		long groupId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.getLostFilesByGroupAndState(groupId,
			deleted, start, end);
	}

	/**
	* Method to get Lostfile by group id, file entry id and file version id
	*
	* @param groupId
	* @param fileEntryId
	* @param fileVersionId
	* @return
	* @throws NoSuchLostFileException
	* @throws SystemException
	*/
	@Override
	public com.liferay.dl.cleaner.model.LostFile getLostFilesByGroupFileIdVersionId(
		long groupId, long fileEntryId, long fileVersionId)
		throws com.liferay.dl.cleaner.NoSuchLostFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.getLostFilesByGroupFileIdVersionId(groupId,
			fileEntryId, fileVersionId);
	}

	/**
	* This method is used to get the total amount of files
	*
	* @param groupId
	* @param deleted
	* @return
	* @throws SystemException
	*/
	@Override
	public int countLostFilesByGroupAndState(long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFileLocalService.countLostFilesByGroupAndState(groupId,
			deleted);
	}

	/**
	* Method used to clean the document and library
	*
	* @param lostFileId
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public void cleanLostFile(long userId, long lostFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_lostFileLocalService.cleanLostFile(userId, lostFileId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LostFileLocalService getWrappedLostFileLocalService() {
		return _lostFileLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLostFileLocalService(
		LostFileLocalService lostFileLocalService) {
		_lostFileLocalService = lostFileLocalService;
	}

	@Override
	public LostFileLocalService getWrappedService() {
		return _lostFileLocalService;
	}

	@Override
	public void setWrappedService(LostFileLocalService lostFileLocalService) {
		_lostFileLocalService = lostFileLocalService;
	}

	private LostFileLocalService _lostFileLocalService;
}