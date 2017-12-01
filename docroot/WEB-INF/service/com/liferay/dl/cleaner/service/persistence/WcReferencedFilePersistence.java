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

import com.liferay.dl.cleaner.model.WcReferencedFile;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the wc referenced file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author guywandji
 * @see WcReferencedFilePersistenceImpl
 * @see WcReferencedFileUtil
 * @generated
 */
public interface WcReferencedFilePersistence extends BasePersistence<WcReferencedFile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WcReferencedFileUtil} to access the wc referenced file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the wc referenced file where groupId = &#63; and dlFileUuId = &#63; or throws a {@link com.liferay.dl.cleaner.NoSuchWcReferencedFileException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dlFileUuId the dl file uu ID
	* @return the matching wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByGroup_dlFileUuId(
		long groupId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file where groupId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dlFileUuId the dl file uu ID
	* @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByGroup_dlFileUuId(
		long groupId, java.lang.String dlFileUuId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file where groupId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dlFileUuId the dl file uu ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByGroup_dlFileUuId(
		long groupId, java.lang.String dlFileUuId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the wc referenced file where groupId = &#63; and dlFileUuId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dlFileUuId the dl file uu ID
	* @return the wc referenced file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile removeByGroup_dlFileUuId(
		long groupId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wc referenced files where groupId = &#63; and dlFileUuId = &#63;.
	*
	* @param groupId the group ID
	* @param dlFileUuId the dl file uu ID
	* @return the number of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroup_dlFileUuId(long groupId, java.lang.String dlFileUuId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the wc referenced files where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findByGroup(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wc referenced files where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wc referenced files
	* @param end the upper bound of the range of wc referenced files (not inclusive)
	* @return the range of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findByGroup(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wc referenced files where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wc referenced files
	* @param end the upper bound of the range of wc referenced files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findByGroup(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wc referenced file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByGroup_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wc referenced file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByGroup_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last wc referenced file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByGroup_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last wc referenced file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByGroup_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced files before and after the current wc referenced file in the ordered set where groupId = &#63;.
	*
	* @param wcRefencedFileId the primary key of the current wc referenced file
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile[] findByGroup_PrevAndNext(
		long wcRefencedFileId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wc referenced files where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroup(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wc referenced files where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroup(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file where companyId = &#63; and dlFileUuId = &#63; or throws a {@link com.liferay.dl.cleaner.NoSuchWcReferencedFileException} if it could not be found.
	*
	* @param companyId the company ID
	* @param dlFileUuId the dl file uu ID
	* @return the matching wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByCompany_DlFileUuId(
		long companyId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file where companyId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param dlFileUuId the dl file uu ID
	* @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByCompany_DlFileUuId(
		long companyId, java.lang.String dlFileUuId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file where companyId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param dlFileUuId the dl file uu ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByCompany_DlFileUuId(
		long companyId, java.lang.String dlFileUuId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the wc referenced file where companyId = &#63; and dlFileUuId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param dlFileUuId the dl file uu ID
	* @return the wc referenced file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile removeByCompany_DlFileUuId(
		long companyId, java.lang.String dlFileUuId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wc referenced files where companyId = &#63; and dlFileUuId = &#63;.
	*
	* @param companyId the company ID
	* @param dlFileUuId the dl file uu ID
	* @return the number of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompany_DlFileUuId(long companyId,
		java.lang.String dlFileUuId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the wc referenced files where companyId = &#63; and orphan = &#63;.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @return the matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findByCompany_Orphan(
		long companyId, boolean orphan)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wc referenced files where companyId = &#63; and orphan = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @param start the lower bound of the range of wc referenced files
	* @param end the upper bound of the range of wc referenced files (not inclusive)
	* @return the range of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findByCompany_Orphan(
		long companyId, boolean orphan, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wc referenced files where companyId = &#63; and orphan = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @param start the lower bound of the range of wc referenced files
	* @param end the upper bound of the range of wc referenced files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findByCompany_Orphan(
		long companyId, boolean orphan, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByCompany_Orphan_First(
		long companyId, boolean orphan,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByCompany_Orphan_First(
		long companyId, boolean orphan,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByCompany_Orphan_Last(
		long companyId, boolean orphan,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByCompany_Orphan_Last(
		long companyId, boolean orphan,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced files before and after the current wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	*
	* @param wcRefencedFileId the primary key of the current wc referenced file
	* @param companyId the company ID
	* @param orphan the orphan
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile[] findByCompany_Orphan_PrevAndNext(
		long wcRefencedFileId, long companyId, boolean orphan,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wc referenced files where companyId = &#63; and orphan = &#63; from the database.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompany_Orphan(long companyId, boolean orphan)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wc referenced files where companyId = &#63; and orphan = &#63;.
	*
	* @param companyId the company ID
	* @param orphan the orphan
	* @return the number of matching wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompany_Orphan(long companyId, boolean orphan)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the wc referenced file in the entity cache if it is enabled.
	*
	* @param wcReferencedFile the wc referenced file
	*/
	public void cacheResult(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile);

	/**
	* Caches the wc referenced files in the entity cache if it is enabled.
	*
	* @param wcReferencedFiles the wc referenced files
	*/
	public void cacheResult(
		java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> wcReferencedFiles);

	/**
	* Creates a new wc referenced file with the primary key. Does not add the wc referenced file to the database.
	*
	* @param wcRefencedFileId the primary key for the new wc referenced file
	* @return the new wc referenced file
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile create(
		long wcRefencedFileId);

	/**
	* Removes the wc referenced file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file that was removed
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile remove(
		long wcRefencedFileId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.dl.cleaner.model.WcReferencedFile updateImpl(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file with the primary key or throws a {@link com.liferay.dl.cleaner.NoSuchWcReferencedFileException} if it could not be found.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file
	* @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile findByPrimaryKey(
		long wcRefencedFileId)
		throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wc referenced file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wcRefencedFileId the primary key of the wc referenced file
	* @return the wc referenced file, or <code>null</code> if a wc referenced file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.dl.cleaner.model.WcReferencedFile fetchByPrimaryKey(
		long wcRefencedFileId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the wc referenced files.
	*
	* @return the wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wc referenced files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wc referenced files
	* @param end the upper bound of the range of wc referenced files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.dl.cleaner.model.WcReferencedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wc referenced files from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wc referenced files.
	*
	* @return the number of wc referenced files
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}