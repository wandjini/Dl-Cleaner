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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the unused file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author guywandji
 * @see UnusedFilePersistenceImpl
 * @see UnusedFileUtil
 * @generated
 */
public interface UnusedFilePersistence extends BasePersistence<UnusedFile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UnusedFileUtil} to access the unused file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

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
	public com.liferay.dl.cleaner.model.UnusedFile findByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the unused file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the unused file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the unused file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile removeByGroup_FileEntryId_VersionId(
		long groupId, long fileEntryId, long dlFileVersionId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of unused files where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63;.
	*
	* @param groupId the group ID
	* @param fileEntryId the file entry ID
	* @param dlFileVersionId the dl file version ID
	* @return the number of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the unused files where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @return the matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByGroup_Deleted(
		long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByGroup_Deleted(
		long groupId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByGroup_Deleted(
		long groupId, boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile findByGroup_Deleted_First(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_Deleted_First(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile findByGroup_Deleted_Last(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last unused file in the ordered set where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile fetchByGroup_Deleted_Last(
		long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile[] findByGroup_Deleted_PrevAndNext(
		long unusedFileId, long groupId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the unused files where groupId = &#63; and deleted = &#63; from the database.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroup_Deleted(long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of unused files where groupId = &#63; and deleted = &#63;.
	*
	* @param groupId the group ID
	* @param deleted the deleted
	* @return the number of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroup_Deleted(long groupId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the unused files where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @return the matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByCompany_Deleted(
		long companyId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByCompany_Deleted(
		long companyId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findByCompany_Deleted(
		long companyId, boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile findByCompany_Deleted_First(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile fetchByCompany_Deleted_First(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile findByCompany_Deleted_Last(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last unused file in the ordered set where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching unused file, or <code>null</code> if a matching unused file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile fetchByCompany_Deleted_Last(
		long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.dl.cleaner.model.UnusedFile[] findByCompany_Deleted_PrevAndNext(
		long unusedFileId, long companyId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the unused files where companyId = &#63; and deleted = &#63; from the database.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompany_Deleted(long companyId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of unused files where companyId = &#63; and deleted = &#63;.
	*
	* @param companyId the company ID
	* @param deleted the deleted
	* @return the number of matching unused files
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompany_Deleted(long companyId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the unused file in the entity cache if it is enabled.
	*
	* @param unusedFile the unused file
	*/
	public void cacheResult(com.liferay.dl.cleaner.model.UnusedFile unusedFile);

	/**
	* Caches the unused files in the entity cache if it is enabled.
	*
	* @param unusedFiles the unused files
	*/
	public void cacheResult(
		java.util.List<com.liferay.dl.cleaner.model.UnusedFile> unusedFiles);

	/**
	* Creates a new unused file with the primary key. Does not add the unused file to the database.
	*
	* @param unusedFileId the primary key for the new unused file
	* @return the new unused file
	*/
	public com.liferay.dl.cleaner.model.UnusedFile create(long unusedFileId);

	/**
	* Removes the unused file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file that was removed
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile remove(long unusedFileId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.dl.cleaner.model.UnusedFile updateImpl(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the unused file with the primary key or throws a {@link com.liferay.dl.cleaner.NoSuchUnusedFileException} if it could not be found.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file
	* @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile findByPrimaryKey(
		long unusedFileId)
		throws com.liferay.dl.cleaner.NoSuchUnusedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the unused file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param unusedFileId the primary key of the unused file
	* @return the unused file, or <code>null</code> if a unused file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.UnusedFile fetchByPrimaryKey(
		long unusedFileId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the unused files.
	*
	* @return the unused files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.UnusedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the unused files from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of unused files.
	*
	* @return the number of unused files
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}