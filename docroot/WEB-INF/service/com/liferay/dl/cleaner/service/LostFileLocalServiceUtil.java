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
 * Provides the local service utility for LostFile. This utility wraps
 * {@link com.liferay.dl.cleaner.service.impl.LostFileLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author guywandji
 * @see LostFileLocalService
 * @see com.liferay.dl.cleaner.service.base.LostFileLocalServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.impl.LostFileLocalServiceImpl
 * @generated
 */
public class LostFileLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.dl.cleaner.service.impl.LostFileLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the lost file to the database. Also notifies the appropriate model listeners.
	*
	* @param lostFile the lost file
	* @return the lost file that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.LostFile addLostFile(
		com.liferay.dl.cleaner.model.LostFile lostFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addLostFile(lostFile);
	}

	/**
	* Creates a new lost file with the primary key. Does not add the lost file to the database.
	*
	* @param lostFileId the primary key for the new lost file
	* @return the new lost file
	*/
	public static com.liferay.dl.cleaner.model.LostFile createLostFile(
		long lostFileId) {
		return getService().createLostFile(lostFileId);
	}

	/**
	* Deletes the lost file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lostFileId the primary key of the lost file
	* @return the lost file that was removed
	* @throws PortalException if a lost file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.LostFile deleteLostFile(
		long lostFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLostFile(lostFileId);
	}

	/**
	* Deletes the lost file from the database. Also notifies the appropriate model listeners.
	*
	* @param lostFile the lost file
	* @return the lost file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.LostFile deleteLostFile(
		com.liferay.dl.cleaner.model.LostFile lostFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLostFile(lostFile);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.dl.cleaner.model.LostFile fetchLostFile(
		long lostFileId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchLostFile(lostFileId);
	}

	/**
	* Returns the lost file with the primary key.
	*
	* @param lostFileId the primary key of the lost file
	* @return the lost file
	* @throws PortalException if a lost file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.LostFile getLostFile(
		long lostFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLostFile(lostFileId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.dl.cleaner.model.LostFile> getLostFiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLostFiles(start, end);
	}

	/**
	* Returns the number of lost files.
	*
	* @return the number of lost files
	* @throws SystemException if a system exception occurred
	*/
	public static int getLostFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLostFilesCount();
	}

	/**
	* Updates the lost file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lostFile the lost file
	* @return the lost file that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.LostFile updateLostFile(
		com.liferay.dl.cleaner.model.LostFile lostFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLostFile(lostFile);
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
	* <p>This method is used to add a new lost file</p>
	*
	* @param fileEntryId
	* @param userId
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	public static com.liferay.dl.cleaner.model.LostFile addLostFile(
		long userId, long fileEntryId, long dlFileVersionId,
		java.lang.String comment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLostFile(userId, fileEntryId, dlFileVersionId, comment);
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
	public static java.util.List<com.liferay.dl.cleaner.model.LostFile> getLostFilesByGroupAndState(
		long groupId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLostFilesByGroupAndState(groupId, deleted, start, end);
	}

	/**
	* This method is used to get the total amount of files
	*
	* @param groupId
	* @param deleted
	* @return
	* @throws SystemException
	*/
	public static int countLostFilesByGroupAndState(long groupId,
		boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countLostFilesByGroupAndState(groupId, deleted);
	}

	/**
	* Method used to clean the document and library
	*
	* @param lostFileId
	* @throws SystemException
	* @throws PortalException
	*/
	public static void cleanLostFile(long userId, long lostFileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().cleanLostFile(userId, lostFileId);
	}

	public static void clearService() {
		_service = null;
	}

	public static LostFileLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					LostFileLocalService.class.getName());

			if (invokableLocalService instanceof LostFileLocalService) {
				_service = (LostFileLocalService)invokableLocalService;
			}
			else {
				_service = new LostFileLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(LostFileLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(LostFileLocalService service) {
	}

	private static LostFileLocalService _service;
}