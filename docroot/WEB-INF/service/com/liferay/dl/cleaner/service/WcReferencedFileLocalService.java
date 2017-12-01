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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for WcReferencedFile. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author guywandji
 * @see WcReferencedFileLocalServiceUtil
 * @see com.liferay.dl.cleaner.service.base.WcReferencedFileLocalServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.impl.WcReferencedFileLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WcReferencedFileLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WcReferencedFileLocalServiceUtil} to access the wc referenced file local service. Add custom service methods to {@link com.liferay.dl.cleaner.service.impl.WcReferencedFileLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the wc referenced file to the database. Also notifies the appropriate model listeners.
	*
	* @param wcReferencedFile the wc referenced file
	* @return the wc referenced file that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.dl.cleaner.model.WcReferencedFile addWcReferencedFile(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new wc referenced file with the primary key. Does not add the wc referenced file to the database.
	*
	* @param wcRefencedFileId the primary key for the new wc referenced file
	* @return the new wc referenced file
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile createWcReferencedFile(
		long wcRefencedFileId);

	/**
	* Deletes the wc referenced file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file that was removed
	* @throws PortalException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.liferay.dl.cleaner.model.WcReferencedFile deleteWcReferencedFile(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the wc referenced file from the database. Also notifies the appropriate model listeners.
	*
	* @param wcReferencedFile the wc referenced file
	* @return the wc referenced file that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.liferay.dl.cleaner.model.WcReferencedFile deleteWcReferencedFile(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchWcReferencedFile(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file with the primary key.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file
	* @throws PortalException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.dl.cleaner.model.WcReferencedFile getWcReferencedFile(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wc referenced files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wc referenced files
	* @param end the upper bound of the range of wc referenced files (not inclusive)
	* @return the range of wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> getWcReferencedFiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wc referenced files.
	*
	* @return the number of wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWcReferencedFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the wc referenced file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wcReferencedFile the wc referenced file
	* @return the wc referenced file that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.dl.cleaner.model.WcReferencedFile updateWcReferencedFile(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* <p>This method is used to add a new Web content referenced  file</p>
	*
	* @param userId
	* @param groupId
	* @param dlFileUuId
	* @param articleId
	* @param comment
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile addWcReferencedFile(
		long userId, long groupId, java.lang.String dlFileUuId,
		java.lang.String articleId, java.lang.String type, boolean orphan)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieves items by groupId
	*
	* @param groupId
	* @param start
	* @param end
	* @return
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> getWcReferencedFilesByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieves Referenced file by groupId, articleId and fileUUID
	*
	* @param groupId
	* @param start
	* @param end
	* @return
	* @throws SystemException
	* @throws NoSuchWcReferencedFileException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.dl.cleaner.model.WcReferencedFile getWcReferencedFilesByGroupAndFileUUID(
		long groupId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Count items by groupId
	*
	* @param groupId
	* @return
	* @throws SystemException
	*/
	public int countWcReferencedFilesByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieves Referenced file by groupId, articleId and fileUUID
	*
	* @param companyId
	* @param dlFileUuId
	* @param start
	* @param end
	* @return
	* @throws SystemException
	* @throws NoSuchWcReferencedFileException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.dl.cleaner.model.WcReferencedFile getWcReferencedFilesByCompanyAndFileUUID(
		long companyId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the file reference
	*
	* @param wcRefencedFileId
	* @throws SystemException
	* @throws NoSuchWcReferencedFileException
	*/
	public void removeWcReferencedFile(long wcRefencedFileId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieves items by companyId and orfan
	*
	* @param companyId
	* @param orphan
	* @param start
	* @param end
	* @return
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> getWcReferencedFilesByCompanyIdAndOrphan(
		long companyId, boolean orphan, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieves items by companyId and orfan
	*
	* @param companyId
	* @param orphan
	* @param start
	* @param end
	* @return
	* @throws SystemException
	*/
	public int countWcReferencedFilesByCompanyIdAndOrphan(long companyId,
		boolean orphan)
		throws com.liferay.portal.kernel.exception.SystemException;
}