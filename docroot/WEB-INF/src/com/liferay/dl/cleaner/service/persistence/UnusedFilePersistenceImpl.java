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

import com.liferay.dl.cleaner.NoSuchUnusedFileException;
import com.liferay.dl.cleaner.model.UnusedFile;
import com.liferay.dl.cleaner.model.impl.UnusedFileImpl;
import com.liferay.dl.cleaner.model.impl.UnusedFileModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the unused file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author guywandji
 * @see UnusedFilePersistence
 * @see UnusedFileUtil
 * @generated
 */
public class UnusedFilePersistenceImpl extends BasePersistenceImpl<UnusedFile>
	implements UnusedFilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UnusedFileUtil} to access the unused file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UnusedFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID =
		new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByGroup_FileEntryId_VersionId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			UnusedFileModelImpl.GROUPID_COLUMN_BITMASK |
			UnusedFileModelImpl.FILEENTRYID_COLUMN_BITMASK |
			UnusedFileModelImpl.DLFILEVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID =
		new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroup_FileEntryId_VersionId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

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
	@Override
	public UnusedFile findByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = fetchByGroup_FileEntryId_VersionId(groupId,
				fileEntryId, dlFileVersionId);

		if (unusedFile == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", fileEntryId=");
			msg.append(fileEntryId);

			msg.append(", dlFileVersionId=");
			msg.append(dlFileVersionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUnusedFileException(msg.toString());
		}

		return unusedFile;
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
	@Override
	public UnusedFile fetchByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId) throws SystemException {
		return fetchByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId, true);
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
	@Override
	public UnusedFile fetchByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, fileEntryId, dlFileVersionId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
					finderArgs, this);
		}

		if (result instanceof UnusedFile) {
			UnusedFile unusedFile = (UnusedFile)result;

			if ((groupId != unusedFile.getGroupId()) ||
					(fileEntryId != unusedFile.getFileEntryId()) ||
					(dlFileVersionId != unusedFile.getDlFileVersionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_UNUSEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_DLFILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(fileEntryId);

				qPos.add(dlFileVersionId);

				List<UnusedFile> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
						finderArgs, list);
				}
				else {
					UnusedFile unusedFile = list.get(0);

					result = unusedFile;

					cacheResult(unusedFile);

					if ((unusedFile.getGroupId() != groupId) ||
							(unusedFile.getFileEntryId() != fileEntryId) ||
							(unusedFile.getDlFileVersionId() != dlFileVersionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
							finderArgs, unusedFile);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (UnusedFile)result;
		}
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
	@Override
	public UnusedFile removeByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = findByGroup_FileEntryId_VersionId(groupId,
				fileEntryId, dlFileVersionId);

		return remove(unusedFile);
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
	@Override
	public int countByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID;

		Object[] finderArgs = new Object[] { groupId, fileEntryId, dlFileVersionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_UNUSEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_DLFILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(fileEntryId);

				qPos.add(dlFileVersionId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_GROUPID_2 =
		"unusedFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_FILEENTRYID_2 =
		"unusedFile.fileEntryId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_DLFILEVERSIONID_2 =
		"unusedFile.dlFileVersionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUP_DELETED =
		new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroup_Deleted",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED =
		new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroup_Deleted",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			UnusedFileModelImpl.GROUPID_COLUMN_BITMASK |
			UnusedFileModelImpl.DELETED_COLUMN_BITMASK |
			UnusedFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUP_DELETED = new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroup_Deleted",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the unused files where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @return the matching unused files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UnusedFile> findByGroup_Deleted(long groupId, boolean deleted)
		throws SystemException {
		return findByGroup_Deleted(groupId, deleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<UnusedFile> findByGroup_Deleted(long groupId, boolean deleted,
		int start, int end) throws SystemException {
		return findByGroup_Deleted(groupId, deleted, start, end, null);
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
	@Override
	public List<UnusedFile> findByGroup_Deleted(long groupId, boolean deleted,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED;
			finderArgs = new Object[] { groupId, deleted };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUP_DELETED;
			finderArgs = new Object[] {
					groupId, deleted,
					
					start, end, orderByComparator
				};
		}

		List<UnusedFile> list = (List<UnusedFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UnusedFile unusedFile : list) {
				if ((groupId != unusedFile.getGroupId()) ||
						(deleted != unusedFile.getDeleted())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_UNUSEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_DELETED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUP_DELETED_DELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UnusedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(deleted);

				if (!pagination) {
					list = (List<UnusedFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UnusedFile>(list);
				}
				else {
					list = (List<UnusedFile>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public UnusedFile findByGroup_Deleted_First(long groupId, boolean deleted,
		OrderByComparator orderByComparator)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = fetchByGroup_Deleted_First(groupId, deleted,
				orderByComparator);

		if (unusedFile != null) {
			return unusedFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUnusedFileException(msg.toString());
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
	@Override
	public UnusedFile fetchByGroup_Deleted_First(long groupId, boolean deleted,
		OrderByComparator orderByComparator) throws SystemException {
		List<UnusedFile> list = findByGroup_Deleted(groupId, deleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UnusedFile findByGroup_Deleted_Last(long groupId, boolean deleted,
		OrderByComparator orderByComparator)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = fetchByGroup_Deleted_Last(groupId, deleted,
				orderByComparator);

		if (unusedFile != null) {
			return unusedFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUnusedFileException(msg.toString());
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
	@Override
	public UnusedFile fetchByGroup_Deleted_Last(long groupId, boolean deleted,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroup_Deleted(groupId, deleted);

		if (count == 0) {
			return null;
		}

		List<UnusedFile> list = findByGroup_Deleted(groupId, deleted,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UnusedFile[] findByGroup_Deleted_PrevAndNext(long unusedFileId,
		long groupId, boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = findByPrimaryKey(unusedFileId);

		Session session = null;

		try {
			session = openSession();

			UnusedFile[] array = new UnusedFileImpl[3];

			array[0] = getByGroup_Deleted_PrevAndNext(session, unusedFile,
					groupId, deleted, orderByComparator, true);

			array[1] = unusedFile;

			array[2] = getByGroup_Deleted_PrevAndNext(session, unusedFile,
					groupId, deleted, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UnusedFile getByGroup_Deleted_PrevAndNext(Session session,
		UnusedFile unusedFile, long groupId, boolean deleted,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_UNUSEDFILE_WHERE);

		query.append(_FINDER_COLUMN_GROUP_DELETED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUP_DELETED_DELETED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(UnusedFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(deleted);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(unusedFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UnusedFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the unused files where groupId = &#63; and deleted = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroup_Deleted(long groupId, boolean deleted)
		throws SystemException {
		for (UnusedFile unusedFile : findByGroup_Deleted(groupId, deleted,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(unusedFile);
		}
	}

	/**
	 * Returns the number of unused files where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @return the number of matching unused files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroup_Deleted(long groupId, boolean deleted)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUP_DELETED;

		Object[] finderArgs = new Object[] { groupId, deleted };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_UNUSEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_DELETED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUP_DELETED_DELETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(deleted);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUP_DELETED_GROUPID_2 = "unusedFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_DELETED_DELETED_2 = "unusedFile.deleted = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANY_DELETED =
		new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompany_Deleted",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_DELETED =
		new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, UnusedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompany_Deleted",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			UnusedFileModelImpl.COMPANYID_COLUMN_BITMASK |
			UnusedFileModelImpl.DELETED_COLUMN_BITMASK |
			UnusedFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANY_DELETED = new FinderPath(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompany_Deleted",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the unused files where companyId = &#63; and deleted = &#63;.
	 *
	 * @param companyId the company ID
	 * @param deleted the deleted
	 * @return the matching unused files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UnusedFile> findByCompany_Deleted(long companyId,
		boolean deleted) throws SystemException {
		return findByCompany_Deleted(companyId, deleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<UnusedFile> findByCompany_Deleted(long companyId,
		boolean deleted, int start, int end) throws SystemException {
		return findByCompany_Deleted(companyId, deleted, start, end, null);
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
	@Override
	public List<UnusedFile> findByCompany_Deleted(long companyId,
		boolean deleted, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_DELETED;
			finderArgs = new Object[] { companyId, deleted };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANY_DELETED;
			finderArgs = new Object[] {
					companyId, deleted,
					
					start, end, orderByComparator
				};
		}

		List<UnusedFile> list = (List<UnusedFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UnusedFile unusedFile : list) {
				if ((companyId != unusedFile.getCompanyId()) ||
						(deleted != unusedFile.getDeleted())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_UNUSEDFILE_WHERE);

			query.append(_FINDER_COLUMN_COMPANY_DELETED_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANY_DELETED_DELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UnusedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(deleted);

				if (!pagination) {
					list = (List<UnusedFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UnusedFile>(list);
				}
				else {
					list = (List<UnusedFile>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public UnusedFile findByCompany_Deleted_First(long companyId,
		boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = fetchByCompany_Deleted_First(companyId,
				deleted, orderByComparator);

		if (unusedFile != null) {
			return unusedFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUnusedFileException(msg.toString());
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
	@Override
	public UnusedFile fetchByCompany_Deleted_First(long companyId,
		boolean deleted, OrderByComparator orderByComparator)
		throws SystemException {
		List<UnusedFile> list = findByCompany_Deleted(companyId, deleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UnusedFile findByCompany_Deleted_Last(long companyId,
		boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = fetchByCompany_Deleted_Last(companyId, deleted,
				orderByComparator);

		if (unusedFile != null) {
			return unusedFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUnusedFileException(msg.toString());
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
	@Override
	public UnusedFile fetchByCompany_Deleted_Last(long companyId,
		boolean deleted, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCompany_Deleted(companyId, deleted);

		if (count == 0) {
			return null;
		}

		List<UnusedFile> list = findByCompany_Deleted(companyId, deleted,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UnusedFile[] findByCompany_Deleted_PrevAndNext(long unusedFileId,
		long companyId, boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = findByPrimaryKey(unusedFileId);

		Session session = null;

		try {
			session = openSession();

			UnusedFile[] array = new UnusedFileImpl[3];

			array[0] = getByCompany_Deleted_PrevAndNext(session, unusedFile,
					companyId, deleted, orderByComparator, true);

			array[1] = unusedFile;

			array[2] = getByCompany_Deleted_PrevAndNext(session, unusedFile,
					companyId, deleted, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UnusedFile getByCompany_Deleted_PrevAndNext(Session session,
		UnusedFile unusedFile, long companyId, boolean deleted,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_UNUSEDFILE_WHERE);

		query.append(_FINDER_COLUMN_COMPANY_DELETED_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANY_DELETED_DELETED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(UnusedFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(deleted);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(unusedFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UnusedFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the unused files where companyId = &#63; and deleted = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param deleted the deleted
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompany_Deleted(long companyId, boolean deleted)
		throws SystemException {
		for (UnusedFile unusedFile : findByCompany_Deleted(companyId, deleted,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(unusedFile);
		}
	}

	/**
	 * Returns the number of unused files where companyId = &#63; and deleted = &#63;.
	 *
	 * @param companyId the company ID
	 * @param deleted the deleted
	 * @return the number of matching unused files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompany_Deleted(long companyId, boolean deleted)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANY_DELETED;

		Object[] finderArgs = new Object[] { companyId, deleted };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_UNUSEDFILE_WHERE);

			query.append(_FINDER_COLUMN_COMPANY_DELETED_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANY_DELETED_DELETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(deleted);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANY_DELETED_COMPANYID_2 = "unusedFile.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANY_DELETED_DELETED_2 = "unusedFile.deleted = ?";

	public UnusedFilePersistenceImpl() {
		setModelClass(UnusedFile.class);
	}

	/**
	 * Caches the unused file in the entity cache if it is enabled.
	 *
	 * @param unusedFile the unused file
	 */
	@Override
	public void cacheResult(UnusedFile unusedFile) {
		EntityCacheUtil.putResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileImpl.class, unusedFile.getPrimaryKey(), unusedFile);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
			new Object[] {
				unusedFile.getGroupId(), unusedFile.getFileEntryId(),
				unusedFile.getDlFileVersionId()
			}, unusedFile);

		unusedFile.resetOriginalValues();
	}

	/**
	 * Caches the unused files in the entity cache if it is enabled.
	 *
	 * @param unusedFiles the unused files
	 */
	@Override
	public void cacheResult(List<UnusedFile> unusedFiles) {
		for (UnusedFile unusedFile : unusedFiles) {
			if (EntityCacheUtil.getResult(
						UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
						UnusedFileImpl.class, unusedFile.getPrimaryKey()) == null) {
				cacheResult(unusedFile);
			}
			else {
				unusedFile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all unused files.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UnusedFileImpl.class.getName());
		}

		EntityCacheUtil.clearCache(UnusedFileImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the unused file.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UnusedFile unusedFile) {
		EntityCacheUtil.removeResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileImpl.class, unusedFile.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(unusedFile);
	}

	@Override
	public void clearCache(List<UnusedFile> unusedFiles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UnusedFile unusedFile : unusedFiles) {
			EntityCacheUtil.removeResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
				UnusedFileImpl.class, unusedFile.getPrimaryKey());

			clearUniqueFindersCache(unusedFile);
		}
	}

	protected void cacheUniqueFindersCache(UnusedFile unusedFile) {
		if (unusedFile.isNew()) {
			Object[] args = new Object[] {
					unusedFile.getGroupId(), unusedFile.getFileEntryId(),
					unusedFile.getDlFileVersionId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
				args, unusedFile);
		}
		else {
			UnusedFileModelImpl unusedFileModelImpl = (UnusedFileModelImpl)unusedFile;

			if ((unusedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						unusedFile.getGroupId(), unusedFile.getFileEntryId(),
						unusedFile.getDlFileVersionId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
					args, unusedFile);
			}
		}
	}

	protected void clearUniqueFindersCache(UnusedFile unusedFile) {
		UnusedFileModelImpl unusedFileModelImpl = (UnusedFileModelImpl)unusedFile;

		Object[] args = new Object[] {
				unusedFile.getGroupId(), unusedFile.getFileEntryId(),
				unusedFile.getDlFileVersionId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
			args);

		if ((unusedFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID.getColumnBitmask()) != 0) {
			args = new Object[] {
					unusedFileModelImpl.getOriginalGroupId(),
					unusedFileModelImpl.getOriginalFileEntryId(),
					unusedFileModelImpl.getOriginalDlFileVersionId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
				args);
		}
	}

	/**
	 * Creates a new unused file with the primary key. Does not add the unused file to the database.
	 *
	 * @param unusedFileId the primary key for the new unused file
	 * @return the new unused file
	 */
	@Override
	public UnusedFile create(long unusedFileId) {
		UnusedFile unusedFile = new UnusedFileImpl();

		unusedFile.setNew(true);
		unusedFile.setPrimaryKey(unusedFileId);

		return unusedFile;
	}

	/**
	 * Removes the unused file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param unusedFileId the primary key of the unused file
	 * @return the unused file that was removed
	 * @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UnusedFile remove(long unusedFileId)
		throws NoSuchUnusedFileException, SystemException {
		return remove((Serializable)unusedFileId);
	}

	/**
	 * Removes the unused file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the unused file
	 * @return the unused file that was removed
	 * @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UnusedFile remove(Serializable primaryKey)
		throws NoSuchUnusedFileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UnusedFile unusedFile = (UnusedFile)session.get(UnusedFileImpl.class,
					primaryKey);

			if (unusedFile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUnusedFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(unusedFile);
		}
		catch (NoSuchUnusedFileException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected UnusedFile removeImpl(UnusedFile unusedFile)
		throws SystemException {
		unusedFile = toUnwrappedModel(unusedFile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(unusedFile)) {
				unusedFile = (UnusedFile)session.get(UnusedFileImpl.class,
						unusedFile.getPrimaryKeyObj());
			}

			if (unusedFile != null) {
				session.delete(unusedFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (unusedFile != null) {
			clearCache(unusedFile);
		}

		return unusedFile;
	}

	@Override
	public UnusedFile updateImpl(
		com.liferay.dl.cleaner.model.UnusedFile unusedFile)
		throws SystemException {
		unusedFile = toUnwrappedModel(unusedFile);

		boolean isNew = unusedFile.isNew();

		UnusedFileModelImpl unusedFileModelImpl = (UnusedFileModelImpl)unusedFile;

		Session session = null;

		try {
			session = openSession();

			if (unusedFile.isNew()) {
				session.save(unusedFile);

				unusedFile.setNew(false);
			}
			else {
				session.merge(unusedFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UnusedFileModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((unusedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						unusedFileModelImpl.getOriginalGroupId(),
						unusedFileModelImpl.getOriginalDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_DELETED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED,
					args);

				args = new Object[] {
						unusedFileModelImpl.getGroupId(),
						unusedFileModelImpl.getDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_DELETED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED,
					args);
			}

			if ((unusedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_DELETED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						unusedFileModelImpl.getOriginalCompanyId(),
						unusedFileModelImpl.getOriginalDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANY_DELETED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_DELETED,
					args);

				args = new Object[] {
						unusedFileModelImpl.getCompanyId(),
						unusedFileModelImpl.getDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANY_DELETED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_DELETED,
					args);
			}
		}

		EntityCacheUtil.putResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
			UnusedFileImpl.class, unusedFile.getPrimaryKey(), unusedFile);

		clearUniqueFindersCache(unusedFile);
		cacheUniqueFindersCache(unusedFile);

		return unusedFile;
	}

	protected UnusedFile toUnwrappedModel(UnusedFile unusedFile) {
		if (unusedFile instanceof UnusedFileImpl) {
			return unusedFile;
		}

		UnusedFileImpl unusedFileImpl = new UnusedFileImpl();

		unusedFileImpl.setNew(unusedFile.isNew());
		unusedFileImpl.setPrimaryKey(unusedFile.getPrimaryKey());

		unusedFileImpl.setUnusedFileId(unusedFile.getUnusedFileId());
		unusedFileImpl.setGroupId(unusedFile.getGroupId());
		unusedFileImpl.setCompanyId(unusedFile.getCompanyId());
		unusedFileImpl.setUserId(unusedFile.getUserId());
		unusedFileImpl.setUserName(unusedFile.getUserName());
		unusedFileImpl.setCreateDate(unusedFile.getCreateDate());
		unusedFileImpl.setModifiedDate(unusedFile.getModifiedDate());
		unusedFileImpl.setFileEntryId(unusedFile.getFileEntryId());
		unusedFileImpl.setDlFileVersionId(unusedFile.getDlFileVersionId());
		unusedFileImpl.setDlFileTitle(unusedFile.getDlFileTitle());
		unusedFileImpl.setDlFileUuId(unusedFile.getDlFileUuId());
		unusedFileImpl.setDeleted(unusedFile.isDeleted());
		unusedFileImpl.setComment(unusedFile.getComment());

		return unusedFileImpl;
	}

	/**
	 * Returns the unused file with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the unused file
	 * @return the unused file
	 * @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UnusedFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUnusedFileException, SystemException {
		UnusedFile unusedFile = fetchByPrimaryKey(primaryKey);

		if (unusedFile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUnusedFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return unusedFile;
	}

	/**
	 * Returns the unused file with the primary key or throws a {@link com.liferay.dl.cleaner.NoSuchUnusedFileException} if it could not be found.
	 *
	 * @param unusedFileId the primary key of the unused file
	 * @return the unused file
	 * @throws com.liferay.dl.cleaner.NoSuchUnusedFileException if a unused file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UnusedFile findByPrimaryKey(long unusedFileId)
		throws NoSuchUnusedFileException, SystemException {
		return findByPrimaryKey((Serializable)unusedFileId);
	}

	/**
	 * Returns the unused file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the unused file
	 * @return the unused file, or <code>null</code> if a unused file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UnusedFile fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		UnusedFile unusedFile = (UnusedFile)EntityCacheUtil.getResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
				UnusedFileImpl.class, primaryKey);

		if (unusedFile == _nullUnusedFile) {
			return null;
		}

		if (unusedFile == null) {
			Session session = null;

			try {
				session = openSession();

				unusedFile = (UnusedFile)session.get(UnusedFileImpl.class,
						primaryKey);

				if (unusedFile != null) {
					cacheResult(unusedFile);
				}
				else {
					EntityCacheUtil.putResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
						UnusedFileImpl.class, primaryKey, _nullUnusedFile);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(UnusedFileModelImpl.ENTITY_CACHE_ENABLED,
					UnusedFileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return unusedFile;
	}

	/**
	 * Returns the unused file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param unusedFileId the primary key of the unused file
	 * @return the unused file, or <code>null</code> if a unused file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UnusedFile fetchByPrimaryKey(long unusedFileId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)unusedFileId);
	}

	/**
	 * Returns all the unused files.
	 *
	 * @return the unused files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UnusedFile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<UnusedFile> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<UnusedFile> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<UnusedFile> list = (List<UnusedFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_UNUSEDFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_UNUSEDFILE;

				if (pagination) {
					sql = sql.concat(UnusedFileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UnusedFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UnusedFile>(list);
				}
				else {
					list = (List<UnusedFile>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the unused files from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (UnusedFile unusedFile : findAll()) {
			remove(unusedFile);
		}
	}

	/**
	 * Returns the number of unused files.
	 *
	 * @return the number of unused files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_UNUSEDFILE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the unused file persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.dl.cleaner.model.UnusedFile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UnusedFile>> listenersList = new ArrayList<ModelListener<UnusedFile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<UnusedFile>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(UnusedFileImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_UNUSEDFILE = "SELECT unusedFile FROM UnusedFile unusedFile";
	private static final String _SQL_SELECT_UNUSEDFILE_WHERE = "SELECT unusedFile FROM UnusedFile unusedFile WHERE ";
	private static final String _SQL_COUNT_UNUSEDFILE = "SELECT COUNT(unusedFile) FROM UnusedFile unusedFile";
	private static final String _SQL_COUNT_UNUSEDFILE_WHERE = "SELECT COUNT(unusedFile) FROM UnusedFile unusedFile WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "unusedFile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UnusedFile exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UnusedFile exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UnusedFilePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"comment"
			});
	private static UnusedFile _nullUnusedFile = new UnusedFileImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UnusedFile> toCacheModel() {
				return _nullUnusedFileCacheModel;
			}
		};

	private static CacheModel<UnusedFile> _nullUnusedFileCacheModel = new CacheModel<UnusedFile>() {
			@Override
			public UnusedFile toEntityModel() {
				return _nullUnusedFile;
			}
		};
}