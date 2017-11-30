package com.liferay.dl.cleaner.messaging.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.dl.cleaner.NoSuchUnusedFileException;
import com.liferay.dl.cleaner.NoSuchWcReferencedFileException;
import com.liferay.dl.cleaner.portlet.util.ActionKeys;
import com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil;
import com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
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
		
		
		try {
			LockLocalServiceUtil.lock(CheckUnreferencedFilesMessageListener.class.getName(),
					ActionKeys.KEY_JOB, ActionKeys.JOB_OWNER);
	
		} catch (Exception e1) {
			_log.error(e1);
			return;
		}
		Object obj = message.getPayload();
		long groupId = 0;
		long userId = 0;
		
		try {
			if(obj != null){
				JSONObject payload = JSONFactoryUtil.createJSONObject(obj.toString());
				userId = payload.getLong("userId");
				groupId = payload.getLong("groupId");
			}
			
			getJournalArticleReferencedFiles(groupId,userId);
			getDlFilesWithNoReferenceInWebContent(groupId, userId);
		} catch (Exception e) {
			_log.error(e);
			e.printStackTrace();
		} 
		finally{
			try {
				Lock lock = LockLocalServiceUtil.getLock(CheckUnreferencedFilesMessageListener.class.getName(),
						ActionKeys.KEY_JOB);
				if(lock != null)
					LockLocalServiceUtil.deleteLock(lock);
			} catch (Exception e) {
				_log.error(e);
			}
		
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
	private void getDlFilesWithNoReferenceInWebContent(long groupId, long userId) throws SystemException, PortalException{
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;; 
		long count = DLFileEntryLocalServiceUtil.dynamicQueryCount(getDlFileDynanicQuery(groupId));
		int orphanedFileVersionSize = 0;

		_log.debug("Total number of DLFileVersions: " + count);
		String comment = StringPool.BLANK;
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")" );
			dynamicQuery = getDlFileDynanicQuery(groupId);
			dynamicQuery.setLimit(start, end);
			@SuppressWarnings("unchecked")
			List<DLFileEntry> dlFileEntries = 
					DLFileEntryLocalServiceUtil.dynamicQuery(dynamicQuery);

			for (DLFileEntry dlFileEntry : dlFileEntries) {
										
				try {
					
					WcReferencedFileLocalServiceUtil.getWcReferencedFilesByGroupAndFileUUID(dlFileEntry.getGroupId(), dlFileEntry.getUuid());
					
				}catch (NoSuchWcReferencedFileException ex) {
					_log.error("The file "+ dlFileEntry.getFileEntryId() +" doesn't exit yet in the table");
					try {
						UnusedFileLocalServiceUtil.getUnusedFilesByGroupFileIdVersionId(dlFileEntry.getGroupId(), 
								dlFileEntry.getFileEntryId(), dlFileEntry.getFileVersion().getFileVersionId());
						
						orphanedFileVersionSize++;
					} catch (NoSuchUnusedFileException e2) {
						_log.error(e2);
						UnusedFileLocalServiceUtil.addUnusedFile(userId,dlFileEntry.getFileEntryId(), dlFileEntry.getFileVersion().getFileVersionId(), comment );
					}
					
				}

			}

			start+=1000;
			end+=1000;
		}
		_log.error(orphanedFileVersionSize + " orphaned DLFileVersions found");
		
	}

	/**
	 * This method retrieves All referenced files in the web content
	 * 
	 * @param groupId
	 * @param userId
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void getJournalArticleReferencedFiles(long groupId, long userId) throws SystemException, PortalException{
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;; 
		long count = JournalArticleLocalServiceUtil.dynamicQueryCount(getJournalArticleDynanicQuery(groupId));
		_log.debug("Total number of JournalArticles: " + count);
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")" );
			dynamicQuery = getJournalArticleDynanicQuery(groupId);
			dynamicQuery.setLimit(start, end);
			List<JSONObject> valuesToProcess = new ArrayList<>();
			
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
												valuesToProcess.add(JSONFactoryUtil.createJSONObject().put("groupId", journalArticle.getGroupId())
														.put("filePath", contentElement.getText())
														.put("articleId", journalArticle.getArticleId()));
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
						
				}


			}
			
			if(!valuesToProcess.isEmpty()){
				String filePath = StringPool.BLANK;
				String dlFileUuId  = StringPool.BLANK;
				String articleId = StringPool.BLANK;
				long articleGroupId = 0;
				for(JSONObject fileToProcess: valuesToProcess){
					filePath = fileToProcess.getString("filePath");
					String[] parameters = filePath.split(StringPool.SLASH);
					dlFileUuId = parameters[parameters.length -1 ];
					articleGroupId = fileToProcess.getLong("groupId");
					articleId = fileToProcess.getString("articleId");
					try{
						WcReferencedFileLocalServiceUtil.getWcReferencedFilesByGroupAndFileUUID(articleGroupId, dlFileUuId);
					}catch (NoSuchWcReferencedFileException e) {
						WcReferencedFileLocalServiceUtil.addWcReferencedFile(userId, articleGroupId, dlFileUuId, articleId, StringPool.BLANK);
					}
					
				}
			}
			start+=1000;
			end+=1000;
		}
		
	}
	
	/**
	 * This method provide dynamicQuery for DlFileVersions
	 * 
	 * @param groupId
	 * @return
	 */
	private DynamicQuery getDlFileDynanicQuery(long groupId){
		DynamicQuery dynamicQuery = DLFileEntryLocalServiceUtil.dynamicQuery();
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
