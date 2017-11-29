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
 * Provides a wrapper for {@link UnusedFileLocalService}.
 *
 * @author guywandji
 * @see UnusedFileLocalService
 * @generated
 */
public class UnusedFileLocalServiceWrapper implements UnusedFileLocalService,
	ServiceWrapper<UnusedFileLocalService> {
	public UnusedFileLocalServiceWrapper(
		UnusedFileLocalService unusedFileLocalService) {
		_unusedFileLocalService = unusedFileLocalService;
	}

	/**
	* Adds the unused file to the database. Also notifies the appropriate model listeners.
	*
	* @param unusedFile the unused file
	* @return the unused file that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile addUnusedFile(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.addUnusedFile(unusedFile);
	}

	/**
	* Creates a new unused file with the primary key. Does not add the unused file to the database.
	*
	* @param unusedFileId the primary key for the new unused file
	* @return the new unused file
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile createUnusedFile(
		long unusedFileId) {
		return _unusedFileLocalService.createUnusedFile(unusedFileId);
	}

	/**
	* Deletes the unused file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file that was removed
	* @throws PortalException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile deleteUnusedFile(
		long unusedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.deleteUnusedFile(unusedFileId);
	}

	/**
	* Deletes the unused file from the database. Also notifies the appropriate model listeners.
	*
	* @param unusedFile the unused file
	* @return the unused file that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile deleteUnusedFile(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.deleteUnusedFile(unusedFile);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _unusedFileLocalService.dynamicQuery();
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
		return _unusedFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _unusedFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _unusedFileLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _unusedFileLocalService.dynamicQueryCount(dynamicQuery);
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
		return _unusedFileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.dl.cleaner.model.UnusedFile fetchUnusedFile(
		long unusedFileId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.fetchUnusedFile(unusedFileId);
	}

	/**
	* Returns the unused file with the primary key.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file
	* @throws PortalException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile getUnusedFile(
		long unusedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.getUnusedFile(unusedFileId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the unused files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of unused files
	* @param end the upper bound of the range of unused files (not inclusive)
	* @return the range of unused files
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> getUnusedFiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.getUnusedFiles(start, end);
	}

	/**
	* Returns the number of unused files.
	*
	* @return the number of unused files
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getUnusedFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.getUnusedFilesCount();
	}

	/**
	* Updates the unused file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param unusedFile the unused file
	* @return the unused file that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile updateUnusedFile(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.updateUnusedFile(unusedFile);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _unusedFileLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_unusedFileLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _unusedFileLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* <p>This method is used to add a new unused file</p>
	*
	* @param fileEntryId
	* @param userId
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile addUnusedFile(long userId,
		long fileEntryId, long fileVersionId, java.lang.String comment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.addUnusedFile(userId, fileEntryId,
			fileVersionId, comment);
	}

	/**
	* Method to get UnusedFile by group id, file entry id and file version id
	*
	* @param groupId
	* @param fileEntryId
	* @param fileVersionId
	* @return
	* @throws SystemException
	* @throws NoSuchUnusedFileException
	*/
	@Override
	public com.liferay.dl.cleaner.model.UnusedFile getUnusedFilesByGroupFileIdVersionId(
		long groupId, long fileEntryId, long fileVersionId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.getUnusedFilesByGroupFileIdVersionId(groupId,
			fileEntryId, fileVersionId);
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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> getUnusedFilesByGroupAndState(
		long groupId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.getUnusedFilesByGroupAndState(groupId,
			deleted, start, end);
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
	public int countUnusedFilesByGroupAndState(long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFileLocalService.countUnusedFilesByGroupAndState(groupId,
			deleted);
	}

	/**
	* Method used to clean the document and library
	*
	* @param unusedFileId
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public void cleanUnusedFile(long unusedFileId,
		java.lang.String fileVersion, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_unusedFileLocalService.cleanUnusedFile(unusedFileId, fileVersion,
			userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UnusedFileLocalService getWrappedUnusedFileLocalService() {
		return _unusedFileLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUnusedFileLocalService(
		UnusedFileLocalService unusedFileLocalService) {
		_unusedFileLocalService = unusedFileLocalService;
	}

	@Override
	public UnusedFileLocalService getWrappedService() {
		return _unusedFileLocalService;
	}

	@Override
	public void setWrappedService(UnusedFileLocalService unusedFileLocalService) {
		_unusedFileLocalService = unusedFileLocalService;
	}

	private UnusedFileLocalService _unusedFileLocalService;
}