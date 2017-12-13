package com.liferay.dl.cleaner.messaging.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;

import com.liferay.dl.cleaner.NoSuchUnusedFileException;
import com.liferay.dl.cleaner.NoSuchWcReferencedFileException;
import com.liferay.dl.cleaner.model.WcReferencedFile;
import com.liferay.dl.cleaner.portlet.util.ActionKeys;
import com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil;
import com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * This class is use to retrieve potential unreferenced and lost files
 * 
 * @author guywandji
 *
 */
public class CheckUnreferencedFilesMessageListener implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {

		try {
			LockLocalServiceUtil.lock(CheckUnreferencedFilesMessageListener.class.getName(), ActionKeys.KEY_JOB,
					ActionKeys.JOB_OWNER);

		} catch (Exception e1) {
			_log.error(e1);
			return;
		}
		Object obj = message.getPayload();
		long companyId = 0;
		long userId = 0;

		try {
			UnusedFileLocalServiceUtil.cleanAll();
			if (obj != null) {
				JSONObject payload = JSONFactoryUtil.createJSONObject(obj.toString());
				userId = payload.getLong("userId");
				companyId = payload.getLong("companyId");
			}

			getJournalArticleReferencedFiles(companyId, userId);
			getDlFilesWithNoReferenceInWebContent(companyId, userId);
		} catch (Exception e) {
			_log.error(e);
			e.printStackTrace();
		} finally {
			try {
				Lock lock = LockLocalServiceUtil.getLock(CheckUnreferencedFilesMessageListener.class.getName(),
						ActionKeys.KEY_JOB);
				if (lock != null)
					LockLocalServiceUtil.deleteLock(lock);
			} catch (Exception e) {
				_log.error(e);
			}

		}
	}

	private final static Log _log = LogFactoryUtil.getLog(CheckUnreferencedFilesMessageListener.class);

	/**
	 * This method retrieves Orphaned files
	 * 
	 * @param companyId
	 * @param userId
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void getDlFilesWithNoReferenceInWebContent(long companyId, long userId)
			throws SystemException, PortalException {
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;
		;
		long count = DLFileEntryLocalServiceUtil.dynamicQueryCount(getDlFileDynanicQuery(companyId));
		int unusedFilesFoundSize = 0;

		_log.debug("Total number of DLFileVersions: " + count);
		String comment = StringPool.BLANK;
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")");
			dynamicQuery = getDlFileDynanicQuery(companyId);
			dynamicQuery.setLimit(start, end);
			@SuppressWarnings("unchecked")
			List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.dynamicQuery(dynamicQuery);

			for (DLFileEntry dlFileEntry : dlFileEntries) {

				try {

					WcReferencedFileLocalServiceUtil.getWcReferencedFilesByCompanyAndFileUUID(
							dlFileEntry.getCompanyId(), dlFileEntry.getUuid());

				} catch (NoSuchWcReferencedFileException ex) {

					try {
						UnusedFileLocalServiceUtil.getUnusedFilesByGroupFileIdVersionId(dlFileEntry.getGroupId(),
								dlFileEntry.getFileEntryId(), dlFileEntry.getFileVersion().getFileVersionId());

					} catch (NoSuchUnusedFileException e2) {
						
						_log.error("The file " + dlFileEntry.getFileEntryId() + " doesn't exit yet in the table");
						UnusedFileLocalServiceUtil.addUnusedFile(userId, dlFileEntry.getFileEntryId(),
								dlFileEntry.getFileVersion().getFileVersionId(), StringPool.BLANK, comment);
						unusedFilesFoundSize++;
					}

				}

			}

			start += 1000;
			end += 1000;
		}
		if (userId > 0) {
			try {

				User user = UserLocalServiceUtil.getUser(userId);
				InternetAddress to = new InternetAddress(user.getEmailAddress());
				InternetAddress from = new InternetAddress("noreply@liferay.com");
				MailMessage mailMessage = new MailMessage();
				mailMessage.setTo(to);
				mailMessage.setFrom(from);
				mailMessage.setBody("Job WebContent Orphan files Finder is ended");
				MailServiceUtil.sendEmail(mailMessage);
			} catch (Exception e) {
				_log.error("Error sending notification", e);
			}
		}
		_log.error(unusedFilesFoundSize + " unused files found");

	}

	/**
	 * This method retrieves All referenced files in the web content
	 * 
	 * @param groupId
	 * @param userId
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void getJournalArticleReferencedFiles(long groupId, long userId) throws SystemException, PortalException {
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;
		;
		long count = JournalArticleLocalServiceUtil.dynamicQueryCount(getJournalArticleDynanicQuery(groupId));
		_log.debug("Total number of JournalArticles: " + count);
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")");
			dynamicQuery = getJournalArticleDynanicQuery(groupId);
			dynamicQuery.setLimit(start, end);

			@SuppressWarnings("unchecked")
			List<JournalArticle> journalArticles = JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
			String xml = StringPool.BLANK;
			for (JournalArticle journalArticle : journalArticles) {

				xml = journalArticle.getContent();
				try {
					String filePath = getReferencedDocumentsUuIdFromContent(xml);

					if (Validator.isNotNull(filePath))
						addWcReferencedFile(userId, journalArticle, filePath, _DOCUMENT_LIBRARY);

					filePath = getReferencedImagesIdFromContent(xml);
					if (Validator.isNotNull(filePath))
						addWcReferencedFile(userId, journalArticle, filePath, _IMAGE);

				} catch (Exception e) {

					_log.error(e);

				}

			}

			start += 1000;
			end += 1000;
		}

	}

	/**
	 * This method provide dynamicQuery for DlFileVersions
	 * 
	 * @param companyId
	 * @return
	 */
	private DynamicQuery getDlFileDynanicQuery(long companyId) {
		DynamicQuery dynamicQuery = DLFileEntryLocalServiceUtil.dynamicQuery();
		if (companyId > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		}
		return dynamicQuery;
	}

	/**
	 * This method provide dynamicQuery for JournalArticle
	 * 
	 * @param companyId
	 * @return
	 */
	private DynamicQuery getJournalArticleDynanicQuery(long companyId) {
		DynamicQuery dynamicQuery = JournalArticleLocalServiceUtil.dynamicQuery();
		if (companyId > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		}
		return dynamicQuery;
	}

		
	/**
	 * This method return a set of documents referenced in a journal article
	 * 
	 * @param content
	 * @return
	 */
	private String getReferencedDocumentsUuIdFromContent(String content) {

		String docUuIds = StringPool.BLANK;
		Pattern pattern = Pattern.compile(dlRegex);
		Matcher matcher = pattern.matcher(content);
		String[] splitLink = null;
		String tmpUuId = StringPool.BLANK;

		while (matcher.find()) {
			splitLink = matcher.group().split(StringPool.SLASH);
			tmpUuId = splitLink[splitLink.length - 1];
			if (!docUuIds.contains(StringPool.SLASH + tmpUuId + StringPool.SLASH)) {
				docUuIds = (Validator.isNull(docUuIds) ? StringPool.SLASH + docUuIds : docUuIds) + tmpUuId
						+ StringPool.SLASH;
			}
		}

		return docUuIds;
	}

	/**
	 * This method return a set of images referenced in a journal article
	 * 
	 * @param content
	 * @return
	 */
	private String getReferencedImagesIdFromContent(String content) {
		String result = StringPool.BLANK;
		Pattern pattern = Pattern.compile(imgRegExp);
		Matcher matcher = pattern.matcher(content);
		String[] splitLink = null;
		String imgId = StringPool.BLANK;
		while (matcher.find()) {
			splitLink = matcher.group().split(StringPool.EQUAL);
			imgId = splitLink[1];
			if (!result.contains(StringPool.SLASH + imgId + StringPool.SLASH)) {
				result = (Validator.isNull(result) ? StringPool.SLASH + result : result) + imgId + StringPool.SLASH;
			}
		}
		return result;
	}

	/**
	 * This method adds referenced web content file
	 * 
	 * @param userId
	 * @param journalArticle
	 * @param filePath
	 * @throws SystemException
	 * @throws PortalException
	 * @throws NumberFormatException
	 */
	private void addWcReferencedFile(long userId, JournalArticle journalArticle, String filePath, String type)
			throws NumberFormatException, PortalException, SystemException {
		if (Validator.isNotNull(filePath)) {

			String[] files = null;
			String dlFileUuId = StringPool.BLANK;
			String articleId = journalArticle.getArticleId();
			long companyId = journalArticle.getCompanyId();
			long articleGroupId = journalArticle.getGroupId();;
			boolean orphan = false;
			files = filePath.split(StringPool.SLASH);
			
			for (int i = 0; i < files.length; i++) {
				if (Validator.isNull(files[i]))
					continue;

				dlFileUuId = files[i];
				try {
					if (type.equals(_IMAGE)) {
						Image image = ImageLocalServiceUtil.getImage(Long.valueOf(dlFileUuId));
						orphan = image == null;

					} else {
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
								.fetchDLFileEntryByUuidAndCompanyId(dlFileUuId, companyId);
						orphan = dlFileEntry == null;
					}

					WcReferencedFile wcReferencedFile = WcReferencedFileLocalServiceUtil
							.getWcReferencedFilesByCompanyAndFileUUID(companyId, dlFileUuId);
					if (!wcReferencedFile.isOrphan() && orphan) {
						wcReferencedFile.setOrphan(orphan);
						WcReferencedFileLocalServiceUtil.updateWcReferencedFile(wcReferencedFile);
					}

				} catch (NoSuchWcReferencedFileException e) {
					WcReferencedFileLocalServiceUtil.addWcReferencedFile(userId, articleGroupId, dlFileUuId, articleId,
							type, orphan);
				}

			}
		}
	}
	
	private final String _DOCUMENT_LIBRARY = "document_library";
	private final String _IMAGE = "image";

	private final String dlRegex = "\\/documents\\/[0-9]*\\/[0-9]*\\/[^\\/]*\\/[a-zA-Z0-9_-]*";
	private final String imgRegExp = "\\/image\\/journal\\/article\\?img_id=[0-9]*";

}
