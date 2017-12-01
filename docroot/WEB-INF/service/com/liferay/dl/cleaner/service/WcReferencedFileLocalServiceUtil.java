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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for WcReferencedFile. This utility wraps
 * {@link com.liferay.dl.cleaner.service.impl.WcReferencedFileLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author guywandji
 * @see WcReferencedFileLocalService
 * @see com.liferay.dl.cleaner.service.base.WcReferencedFileLocalServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.impl.WcReferencedFileLocalServiceImpl
 * @generated
 */
public class WcReferencedFileLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.dl.cleaner.service.impl.WcReferencedFileLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the wc referenced file to the database. Also notifies the appropriate model listeners.
	*
	* @param wcReferencedFile the wc referenced file
	* @return the wc referenced file that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.WcReferencedFile addWcReferencedFile(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWcReferencedFile(wcReferencedFile);
	}

	/**
	* Creates a new wc referenced file with the primary key. Does not add the wc referenced file to the database.
	*
	* @param wcRefencedFileId the primary key for the new wc referenced file
	* @return the new wc referenced file
	*/
	public static com.liferay.dl.cleaner.model.WcReferencedFile createWcReferencedFile(
		long wcRefencedFileId) {
		return getService().createWcReferencedFile(wcRefencedFileId);
	}

	/**
	* Deletes the wc referenced file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file that was removed
	* @throws PortalException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.WcReferencedFile deleteWcReferencedFile(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWcReferencedFile(wcRefencedFileId);
	}

	/**
	* Deletes the wc referenced file from the database. Also notifies the appropriate model listeners.
	*
	* @param wcReferencedFile the wc referenced file
	* @return the wc referenced file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.WcReferencedFile deleteWcReferencedFile(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWcReferencedFile(wcReferencedFile);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.dl.cleaner.model.WcReferencedFile fetchWcReferencedFile(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchWcReferencedFile(wcRefencedFileId);
	}

	/**
	* Returns the wc referenced file with the primary key.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file
	* @throws PortalException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.WcReferencedFile getWcReferencedFile(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWcReferencedFile(wcRefencedFileId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

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
	public static java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> getWcReferencedFiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWcReferencedFiles(start, end);
	}

	/**
	* Returns the number of wc referenced files.
	*
	* @return the number of wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public static int getWcReferencedFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWcReferencedFilesCount();
	}

	/**
	* Updates the wc referenced file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wcReferencedFile the wc referenced file
	* @return the wc referenced file that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.WcReferencedFile updateWcReferencedFile(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWcReferencedFile(wcReferencedFile);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

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
	public static com.liferay.dl.cleaner.model.WcReferencedFile addWcReferencedFile(
		long userId, long groupId, java.lang.String dlFileUuId,
		java.lang.String articleId, java.lang.String type, boolean orphan)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addWcReferencedFile(userId, groupId, dlFileUuId, articleId,
			type, orphan);
	}

	/**
	* Retrieves items by groupId
	*
	* @param groupId
	* @param start
	* @param end
	* @return
	* @throws SystemException
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> getWcReferencedFilesByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWcReferencedFilesByGroupId(groupId, start, end);
	}

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
	public static com.liferay.dl.cleaner.model.WcReferencedFile getWcReferencedFilesByGroupAndFileUUID(
		long groupId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWcReferencedFilesByGroupAndFileUUID(groupId, dlFileUuId);
	}

	/**
	* Count items by groupId
	*
	* @param groupId
	* @return
	* @throws SystemException
	*/
	public static int countWcReferencedFilesByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countWcReferencedFilesByGroupId(groupId);
	}

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
	public static com.liferay.dl.cleaner.model.WcReferencedFile getWcReferencedFilesByCompanyAndFileUUID(
		long companyId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWcReferencedFilesByCompanyAndFileUUID(companyId,
			dlFileUuId);
	}

	/**
	* Deletes the file reference
	*
	* @param wcRefencedFileId
	* @throws SystemException
	* @throws NoSuchWcReferencedFileException
	*/
	public static void removeWcReferencedFile(long wcRefencedFileId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().removeWcReferencedFile(wcRefencedFileId);
	}

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
	public static java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> getWcReferencedFilesByCompanyIdAndOrphan(
		long companyId, boolean orphan, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWcReferencedFilesByCompanyIdAndOrphan(companyId, orphan,
			start, end);
	}

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
	public static int countWcReferencedFilesByCompanyIdAndOrphan(
		long companyId, boolean orphan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countWcReferencedFilesByCompanyIdAndOrphan(companyId, orphan);
	}

	public static void clearService() {
		_service = null;
	}

	public static WcReferencedFileLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WcReferencedFileLocalService.class.getName());

			if (invokableLocalService instanceof WcReferencedFileLocalService) {
				_service = (WcReferencedFileLocalService)invokableLocalService;
			}
			else {
				_service = new WcReferencedFileLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WcReferencedFileLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(WcReferencedFileLocalService service) {
	}

	private static WcReferencedFileLocalService _service;
}