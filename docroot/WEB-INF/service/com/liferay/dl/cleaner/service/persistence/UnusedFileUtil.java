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

package com.liferay.dl.cleaner.service.persistence;

import com.liferay.dl.cleaner.model.UnusedFile;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the unused file service. This utility wraps {@link UnusedFilePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author guywandji
 * @see UnusedFilePersistence
 * @see UnusedFilePersistenceImpl
 * @generated
 */
public class UnusedFileUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(UnusedFile unusedFile) {
		getPersistence().clearCache(unusedFile);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UnusedFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UnusedFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UnusedFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static UnusedFile update(UnusedFile unusedFile)
		throws SystemException {
		return getPersistence().update(unusedFile);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static UnusedFile update(UnusedFile unusedFile,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(unusedFile, serviceContext);
	}

	/**
	* Returns the unused file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or throws a {@link com.liferay.dl.cleaner.NoSuchUnusedFileException} if it could not be found.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the matching unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile findByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId);
	}

	/**
	* Returns the unused file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId);
	}

	/**
	* Returns the unused file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId, retrieveFromCache);
	}

	/**
	* Removes the unused file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the unused file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile removeByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId);
	}

	/**
	* Returns the number of unused files where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63;.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the number of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId);
	}

	/**
	* Returns all the unused files where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @return the matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByGroup_Deleted(
		long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroup_Deleted(groupId, deleted);
	}

	/**
	* Returns a range of all the unused files where groupId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param start the lower bound of the range of unused files
	* @param end the upper bound of the range of unused files (not inclusive)
	* @return the range of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByGroup_Deleted(
		long groupId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroup_Deleted(groupId, deleted, start, end);
	}

	/**
	* Returns an ordered range of all the unused files where groupId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param start the lower bound of the range of unused files
	* @param end the upper bound of the range of unused files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByGroup_Deleted(
		long groupId, boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroup_Deleted(groupId, deleted, start, end,
			orderByComparator);
	}

	/**
	* Returns the first unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile findByGroup_Deleted_First(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroup_Deleted_First(groupId, deleted,
			orderByComparator);
	}

	/**
	* Returns the first unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_Deleted_First(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroup_Deleted_First(groupId, deleted,
			orderByComparator);
	}

	/**
	* Returns the last unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile findByGroup_Deleted_Last(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroup_Deleted_Last(groupId, deleted, orderByComparator);
	}

	/**
	* Returns the last unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_Deleted_Last(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGroup_Deleted_Last(groupId, deleted,
			orderByComparator);
	}

	/**
	* Returns the unused files before and after the current unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param unusedFileId the primary key of the current unused file
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile[] findByGroup_Deleted_PrevAndNext(
		long unusedFileId, long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroup_Deleted_PrevAndNext(unusedFileId, groupId,
			deleted, orderByComparator);
	}

	/**
	* Removes all the unused files where groupId = &#63; and deleted = &#63; from the database.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroup_Deleted(long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroup_Deleted(groupId, deleted);
	}

	/**
	* Returns the number of unused files where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @return the number of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroup_Deleted(long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroup_Deleted(groupId, deleted);
	}

	/**
	* Returns all the unused files where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @return the matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByCompany_Deleted(
		long companyId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompany_Deleted(companyId, deleted);
	}

	/**
	* Returns a range of all the unused files where companyId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param start the lower bound of the range of unused files
	* @param end the upper bound of the range of unused files (not inclusive)
	* @return the range of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByCompany_Deleted(
		long companyId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompany_Deleted(companyId, deleted, start, end);
	}

	/**
	* Returns an ordered range of all the unused files where companyId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param start the lower bound of the range of unused files
	* @param end the upper bound of the range of unused files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByCompany_Deleted(
		long companyId, boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompany_Deleted(companyId, deleted, start, end,
			orderByComparator);
	}

	/**
	* Returns the first unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile findByCompany_Deleted_First(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompany_Deleted_First(companyId, deleted,
			orderByComparator);
	}

	/**
	* Returns the first unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByCompany_Deleted_First(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompany_Deleted_First(companyId, deleted,
			orderByComparator);
	}

	/**
	* Returns the last unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile findByCompany_Deleted_Last(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompany_Deleted_Last(companyId, deleted,
			orderByComparator);
	}

	/**
	* Returns the last unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByCompany_Deleted_Last(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompany_Deleted_Last(companyId, deleted,
			orderByComparator);
	}

	/**
	* Returns the unused files before and after the current unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param unusedFileId the primary key of the current unused file
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile[] findByCompany_Deleted_PrevAndNext(
		long unusedFileId, long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompany_Deleted_PrevAndNext(unusedFileId, companyId,
			deleted, orderByComparator);
	}

	/**
	* Removes all the unused files where companyId = &#63; and deleted = &#63; from the database.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompany_Deleted(long companyId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompany_Deleted(companyId, deleted);
	}

	/**
	* Returns the number of unused files where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @return the number of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompany_Deleted(long companyId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompany_Deleted(companyId, deleted);
	}

	/**
	* Caches the unused file in the entity cache if it is enabled.
	*
	* @param unusedFile the unused file
	*/
	public static void cacheResult(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile) {
		getPersistence().cacheResult(unusedFile);
	}

	/**
	* Caches the unused files in the entity cache if it is enabled.
	*
	* @param unusedFiles the unused files
	*/
	public static void cacheResult(
		java.util.List<com.liferay.dl.cleaner.model.UnusedFile> unusedFiles) {
		getPersistence().cacheResult(unusedFiles);
	}

	/**
	* Creates a new unused file with the primary key. Does not add the unused file to the database.
	*
	* @param unusedFileId the primary key for the new unused file
	* @return the new unused file
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile create(
		long unusedFileId) {
		return getPersistence().create(unusedFileId);
	}

	/**
	* Removes the unused file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file that was removed
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile remove(
		long unusedFileId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(unusedFileId);
	}

	public static com.liferay.dl.cleaner.model.UnusedFile updateImpl(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(unusedFile);
	}

	/**
	* Returns the unused file with the primary key or throws a {@link com.liferay.dl.cleaner.NoSuchUnusedFileException} if it could not be found.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile findByPrimaryKey(
		long unusedFileId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(unusedFileId);
	}

	/**
	* Returns the unused file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file, or <code>null</code> if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.dl.cleaner.model.UnusedFile fetchByPrimaryKey(
		long unusedFileId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(unusedFileId);
	}

	/**
	* Returns all the unused files.
	*
	* @return the unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the unused files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of unused files
	* @param end the upper bound of the range of unused files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of unused files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the unused files from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of unused files.
	*
	* @return the number of unused files
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static UnusedFilePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UnusedFilePersistence)PortletBeanLocatorUtil.locate(com.liferay.dl.cleaner.service.ClpSerializer.getServletContextName(),
					UnusedFilePersistence.class.getName());

			ReferenceRegistry.registerReference(UnusedFileUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(UnusedFilePersistence persistence) {
	}

	private static UnusedFilePersistence _persistence;
}