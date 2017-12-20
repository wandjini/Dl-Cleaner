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

import com.liferay.dl.cleaner.NoSuchWcReferencedFileException;
import com.liferay.dl.cleaner.model.WcReferencedFile;
import com.liferay.dl.cleaner.model.impl.WcReferencedFileImpl;
import com.liferay.dl.cleaner.model.impl.WcReferencedFileModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the wc referenced file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author guywandji
 * @see WcReferencedFilePersistence
 * @see WcReferencedFileUtil
 * @generated
 */
public class WcReferencedFilePersistenceImpl extends BasePersistenceImpl<WcReferencedFile>
	implements WcReferencedFilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WcReferencedFileUtil} to access the wc referenced file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WcReferencedFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGroup_dlFileUuId",
			new String[] { Long.class.getName(), String.class.getName() },
			WcReferencedFileModelImpl.GROUPID_COLUMN_BITMASK |
			WcReferencedFileModelImpl.DLFILEUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUP_DLFILEUUID = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroup_dlFileUuId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the wc referenced file where groupId = &#63; and dlFileUuId = &#63; or throws a {@link com.liferay.dl.cleaner.NoSuchWcReferencedFileException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the matching wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByGroup_dlFileUuId(long groupId,
		String dlFileUuId)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByGroup_dlFileUuId(groupId,
				dlFileUuId);

		if (wcReferencedFile == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dlFileUuId=");
			msg.append(dlFileUuId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchWcReferencedFileException(msg.toString());
		}

		return wcReferencedFile;
	}

	/**
	 * Returns the wc referenced file where groupId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByGroup_dlFileUuId(long groupId,
		String dlFileUuId) throws SystemException {
		return fetchByGroup_dlFileUuId(groupId, dlFileUuId, true);
	}

	/**
	 * Returns the wc referenced file where groupId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dlFileUuId the dl file uu ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByGroup_dlFileUuId(long groupId,
		String dlFileUuId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, dlFileUuId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
					finderArgs, this);
		}

		if (result instanceof WcReferencedFile) {
			WcReferencedFile wcReferencedFile = (WcReferencedFile)result;

			if ((groupId != wcReferencedFile.getGroupId()) ||
					!Validator.equals(dlFileUuId,
						wcReferencedFile.getDlFileUuId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_GROUPID_2);

			boolean bindDlFileUuId = false;

			if (dlFileUuId == null) {
				query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_1);
			}
			else if (dlFileUuId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_3);
			}
			else {
				bindDlFileUuId = true;

				query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDlFileUuId) {
					qPos.add(dlFileUuId);
				}

				List<WcReferencedFile> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
						finderArgs, list);
				}
				else {
					WcReferencedFile wcReferencedFile = list.get(0);

					result = wcReferencedFile;

					cacheResult(wcReferencedFile);

					if ((wcReferencedFile.getGroupId() != groupId) ||
							(wcReferencedFile.getDlFileUuId() == null) ||
							!wcReferencedFile.getDlFileUuId().equals(dlFileUuId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
							finderArgs, wcReferencedFile);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
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
			return (WcReferencedFile)result;
		}
	}

	/**
	 * Removes the wc referenced file where groupId = &#63; and dlFileUuId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the wc referenced file that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile removeByGroup_dlFileUuId(long groupId,
		String dlFileUuId)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = findByGroup_dlFileUuId(groupId,
				dlFileUuId);

		return remove(wcReferencedFile);
	}

	/**
	 * Returns the number of wc referenced files where groupId = &#63; and dlFileUuId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the number of matching wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroup_dlFileUuId(long groupId, String dlFileUuId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUP_DLFILEUUID;

		Object[] finderArgs = new Object[] { groupId, dlFileUuId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_GROUPID_2);

			boolean bindDlFileUuId = false;

			if (dlFileUuId == null) {
				query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_1);
			}
			else if (dlFileUuId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_3);
			}
			else {
				bindDlFileUuId = true;

				query.append(_FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDlFileUuId) {
					qPos.add(dlFileUuId);
				}

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

	private static final String _FINDER_COLUMN_GROUP_DLFILEUUID_GROUPID_2 = "wcReferencedFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_1 = "wcReferencedFile.dlFileUuId IS NULL";
	private static final String _FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_2 = "wcReferencedFile.dlFileUuId = ?";
	private static final String _FINDER_COLUMN_GROUP_DLFILEUUID_DLFILEUUID_3 = "(wcReferencedFile.dlFileUuId IS NULL OR wcReferencedFile.dlFileUuId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUP = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroup",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroup",
			new String[] { Long.class.getName() },
			WcReferencedFileModelImpl.GROUPID_COLUMN_BITMASK |
			WcReferencedFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUP = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroup",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the wc referenced files where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WcReferencedFile> findByGroup(long groupId)
		throws SystemException {
		return findByGroup(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<WcReferencedFile> findByGroup(long groupId, int start, int end)
		throws SystemException {
		return findByGroup(groupId, start, end, null);
	}

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
	@Override
	public List<WcReferencedFile> findByGroup(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUP;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<WcReferencedFile> list = (List<WcReferencedFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WcReferencedFile wcReferencedFile : list) {
				if ((groupId != wcReferencedFile.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WcReferencedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<WcReferencedFile>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WcReferencedFile>(list);
				}
				else {
					list = (List<WcReferencedFile>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first wc referenced file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByGroup_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByGroup_First(groupId,
				orderByComparator);

		if (wcReferencedFile != null) {
			return wcReferencedFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWcReferencedFileException(msg.toString());
	}

	/**
	 * Returns the first wc referenced file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByGroup_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WcReferencedFile> list = findByGroup(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wc referenced file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByGroup_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByGroup_Last(groupId,
				orderByComparator);

		if (wcReferencedFile != null) {
			return wcReferencedFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWcReferencedFileException(msg.toString());
	}

	/**
	 * Returns the last wc referenced file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByGroup_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroup(groupId);

		if (count == 0) {
			return null;
		}

		List<WcReferencedFile> list = findByGroup(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public WcReferencedFile[] findByGroup_PrevAndNext(long wcRefencedFileId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = findByPrimaryKey(wcRefencedFileId);

		Session session = null;

		try {
			session = openSession();

			WcReferencedFile[] array = new WcReferencedFileImpl[3];

			array[0] = getByGroup_PrevAndNext(session, wcReferencedFile,
					groupId, orderByComparator, true);

			array[1] = wcReferencedFile;

			array[2] = getByGroup_PrevAndNext(session, wcReferencedFile,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WcReferencedFile getByGroup_PrevAndNext(Session session,
		WcReferencedFile wcReferencedFile, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WCREFERENCEDFILE_WHERE);

		query.append(_FINDER_COLUMN_GROUP_GROUPID_2);

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
			query.append(WcReferencedFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wcReferencedFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WcReferencedFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the wc referenced files where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroup(long groupId) throws SystemException {
		for (WcReferencedFile wcReferencedFile : findByGroup(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wcReferencedFile);
		}
	}

	/**
	 * Returns the number of wc referenced files where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroup(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUP;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUP_GROUPID_2 = "wcReferencedFile.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCompany_DlFileUuId",
			new String[] { Long.class.getName(), String.class.getName() },
			WcReferencedFileModelImpl.COMPANYID_COLUMN_BITMASK |
			WcReferencedFileModelImpl.DLFILEUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANY_DLFILEUUID = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompany_DlFileUuId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the wc referenced file where companyId = &#63; and dlFileUuId = &#63; or throws a {@link com.liferay.dl.cleaner.NoSuchWcReferencedFileException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the matching wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByCompany_DlFileUuId(long companyId,
		String dlFileUuId)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByCompany_DlFileUuId(companyId,
				dlFileUuId);

		if (wcReferencedFile == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", dlFileUuId=");
			msg.append(dlFileUuId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchWcReferencedFileException(msg.toString());
		}

		return wcReferencedFile;
	}

	/**
	 * Returns the wc referenced file where companyId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByCompany_DlFileUuId(long companyId,
		String dlFileUuId) throws SystemException {
		return fetchByCompany_DlFileUuId(companyId, dlFileUuId, true);
	}

	/**
	 * Returns the wc referenced file where companyId = &#63; and dlFileUuId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param dlFileUuId the dl file uu ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByCompany_DlFileUuId(long companyId,
		String dlFileUuId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, dlFileUuId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
					finderArgs, this);
		}

		if (result instanceof WcReferencedFile) {
			WcReferencedFile wcReferencedFile = (WcReferencedFile)result;

			if ((companyId != wcReferencedFile.getCompanyId()) ||
					!Validator.equals(dlFileUuId,
						wcReferencedFile.getDlFileUuId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_COMPANYID_2);

			boolean bindDlFileUuId = false;

			if (dlFileUuId == null) {
				query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_1);
			}
			else if (dlFileUuId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_3);
			}
			else {
				bindDlFileUuId = true;

				query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindDlFileUuId) {
					qPos.add(dlFileUuId);
				}

				List<WcReferencedFile> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
						finderArgs, list);
				}
				else {
					WcReferencedFile wcReferencedFile = list.get(0);

					result = wcReferencedFile;

					cacheResult(wcReferencedFile);

					if ((wcReferencedFile.getCompanyId() != companyId) ||
							(wcReferencedFile.getDlFileUuId() == null) ||
							!wcReferencedFile.getDlFileUuId().equals(dlFileUuId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
							finderArgs, wcReferencedFile);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
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
			return (WcReferencedFile)result;
		}
	}

	/**
	 * Removes the wc referenced file where companyId = &#63; and dlFileUuId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the wc referenced file that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile removeByCompany_DlFileUuId(long companyId,
		String dlFileUuId)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = findByCompany_DlFileUuId(companyId,
				dlFileUuId);

		return remove(wcReferencedFile);
	}

	/**
	 * Returns the number of wc referenced files where companyId = &#63; and dlFileUuId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param dlFileUuId the dl file uu ID
	 * @return the number of matching wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompany_DlFileUuId(long companyId, String dlFileUuId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANY_DLFILEUUID;

		Object[] finderArgs = new Object[] { companyId, dlFileUuId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_COMPANYID_2);

			boolean bindDlFileUuId = false;

			if (dlFileUuId == null) {
				query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_1);
			}
			else if (dlFileUuId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_3);
			}
			else {
				bindDlFileUuId = true;

				query.append(_FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindDlFileUuId) {
					qPos.add(dlFileUuId);
				}

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

	private static final String _FINDER_COLUMN_COMPANY_DLFILEUUID_COMPANYID_2 = "wcReferencedFile.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_1 = "wcReferencedFile.dlFileUuId IS NULL";
	private static final String _FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_2 = "wcReferencedFile.dlFileUuId = ?";
	private static final String _FINDER_COLUMN_COMPANY_DLFILEUUID_DLFILEUUID_3 = "(wcReferencedFile.dlFileUuId IS NULL OR wcReferencedFile.dlFileUuId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANY_ORPHAN =
		new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompany_Orphan",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_ORPHAN =
		new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED,
			WcReferencedFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompany_Orphan",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			WcReferencedFileModelImpl.COMPANYID_COLUMN_BITMASK |
			WcReferencedFileModelImpl.ORPHAN_COLUMN_BITMASK |
			WcReferencedFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANY_ORPHAN = new FinderPath(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompany_Orphan",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the wc referenced files where companyId = &#63; and orphan = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orphan the orphan
	 * @return the matching wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WcReferencedFile> findByCompany_Orphan(long companyId,
		boolean orphan) throws SystemException {
		return findByCompany_Orphan(companyId, orphan, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<WcReferencedFile> findByCompany_Orphan(long companyId,
		boolean orphan, int start, int end) throws SystemException {
		return findByCompany_Orphan(companyId, orphan, start, end, null);
	}

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
	@Override
	public List<WcReferencedFile> findByCompany_Orphan(long companyId,
		boolean orphan, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_ORPHAN;
			finderArgs = new Object[] { companyId, orphan };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANY_ORPHAN;
			finderArgs = new Object[] {
					companyId, orphan,
					
					start, end, orderByComparator
				};
		}

		List<WcReferencedFile> list = (List<WcReferencedFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WcReferencedFile wcReferencedFile : list) {
				if ((companyId != wcReferencedFile.getCompanyId()) ||
						(orphan != wcReferencedFile.getOrphan())) {
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

			query.append(_SQL_SELECT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_COMPANY_ORPHAN_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANY_ORPHAN_ORPHAN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WcReferencedFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(orphan);

				if (!pagination) {
					list = (List<WcReferencedFile>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WcReferencedFile>(list);
				}
				else {
					list = (List<WcReferencedFile>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orphan the orphan
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByCompany_Orphan_First(long companyId,
		boolean orphan, OrderByComparator orderByComparator)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByCompany_Orphan_First(companyId,
				orphan, orderByComparator);

		if (wcReferencedFile != null) {
			return wcReferencedFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", orphan=");
		msg.append(orphan);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWcReferencedFileException(msg.toString());
	}

	/**
	 * Returns the first wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orphan the orphan
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByCompany_Orphan_First(long companyId,
		boolean orphan, OrderByComparator orderByComparator)
		throws SystemException {
		List<WcReferencedFile> list = findByCompany_Orphan(companyId, orphan,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public WcReferencedFile findByCompany_Orphan_Last(long companyId,
		boolean orphan, OrderByComparator orderByComparator)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByCompany_Orphan_Last(companyId,
				orphan, orderByComparator);

		if (wcReferencedFile != null) {
			return wcReferencedFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", orphan=");
		msg.append(orphan);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWcReferencedFileException(msg.toString());
	}

	/**
	 * Returns the last wc referenced file in the ordered set where companyId = &#63; and orphan = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orphan the orphan
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wc referenced file, or <code>null</code> if a matching wc referenced file could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByCompany_Orphan_Last(long companyId,
		boolean orphan, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCompany_Orphan(companyId, orphan);

		if (count == 0) {
			return null;
		}

		List<WcReferencedFile> list = findByCompany_Orphan(companyId, orphan,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public WcReferencedFile[] findByCompany_Orphan_PrevAndNext(
		long wcRefencedFileId, long companyId, boolean orphan,
		OrderByComparator orderByComparator)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = findByPrimaryKey(wcRefencedFileId);

		Session session = null;

		try {
			session = openSession();

			WcReferencedFile[] array = new WcReferencedFileImpl[3];

			array[0] = getByCompany_Orphan_PrevAndNext(session,
					wcReferencedFile, companyId, orphan, orderByComparator, true);

			array[1] = wcReferencedFile;

			array[2] = getByCompany_Orphan_PrevAndNext(session,
					wcReferencedFile, companyId, orphan, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WcReferencedFile getByCompany_Orphan_PrevAndNext(
		Session session, WcReferencedFile wcReferencedFile, long companyId,
		boolean orphan, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WCREFERENCEDFILE_WHERE);

		query.append(_FINDER_COLUMN_COMPANY_ORPHAN_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANY_ORPHAN_ORPHAN_2);

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
			query.append(WcReferencedFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(orphan);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wcReferencedFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WcReferencedFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the wc referenced files where companyId = &#63; and orphan = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param orphan the orphan
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompany_Orphan(long companyId, boolean orphan)
		throws SystemException {
		for (WcReferencedFile wcReferencedFile : findByCompany_Orphan(
				companyId, orphan, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wcReferencedFile);
		}
	}

	/**
	 * Returns the number of wc referenced files where companyId = &#63; and orphan = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orphan the orphan
	 * @return the number of matching wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompany_Orphan(long companyId, boolean orphan)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANY_ORPHAN;

		Object[] finderArgs = new Object[] { companyId, orphan };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WCREFERENCEDFILE_WHERE);

			query.append(_FINDER_COLUMN_COMPANY_ORPHAN_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANY_ORPHAN_ORPHAN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(orphan);

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

	private static final String _FINDER_COLUMN_COMPANY_ORPHAN_COMPANYID_2 = "wcReferencedFile.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANY_ORPHAN_ORPHAN_2 = "wcReferencedFile.orphan = ?";

	public WcReferencedFilePersistenceImpl() {
		setModelClass(WcReferencedFile.class);
	}

	/**
	 * Caches the wc referenced file in the entity cache if it is enabled.
	 *
	 * @param wcReferencedFile the wc referenced file
	 */
	@Override
	public void cacheResult(WcReferencedFile wcReferencedFile) {
		EntityCacheUtil.putResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileImpl.class, wcReferencedFile.getPrimaryKey(),
			wcReferencedFile);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
			new Object[] {
				wcReferencedFile.getGroupId(), wcReferencedFile.getDlFileUuId()
			}, wcReferencedFile);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
			new Object[] {
				wcReferencedFile.getCompanyId(),
				wcReferencedFile.getDlFileUuId()
			}, wcReferencedFile);

		wcReferencedFile.resetOriginalValues();
	}

	/**
	 * Caches the wc referenced files in the entity cache if it is enabled.
	 *
	 * @param wcReferencedFiles the wc referenced files
	 */
	@Override
	public void cacheResult(List<WcReferencedFile> wcReferencedFiles) {
		for (WcReferencedFile wcReferencedFile : wcReferencedFiles) {
			if (EntityCacheUtil.getResult(
						WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
						WcReferencedFileImpl.class,
						wcReferencedFile.getPrimaryKey()) == null) {
				cacheResult(wcReferencedFile);
			}
			else {
				wcReferencedFile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all wc referenced files.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WcReferencedFileImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WcReferencedFileImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the wc referenced file.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WcReferencedFile wcReferencedFile) {
		EntityCacheUtil.removeResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileImpl.class, wcReferencedFile.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(wcReferencedFile);
	}

	@Override
	public void clearCache(List<WcReferencedFile> wcReferencedFiles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WcReferencedFile wcReferencedFile : wcReferencedFiles) {
			EntityCacheUtil.removeResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
				WcReferencedFileImpl.class, wcReferencedFile.getPrimaryKey());

			clearUniqueFindersCache(wcReferencedFile);
		}
	}

	protected void cacheUniqueFindersCache(WcReferencedFile wcReferencedFile) {
		if (wcReferencedFile.isNew()) {
			Object[] args = new Object[] {
					wcReferencedFile.getGroupId(),
					wcReferencedFile.getDlFileUuId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUP_DLFILEUUID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
				args, wcReferencedFile);

			args = new Object[] {
					wcReferencedFile.getCompanyId(),
					wcReferencedFile.getDlFileUuId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANY_DLFILEUUID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
				args, wcReferencedFile);
		}
		else {
			WcReferencedFileModelImpl wcReferencedFileModelImpl = (WcReferencedFileModelImpl)wcReferencedFile;

			if ((wcReferencedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wcReferencedFile.getGroupId(),
						wcReferencedFile.getDlFileUuId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUP_DLFILEUUID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
					args, wcReferencedFile);
			}

			if ((wcReferencedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wcReferencedFile.getCompanyId(),
						wcReferencedFile.getDlFileUuId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANY_DLFILEUUID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
					args, wcReferencedFile);
			}
		}
	}

	protected void clearUniqueFindersCache(WcReferencedFile wcReferencedFile) {
		WcReferencedFileModelImpl wcReferencedFileModelImpl = (WcReferencedFileModelImpl)wcReferencedFile;

		Object[] args = new Object[] {
				wcReferencedFile.getGroupId(), wcReferencedFile.getDlFileUuId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_DLFILEUUID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID, args);

		if ((wcReferencedFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID.getColumnBitmask()) != 0) {
			args = new Object[] {
					wcReferencedFileModelImpl.getOriginalGroupId(),
					wcReferencedFileModelImpl.getOriginalDlFileUuId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP_DLFILEUUID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_GROUP_DLFILEUUID,
				args);
		}

		args = new Object[] {
				wcReferencedFile.getCompanyId(),
				wcReferencedFile.getDlFileUuId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANY_DLFILEUUID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
			args);

		if ((wcReferencedFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID.getColumnBitmask()) != 0) {
			args = new Object[] {
					wcReferencedFileModelImpl.getOriginalCompanyId(),
					wcReferencedFileModelImpl.getOriginalDlFileUuId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANY_DLFILEUUID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANY_DLFILEUUID,
				args);
		}
	}

	/**
	 * Creates a new wc referenced file with the primary key. Does not add the wc referenced file to the database.
	 *
	 * @param wcRefencedFileId the primary key for the new wc referenced file
	 * @return the new wc referenced file
	 */
	@Override
	public WcReferencedFile create(long wcRefencedFileId) {
		WcReferencedFile wcReferencedFile = new WcReferencedFileImpl();

		wcReferencedFile.setNew(true);
		wcReferencedFile.setPrimaryKey(wcRefencedFileId);

		return wcReferencedFile;
	}

	/**
	 * Removes the wc referenced file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wcRefencedFileId the primary key of the wc referenced file
	 * @return the wc referenced file that was removed
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile remove(long wcRefencedFileId)
		throws NoSuchWcReferencedFileException, SystemException {
		return remove((Serializable)wcRefencedFileId);
	}

	/**
	 * Removes the wc referenced file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the wc referenced file
	 * @return the wc referenced file that was removed
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile remove(Serializable primaryKey)
		throws NoSuchWcReferencedFileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WcReferencedFile wcReferencedFile = (WcReferencedFile)session.get(WcReferencedFileImpl.class,
					primaryKey);

			if (wcReferencedFile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWcReferencedFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(wcReferencedFile);
		}
		catch (NoSuchWcReferencedFileException nsee) {
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
	protected WcReferencedFile removeImpl(WcReferencedFile wcReferencedFile)
		throws SystemException {
		wcReferencedFile = toUnwrappedModel(wcReferencedFile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(wcReferencedFile)) {
				wcReferencedFile = (WcReferencedFile)session.get(WcReferencedFileImpl.class,
						wcReferencedFile.getPrimaryKeyObj());
			}

			if (wcReferencedFile != null) {
				session.delete(wcReferencedFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (wcReferencedFile != null) {
			clearCache(wcReferencedFile);
		}

		return wcReferencedFile;
	}

	@Override
	public WcReferencedFile updateImpl(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile)
		throws SystemException {
		wcReferencedFile = toUnwrappedModel(wcReferencedFile);

		boolean isNew = wcReferencedFile.isNew();

		WcReferencedFileModelImpl wcReferencedFileModelImpl = (WcReferencedFileModelImpl)wcReferencedFile;

		Session session = null;

		try {
			session = openSession();

			if (wcReferencedFile.isNew()) {
				session.save(wcReferencedFile);

				wcReferencedFile.setNew(false);
			}
			else {
				session.merge(wcReferencedFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WcReferencedFileModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((wcReferencedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wcReferencedFileModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP,
					args);

				args = new Object[] { wcReferencedFileModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUP,
					args);
			}

			if ((wcReferencedFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_ORPHAN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wcReferencedFileModelImpl.getOriginalCompanyId(),
						wcReferencedFileModelImpl.getOriginalOrphan()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANY_ORPHAN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_ORPHAN,
					args);

				args = new Object[] {
						wcReferencedFileModelImpl.getCompanyId(),
						wcReferencedFileModelImpl.getOrphan()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANY_ORPHAN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANY_ORPHAN,
					args);
			}
		}

		EntityCacheUtil.putResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
			WcReferencedFileImpl.class, wcReferencedFile.getPrimaryKey(),
			wcReferencedFile);

		clearUniqueFindersCache(wcReferencedFile);
		cacheUniqueFindersCache(wcReferencedFile);

		return wcReferencedFile;
	}

	protected WcReferencedFile toUnwrappedModel(
		WcReferencedFile wcReferencedFile) {
		if (wcReferencedFile instanceof WcReferencedFileImpl) {
			return wcReferencedFile;
		}

		WcReferencedFileImpl wcReferencedFileImpl = new WcReferencedFileImpl();

		wcReferencedFileImpl.setNew(wcReferencedFile.isNew());
		wcReferencedFileImpl.setPrimaryKey(wcReferencedFile.getPrimaryKey());

		wcReferencedFileImpl.setWcRefencedFileId(wcReferencedFile.getWcRefencedFileId());
		wcReferencedFileImpl.setGroupId(wcReferencedFile.getGroupId());
		wcReferencedFileImpl.setCompanyId(wcReferencedFile.getCompanyId());
		wcReferencedFileImpl.setUserId(wcReferencedFile.getUserId());
		wcReferencedFileImpl.setUserName(wcReferencedFile.getUserName());
		wcReferencedFileImpl.setCreateDate(wcReferencedFile.getCreateDate());
		wcReferencedFileImpl.setModifiedDate(wcReferencedFile.getModifiedDate());
		wcReferencedFileImpl.setWcUrlTitle(wcReferencedFile.getWcUrlTitle());
		wcReferencedFileImpl.setDlFileUuId(wcReferencedFile.getDlFileUuId());
		wcReferencedFileImpl.setOrphan(wcReferencedFile.isOrphan());
		wcReferencedFileImpl.setType(wcReferencedFile.getType());

		return wcReferencedFileImpl;
	}

	/**
	 * Returns the wc referenced file with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the wc referenced file
	 * @return the wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWcReferencedFileException, SystemException {
		WcReferencedFile wcReferencedFile = fetchByPrimaryKey(primaryKey);

		if (wcReferencedFile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWcReferencedFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return wcReferencedFile;
	}

	/**
	 * Returns the wc referenced file with the primary key or throws a {@link com.liferay.dl.cleaner.NoSuchWcReferencedFileException} if it could not be found.
	 *
	 * @param wcRefencedFileId the primary key of the wc referenced file
	 * @return the wc referenced file
	 * @throws com.liferay.dl.cleaner.NoSuchWcReferencedFileException if a wc referenced file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile findByPrimaryKey(long wcRefencedFileId)
		throws NoSuchWcReferencedFileException, SystemException {
		return findByPrimaryKey((Serializable)wcRefencedFileId);
	}

	/**
	 * Returns the wc referenced file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the wc referenced file
	 * @return the wc referenced file, or <code>null</code> if a wc referenced file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WcReferencedFile wcReferencedFile = (WcReferencedFile)EntityCacheUtil.getResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
				WcReferencedFileImpl.class, primaryKey);

		if (wcReferencedFile == _nullWcReferencedFile) {
			return null;
		}

		if (wcReferencedFile == null) {
			Session session = null;

			try {
				session = openSession();

				wcReferencedFile = (WcReferencedFile)session.get(WcReferencedFileImpl.class,
						primaryKey);

				if (wcReferencedFile != null) {
					cacheResult(wcReferencedFile);
				}
				else {
					EntityCacheUtil.putResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
						WcReferencedFileImpl.class, primaryKey,
						_nullWcReferencedFile);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WcReferencedFileModelImpl.ENTITY_CACHE_ENABLED,
					WcReferencedFileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return wcReferencedFile;
	}

	/**
	 * Returns the wc referenced file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wcRefencedFileId the primary key of the wc referenced file
	 * @return the wc referenced file, or <code>null</code> if a wc referenced file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WcReferencedFile fetchByPrimaryKey(long wcRefencedFileId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)wcRefencedFileId);
	}

	/**
	 * Returns all the wc referenced files.
	 *
	 * @return the wc referenced files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WcReferencedFile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<WcReferencedFile> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	@Override
	public List<WcReferencedFile> findAll(int start, int end,
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

		List<WcReferencedFile> list = (List<WcReferencedFile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WCREFERENCEDFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WCREFERENCEDFILE;

				if (pagination) {
					sql = sql.concat(WcReferencedFileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WcReferencedFile>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WcReferencedFile>(list);
				}
				else {
					list = (List<WcReferencedFile>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the wc referenced files from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WcReferencedFile wcReferencedFile : findAll()) {
			remove(wcReferencedFile);
		}
	}

	/**
	 * Returns the number of wc referenced files.
	 *
	 * @return the number of wc referenced files
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

				Query q = session.createQuery(_SQL_COUNT_WCREFERENCEDFILE);

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
	 * Initializes the wc referenced file persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.dl.cleaner.model.WcReferencedFile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WcReferencedFile>> listenersList = new ArrayList<ModelListener<WcReferencedFile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WcReferencedFile>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(WcReferencedFileImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_WCREFERENCEDFILE = "SELECT wcReferencedFile FROM WcReferencedFile wcReferencedFile";
	private static final String _SQL_SELECT_WCREFERENCEDFILE_WHERE = "SELECT wcReferencedFile FROM WcReferencedFile wcReferencedFile WHERE ";
	private static final String _SQL_COUNT_WCREFERENCEDFILE = "SELECT COUNT(wcReferencedFile) FROM WcReferencedFile wcReferencedFile";
	private static final String _SQL_COUNT_WCREFERENCEDFILE_WHERE = "SELECT COUNT(wcReferencedFile) FROM WcReferencedFile wcReferencedFile WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wcReferencedFile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WcReferencedFile exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WcReferencedFile exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WcReferencedFilePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
	private static WcReferencedFile _nullWcReferencedFile = new WcReferencedFileImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WcReferencedFile> toCacheModel() {
				return _nullWcReferencedFileCacheModel;
			}
		};

	private static CacheModel<WcReferencedFile> _nullWcReferencedFileCacheModel = new CacheModel<WcReferencedFile>() {
			@Override
			public WcReferencedFile toEntityModel() {
				return _nullWcReferencedFile;
			}
		};
}