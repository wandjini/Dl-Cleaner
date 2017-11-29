package com.liferay.dl.cleaner.messaging.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.dl.cleaner.service.LostFileLocalServiceUtil;
import com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * This class is use to retrieve potentcial unrefenced and lost files
 * 
 * @author guywandji
 *
 */
public class CheckUnreferencedFilesMessageListener implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		
		Object obj = message.getPayload();
		long groupId = 0;
		long userId = 0;
		
		try {
			if(obj != null){
				JSONObject payload = JSONFactoryUtil.createJSONObject(obj.toString());
				userId = payload.getLong("userId");
				groupId = payload.getLong("groupId");
			}
			getDlFileVersionsWithNoBinaryContent(groupId, userId);
			getJournalArticlesFilesWithNoBinaryContent(groupId,userId);
		} catch (JSONException e) {
			_log.error(e);
			e.printStackTrace();
		} catch (SystemException e) {
			_log.error(e);
			e.printStackTrace();
		} catch (PortalException e) {
			_log.error(e);
			e.printStackTrace();
		} 
		
		
	}
	
	

	private final static Log _log = LogFactoryUtil.getLog(CheckUnreferencedFilesMessageListener.class);
	
	/**
	 * This method retrieves Orphaned file versions and files without binary content
	 * 
	 * @param groupId
	 * @param userId
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void getDlFileVersionsWithNoBinaryContent(long groupId, long userId) throws SystemException, PortalException{
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;; 
		long count = DLFileVersionLocalServiceUtil.dynamicQueryCount(getDlFileVesionsDynanicQuery(groupId));
		int orphanedFileVersionSize = 0;
		int filesWithoutBinarySize = 0;
		_log.debug("Total number of DLFileVersions: " + count);
		String comment = StringPool.BLANK;
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")" );
			dynamicQuery = getDlFileVesionsDynanicQuery(groupId);
			dynamicQuery.setLimit(start, end);
			@SuppressWarnings("unchecked")
			List<DLFileVersion> dlFileVersions = 
				DLFileVersionLocalServiceUtil.dynamicQuery(dynamicQuery);

			for (DLFileVersion dlFileVersion : dlFileVersions) {
				DLFileEntry dlFileEntry = null;

				try {
					dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(
						dlFileVersion.getFileEntryId());
				}
				catch (NoSuchFileEntryException e) {
					comment = "Unable to find file entry " +
							dlFileVersion.getFileEntryId() +
							" associated with file version " +
							dlFileVersion.getFileVersionId(); 
					_log.error(comment
				);
						
					
					UnusedFileLocalServiceUtil.addUnusedFile(userId,dlFileVersion.getFileEntryId(), dlFileVersion.getFileVersionId(), comment );
					orphanedFileVersionSize++;
					continue;
				}

				try {
					long repositoryId = DLFolderConstants.getDataRepositoryId(
						dlFileEntry.getRepositoryId(), dlFileEntry.getFolderId());

					if(!DLStoreUtil.hasFile(dlFileEntry.getCompanyId(),
							repositoryId, dlFileEntry.getName(),
							dlFileVersion.getVersion())) {
						comment = "DlFileVersion with ID " +
								dlFileVersion.getFileVersionId() + " and version " +
								dlFileVersion.getVersion() + " and DLFileEntry " +
								dlFileVersion.getFileEntryId() + " does not have a " +
								"binary content";
						_log.error(comment);

						LostFileLocalServiceUtil.addLostFile(userId, dlFileVersion.getFileEntryId(), dlFileVersion.getFileVersionId(), comment);
						
						filesWithoutBinarySize++;
						
						
					}
				}
				catch(Exception e) {
					_log.error("Error processing " + dlFileEntry + " - " + e.getMessage(),e);
				}
			}

			start+=1000;
			end+=1000;
		}

		_log.error("DlFileVersion objects with invalid file: " + filesWithoutBinarySize);
		_log.error(orphanedFileVersionSize + " orphaned DLFileVersions found");
		
	}

	/**
	 * This method retrieves Orphaned file versions and files without binary content
	 * 
	 * @param groupId
	 * @param userId
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void getJournalArticlesFilesWithNoBinaryContent(long groupId, long userId) throws SystemException, PortalException{
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;; 
		long count = JournalArticleLocalServiceUtil.dynamicQueryCount(getJournalArticleDynanicQuery(groupId));
		int orphanedFileVersionSize = 0;
		int filesWithoutBinarySize = 0;
		_log.debug("Total number of JournalArticles: " + count);
		String comment = StringPool.BLANK;
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")" );
			dynamicQuery = getJournalArticleDynanicQuery(groupId);
			dynamicQuery.setLimit(start, end);
			List<String> valuesToProcess = new ArrayList<>();
			
			@SuppressWarnings("unchecked")
			List<JournalArticle> journalArticles = 
					JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);

			for (JournalArticle journalArticle : journalArticles) {
				
				String xml = journalArticle.getContent();
				Document document = null;
				Element rootElement = null;
				try {
					
					document = SAXReaderUtil.read(xml);
					rootElement = document.getRootElement();
					List<Element> dynamicElements = rootElement.elements("dynamic-element");
					List<Attribute> elementAttributes = null;
					List<Element> contentElements = null;
					if(!dynamicElements.isEmpty()){
						for(Element dynamicElement:dynamicElements){
							elementAttributes = dynamicElement.attributes();
							if(!elementAttributes.isEmpty()){
								for(Attribute elementAttribute:elementAttributes){
									if(elementAttribute.getName().equals("type") && elementAttribute.getValue().equals("document_library")){
										contentElements = dynamicElement.elements("dynamic-content");
										if(!contentElements.isEmpty()){
											for(Element contentElement: contentElements){
												valuesToProcess.add(contentElement.getText());
											}
										}
										break;
									}
								}
							}
						}
					}
				}
				catch (Exception e) {
					 
					_log.error(e);
						
					
					//UnusedFileLocalServiceUtil.addUnusedFile(userId,dlFileVersion.getFileEntryId(), dlFileVersion.getFileVersionId(), comment );
					orphanedFileVersionSize++;
					continue;
				}


			}

			start+=1000;
			end+=1000;
		}

		_log.error("DlFileVersion objects with invalid file: " + filesWithoutBinarySize);
		_log.error(orphanedFileVersionSize + " orphaned DLFileVersions found");
		
	}
	/**
	 * This method provide dynamicQuery for DlFileVersions
	 * 
	 * @param groupId
	 * @return
	 */
	private DynamicQuery getDlFileVesionsDynanicQuery(long groupId){
		DynamicQuery dynamicQuery = DLFileVersionLocalServiceUtil.dynamicQuery();
		if(groupId > 0){
			dynamicQuery.add(RestrictionsFactoryUtil.eq("groupid", groupId));
		}
		return dynamicQuery;
	}
	
	/**
	 * This method provide dynamicQuery for JournalArticle
	 * 
	 * @param groupId
	 * @return
	 */
	private DynamicQuery getJournalArticleDynanicQuery(long groupId){
		DynamicQuery dynamicQuery = JournalArticleLocalServiceUtil.dynamicQuery();
		if(groupId > 0){
			dynamicQuery.add(RestrictionsFactoryUtil.eq("groupid", groupId));
		}
		return dynamicQuery;
	}
	
	
}
