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

import com.liferay.dl.cleaner.NoSuchLostFileException;
import com.liferay.dl.cleaner.model.LostFile;
import com.liferay.dl.cleaner.model.impl.LostFileImpl;
import com.liferay.dl.cleaner.model.impl.LostFileModelImpl;

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
 * The persistence implementation for the lost file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author guywandji
 * @see LostFilePersistence
 * @see LostFileUtil
 * @generated
 */
public class LostFilePersistenceImpl extends BasePersistenceImpl<LostFile>
	implements LostFilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LostFileUtil} to access the lost file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LostFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, LostFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, LostFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID =
		new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, LostFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByGroup_FileEntryId_VersionId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LostFileModelImpl.GROUPID_COLUMN_BITMASK |
			LostFileModelImpl.FILEENTRYID_COLUMN_BITMASK |
			LostFileModelImpl.DLFILEVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID =
		new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroup_FileEntryId_VersionId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the lost file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or throws a {@link com.liferay.dl.cleaner.NoSuchLostFileException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param fileEntryId the file entry ID
	 * @param dlFileVersionId the dl file version ID
	 * @return the matching lost file
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile findByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId)
		throws NoSuchLostFileException, SystemException {
		LostFile lostFile = fetchByGroup_FileEntryId_VersionId(groupId,
				fileEntryId, dlFileVersionId);

		if (lostFile == null) {
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

			throw new NoSuchLostFileException(msg.toString());
		}

		return lostFile;
	}

	/**
	 * Returns the lost file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fileEntryId the file entry ID
	 * @param dlFileVersionId the dl file version ID
	 * @return the matching lost file, or <code>null</code> if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile fetchByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId) throws SystemException {
		return fetchByGroup_FileEntryId_VersionId(groupId, fileEntryId,
			dlFileVersionId, true);
	}

	/**
	 * Returns the lost file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fileEntryId the file entry ID
	 * @param dlFileVersionId the dl file version ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching lost file, or <code>null</code> if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile fetchByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, fileEntryId, dlFileVersionId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
					finderArgs, this);
		}

		if (result instanceof LostFile) {
			LostFile lostFile = (LostFile)result;

			if ((groupId != lostFile.getGroupId()) ||
					(fileEntryId != lostFile.getFileEntryId()) ||
					(dlFileVersionId != lostFile.getDlFileVersionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LOSTFILE_WHERE);

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

				List<LostFile> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
						finderArgs, list);
				}
				else {
					LostFile lostFile = list.get(0);

					result = lostFile;

					cacheResult(lostFile);

					if ((lostFile.getGroupId() != groupId) ||
							(lostFile.getFileEntryId() != fileEntryId) ||
							(lostFile.getDlFileVersionId() != dlFileVersionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
							finderArgs, lostFile);
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
			return (LostFile)result;
		}
	}

	/**
	 * Removes the lost file where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fileEntryId the file entry ID
	 * @param dlFileVersionId the dl file version ID
	 * @return the lost file that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile removeByGroup_FileEntryId_VersionId(long groupId,
		long fileEntryId, long dlFileVersionId)
		throws NoSuchLostFileException, SystemException {
		LostFile lostFile = findByGroup_FileEntryId_VersionId(groupId,
				fileEntryId, dlFileVersionId);

		return remove(lostFile);
	}

	/**
	 * Returns the number of lost files where groupId = &#63; and fileEntryId = &#63; and dlFileVersionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fileEntryId the file entry ID
	 * @param dlFileVersionId the dl file version ID
	 * @return the number of matching lost files
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

			query.append(_SQL_COUNT_LOSTFILE_WHERE);

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
		"lostFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_FILEENTRYID_2 =
		"lostFile.fileEntryId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_FILEENTRYID_VERSIONID_DLFILEVERSIONID_2 =
		"lostFile.dlFileVersionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUP_DELETED =
		new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, LostFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroup_Deleted",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED =
		new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, LostFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroup_Deleted",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LostFileModelImpl.GROUPID_COLUMN_BITMASK |
			LostFileModelImpl.DELETED_COLUMN_BITMASK |
			LostFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUP_DELETED = new FinderPath(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroup_Deleted",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the lost files where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @return the matching lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LostFile> findByGroup_Deleted(long groupId, boolean deleted)
		throws SystemException {
		return findByGroup_Deleted(groupId, deleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lost files where groupId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param start the lower bound of the range of lost files
	 * @param end the upper bound of the range of lost files (not inclusive)
	 * @return the range of matching lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LostFile> findByGroup_Deleted(long groupId, boolean deleted,
		int start, int end) throws SystemException {
		return findByGroup_Deleted(groupId, deleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lost files where groupId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param start the lower bound of the range of lost files
	 * @param end the upper bound of the range of lost files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LostFile> findByGroup_Deleted(long groupId, boolean deleted,
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

		List<LostFile> list = (List<LostFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LostFile lostFile : list) {
				if ((groupId != lostFile.getGroupId()) ||
						(deleted != lostFile.getDeleted())) {
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

			query.append(_SQL_SELECT_LOSTFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_DELETED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUP_DELETED_DELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LostFileModelImpl.ORDER_BY_JPQL);
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
					list = (List<LostFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<LostFile>(list);
				}
				else {
					list = (List<LostFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first lost file in the ordered set where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lost file
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile findByGroup_Deleted_First(long groupId, boolean deleted,
		OrderByComparator orderByComparator)
		throws NoSuchLostFileException, SystemException {
		LostFile lostFile = fetchByGroup_Deleted_First(groupId, deleted,
				orderByComparator);

		if (lostFile != null) {
			return lostFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLostFileException(msg.toString());
	}

	/**
	 * Returns the first lost file in the ordered set where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lost file, or <code>null</code> if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile fetchByGroup_Deleted_First(long groupId, boolean deleted,
		OrderByComparator orderByComparator) throws SystemException {
		List<LostFile> list = findByGroup_Deleted(groupId, deleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lost file in the ordered set where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lost file
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile findByGroup_Deleted_Last(long groupId, boolean deleted,
		OrderByComparator orderByComparator)
		throws NoSuchLostFileException, SystemException {
		LostFile lostFile = fetchByGroup_Deleted_Last(groupId, deleted,
				orderByComparator);

		if (lostFile != null) {
			return lostFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLostFileException(msg.toString());
	}

	/**
	 * Returns the last lost file in the ordered set where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lost file, or <code>null</code> if a matching lost file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile fetchByGroup_Deleted_Last(long groupId, boolean deleted,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroup_Deleted(groupId, deleted);

		if (count == 0) {
			return null;
		}

		List<LostFile> list = findByGroup_Deleted(groupId, deleted, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lost files before and after the current lost file in the ordered set where groupId = &#63; and deleted = &#63;.
	 *
	 * @param lostFileId the primary key of the current lost file
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lost file
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile[] findByGroup_Deleted_PrevAndNext(long lostFileId,
		long groupId, boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchLostFileException, SystemException {
		LostFile lostFile = findByPrimaryKey(lostFileId);

		Session session = null;

		try {
			session = openSession();

			LostFile[] array = new LostFileImpl[3];

			array[0] = getByGroup_Deleted_PrevAndNext(session, lostFile,
					groupId, deleted, orderByComparator, true);

			array[1] = lostFile;

			array[2] = getByGroup_Deleted_PrevAndNext(session, lostFile,
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

	protected LostFile getByGroup_Deleted_PrevAndNext(Session session,
		LostFile lostFile, long groupId, boolean deleted,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LOSTFILE_WHERE);

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
			query.append(LostFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(deleted);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lostFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LostFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lost files where groupId = &#63; and deleted = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroup_Deleted(long groupId, boolean deleted)
		throws SystemException {
		for (LostFile lostFile : findByGroup_Deleted(groupId, deleted,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lostFile);
		}
	}

	/**
	 * Returns the number of lost files where groupId = &#63; and deleted = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deleted the deleted
	 * @return the number of matching lost files
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

			query.append(_SQL_COUNT_LOSTFILE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUP_DELETED_GROUPID_2 = "lostFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_DELETED_DELETED_2 = "lostFile.deleted = ?";

	public LostFilePersistenceImpl() {
		setModelClass(LostFile.class);
	}

	/**
	 * Caches the lost file in the entity cache if it is enabled.
	 *
	 * @param lostFile the lost file
	 */
	@Override
	public void cacheResult(LostFile lostFile) {
		EntityCacheUtil.putResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileImpl.class, lostFile.getPrimaryKey(), lostFile);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
			new Object[] {
				lostFile.getGroupId(), lostFile.getFileEntryId(),
				lostFile.getDlFileVersionId()
			}, lostFile);

		lostFile.resetOriginalValues();
	}

	/**
	 * Caches the lost files in the entity cache if it is enabled.
	 *
	 * @param lostFiles the lost files
	 */
	@Override
	public void cacheResult(List<LostFile> lostFiles) {
		for (LostFile lostFile : lostFiles) {
			if (EntityCacheUtil.getResult(
						LostFileModelImpl.ENTITY_CACHE_ENABLED,
						LostFileImpl.class, lostFile.getPrimaryKey()) == null) {
				cacheResult(lostFile);
			}
			else {
				lostFile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all lost files.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LostFileImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LostFileImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the lost file.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LostFile lostFile) {
		EntityCacheUtil.removeResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileImpl.class, lostFile.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(lostFile);
	}

	@Override
	public void clearCache(List<LostFile> lostFiles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LostFile lostFile : lostFiles) {
			EntityCacheUtil.removeResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
				LostFileImpl.class, lostFile.getPrimaryKey());

			clearUniqueFindersCache(lostFile);
		}
	}

	protected void cacheUniqueFindersCache(LostFile lostFile) {
		if (lostFile.isNew()) {
			Object[] args = new Object[] {
					lostFile.getGroupId(), lostFile.getFileEntryId(),
					lostFile.getDlFileVersionId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
				args, lostFile);
		}
		else {
			LostFileModelImpl lostFileModelImpl = (LostFileModelImpl)lostFile;

			if ((lostFileModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lostFile.getGroupId(), lostFile.getFileEntryId(),
						lostFile.getDlFileVersionId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
					args, lostFile);
			}
		}
	}

	protected void clearUniqueFindersCache(LostFile lostFile) {
		LostFileModelImpl lostFileModelImpl = (LostFileModelImpl)lostFile;

		Object[] args = new Object[] {
				lostFile.getGroupId(), lostFile.getFileEntryId(),
				lostFile.getDlFileVersionId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
			args);

		if ((lostFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID.getColumnBitmask()) != 0) {
			args = new Object[] {
					lostFileModelImpl.getOriginalGroupId(),
					lostFileModelImpl.getOriginalFileEntryId(),
					lostFileModelImpl.getOriginalDlFileVersionId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_FILEENTRYID_VERSIONID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_FILEENTRYID_VERSIONID,
				args);
		}
	}

	/**
	 * Creates a new lost file with the primary key. Does not add the lost file to the database.
	 *
	 * @param lostFileId the primary key for the new lost file
	 * @return the new lost file
	 */
	@Override
	public LostFile create(long lostFileId) {
		LostFile lostFile = new LostFileImpl();

		lostFile.setNew(true);
		lostFile.setPrimaryKey(lostFileId);

		return lostFile;
	}

	/**
	 * Removes the lost file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lostFileId the primary key of the lost file
	 * @return the lost file that was removed
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile remove(long lostFileId)
		throws NoSuchLostFileException, SystemException {
		return remove((Serializable)lostFileId);
	}

	/**
	 * Removes the lost file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lost file
	 * @return the lost file that was removed
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile remove(Serializable primaryKey)
		throws NoSuchLostFileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LostFile lostFile = (LostFile)session.get(LostFileImpl.class,
					primaryKey);

			if (lostFile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLostFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lostFile);
		}
		catch (NoSuchLostFileException nsee) {
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
	protected LostFile removeImpl(LostFile lostFile) throws SystemException {
		lostFile = toUnwrappedModel(lostFile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lostFile)) {
				lostFile = (LostFile)session.get(LostFileImpl.class,
						lostFile.getPrimaryKeyObj());
			}

			if (lostFile != null) {
				session.delete(lostFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lostFile != null) {
			clearCache(lostFile);
		}

		return lostFile;
	}

	@Override
	public LostFile updateImpl(com.liferay.dl.cleaner.model.LostFile lostFile)
		throws SystemException {
		lostFile = toUnwrappedModel(lostFile);

		boolean isNew = lostFile.isNew();

		LostFileModelImpl lostFileModelImpl = (LostFileModelImpl)lostFile;

		Session session = null;

		try {
			session = openSession();

			if (lostFile.isNew()) {
				session.save(lostFile);

				lostFile.setNew(false);
			}
			else {
				session.merge(lostFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LostFileModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lostFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lostFileModelImpl.getOriginalGroupId(),
						lostFileModelImpl.getOriginalDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_DELETED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED,
					args);

				args = new Object[] {
						lostFileModelImpl.getGroupId(),
						lostFileModelImpl.getDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_DELETED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP_DELETED,
					args);
			}
		}

		EntityCacheUtil.putResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
			LostFileImpl.class, lostFile.getPrimaryKey(), lostFile);

		clearUniqueFindersCache(lostFile);
		cacheUniqueFindersCache(lostFile);

		return lostFile;
	}

	protected LostFile toUnwrappedModel(LostFile lostFile) {
		if (lostFile instanceof LostFileImpl) {
			return lostFile;
		}

		LostFileImpl lostFileImpl = new LostFileImpl();

		lostFileImpl.setNew(lostFile.isNew());
		lostFileImpl.setPrimaryKey(lostFile.getPrimaryKey());

		lostFileImpl.setLostFileId(lostFile.getLostFileId());
		lostFileImpl.setGroupId(lostFile.getGroupId());
		lostFileImpl.setCompanyId(lostFile.getCompanyId());
		lostFileImpl.setUserId(lostFile.getUserId());
		lostFileImpl.setUserName(lostFile.getUserName());
		lostFileImpl.setCreateDate(lostFile.getCreateDate());
		lostFileImpl.setModifiedDate(lostFile.getModifiedDate());
		lostFileImpl.setFileEntryId(lostFile.getFileEntryId());
		lostFileImpl.setDlFileVersionId(lostFile.getDlFileVersionId());
		lostFileImpl.setDlFileTitle(lostFile.getDlFileTitle());
		lostFileImpl.setDeleted(lostFile.isDeleted());
		lostFileImpl.setComment(lostFile.getComment());

		return lostFileImpl;
	}

	/**
	 * Returns the lost file with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the lost file
	 * @return the lost file
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLostFileException, SystemException {
		LostFile lostFile = fetchByPrimaryKey(primaryKey);

		if (lostFile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLostFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lostFile;
	}

	/**
	 * Returns the lost file with the primary key or throws a {@link com.liferay.dl.cleaner.NoSuchLostFileException} if it could not be found.
	 *
	 * @param lostFileId the primary key of the lost file
	 * @return the lost file
	 * @throws com.liferay.dl.cleaner.NoSuchLostFileException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile findByPrimaryKey(long lostFileId)
		throws NoSuchLostFileException, SystemException {
		return findByPrimaryKey((Serializable)lostFileId);
	}

	/**
	 * Returns the lost file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lost file
	 * @return the lost file, or <code>null</code> if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		LostFile lostFile = (LostFile)EntityCacheUtil.getResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
				LostFileImpl.class, primaryKey);

		if (lostFile == _nullLostFile) {
			return null;
		}

		if (lostFile == null) {
			Session session = null;

			try {
				session = openSession();

				lostFile = (LostFile)session.get(LostFileImpl.class, primaryKey);

				if (lostFile != null) {
					cacheResult(lostFile);
				}
				else {
					EntityCacheUtil.putResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
						LostFileImpl.class, primaryKey, _nullLostFile);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(LostFileModelImpl.ENTITY_CACHE_ENABLED,
					LostFileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lostFile;
	}

	/**
	 * Returns the lost file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lostFileId the primary key of the lost file
	 * @return the lost file, or <code>null</code> if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile fetchByPrimaryKey(long lostFileId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)lostFileId);
	}

	/**
	 * Returns all the lost files.
	 *
	 * @return the lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LostFile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<LostFile> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lost files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lost files
	 * @param end the upper bound of the range of lost files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LostFile> findAll(int start, int end,
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

		List<LostFile> list = (List<LostFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LOSTFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOSTFILE;

				if (pagination) {
					sql = sql.concat(LostFileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LostFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<LostFile>(list);
				}
				else {
					list = (List<LostFile>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the lost files from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (LostFile lostFile : findAll()) {
			remove(lostFile);
		}
	}

	/**
	 * Returns the number of lost files.
	 *
	 * @return the number of lost files
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

				Query q = session.createQuery(_SQL_COUNT_LOSTFILE);

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
	 * Initializes the lost file persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.dl.cleaner.model.LostFile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LostFile>> listenersList = new ArrayList<ModelListener<LostFile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<LostFile>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LostFileImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_LOSTFILE = "SELECT lostFile FROM LostFile lostFile";
	private static final String _SQL_SELECT_LOSTFILE_WHERE = "SELECT lostFile FROM LostFile lostFile WHERE ";
	private static final String _SQL_COUNT_LOSTFILE = "SELECT COUNT(lostFile) FROM LostFile lostFile";
	private static final String _SQL_COUNT_LOSTFILE_WHERE = "SELECT COUNT(lostFile) FROM LostFile lostFile WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lostFile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LostFile exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LostFile exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LostFilePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"comment"
			});
	private static LostFile _nullLostFile = new LostFileImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<LostFile> toCacheModel() {
				return _nullLostFileCacheModel;
			}
		};

	private static CacheModel<LostFile> _nullLostFileCacheModel = new CacheModel<LostFile>() {
			@Override
			public LostFile toEntityModel() {
				return _nullLostFile;
			}
		};
}