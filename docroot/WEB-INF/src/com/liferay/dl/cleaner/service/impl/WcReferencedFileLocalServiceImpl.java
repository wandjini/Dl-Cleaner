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

package com.liferay.dl.cleaner.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.dl.cleaner.NoSuchWcReferencedFileException;
import com.liferay.dl.cleaner.model.WcReferencedFile;
import com.liferay.dl.cleaner.service.base.WcReferencedFileLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * The implementation of the wc referenced file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.dl.cleaner.service.WcReferencedFileLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.base.WcReferencedFileLocalServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil
 */
public class WcReferencedFileLocalServiceImpl extends WcReferencedFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil} to
	 * access the wc referenced file local service.
	 */

	/**
	 * <p>
	 * This method is used to add a new Web content referenced file
	 * </p>
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
	public WcReferencedFile addWcReferencedFile(long userId, long groupId, String dlFileUuId, String articleId,
			String type, boolean orphan) throws SystemException, PortalException {

		Date now = new Date();
		long wcReferencedFileId = counterLocalService.increment(WcReferencedFile.class.getName());
		WcReferencedFile wcRefencedFile = wcReferencedFilePersistence.create(wcReferencedFileId);
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);
		if (journalArticle == null) {
			wcRefencedFile = null;
		} else {
			wcRefencedFile.setDlFileUuId(dlFileUuId);
			;
			wcRefencedFile.setCompanyId(journalArticle.getCompanyId());
			wcRefencedFile.setGroupId(groupId);
			wcRefencedFile.setArticleId(articleId);
			wcRefencedFile.setType(type);
			if (userId > 0) {
				User user = userLocalService.fetchUser(userId);
				wcRefencedFile.setUserId(userId);
				wcRefencedFile.setUserName(user.getFullName());
			}
			wcRefencedFile.setCreateDate(now);
			wcRefencedFile.setModifiedDate(now);
			wcRefencedFile.setOrphan(orphan);

			wcRefencedFile = wcReferencedFilePersistence.update(wcRefencedFile);
			resourceLocalService.addResources(journalArticle.getCompanyId(), groupId,
					userId > 0 ? userId : userLocalService.getDefaultUser(journalArticle.getCompanyId()).getUserId(),
					WcReferencedFile.class.getName(), wcReferencedFileId, false, true, true);
		}

		return wcRefencedFile;
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
	public List<WcReferencedFile> getWcReferencedFilesByGroupId(long groupId, int start, int end)
			throws SystemException {

		return wcReferencedFilePersistence.findByGroup(groupId, start, end);
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
	public WcReferencedFile getWcReferencedFilesByGroupAndFileUUID(long groupId, String dlFileUuId)
			throws SystemException, NoSuchWcReferencedFileException {

		return wcReferencedFilePersistence.findByGroup_dlFileUuId(groupId, dlFileUuId);
	}

	/**
	 * Count items by groupId
	 * 
	 * @param groupId
	 * @return
	 * @throws SystemException
	 */
	public int countWcReferencedFilesByGroupId(long groupId) throws SystemException {

		return wcReferencedFilePersistence.countByGroup(groupId);
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
	public WcReferencedFile getWcReferencedFilesByCompanyAndFileUUID(long companyId, String dlFileUuId)
			throws SystemException, NoSuchWcReferencedFileException {

		return wcReferencedFilePersistence.findByCompany_DlFileUuId(companyId, dlFileUuId);
	}

	/**
	 * Deletes the file reference
	 * 
	 * @param wcRefencedFileId
	 * @throws SystemException
	 * @throws NoSuchWcReferencedFileException
	 */
	public void removeWcReferencedFile(long wcRefencedFileId) throws NoSuchWcReferencedFileException, SystemException {

		wcReferencedFilePersistence.remove(wcRefencedFileId);
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
	public List<WcReferencedFile> getWcReferencedFilesByCompanyIdAndOrphan(long companyId, boolean orphan, int start,
			int end) throws SystemException {

		return wcReferencedFilePersistence.findByCompany_Orphan(companyId, orphan);
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
	public int countWcReferencedFilesByCompanyIdAndOrphan(long companyId, boolean orphan) throws SystemException {

		return wcReferencedFilePersistence.countByCompany_Orphan(companyId, orphan);
	}
}