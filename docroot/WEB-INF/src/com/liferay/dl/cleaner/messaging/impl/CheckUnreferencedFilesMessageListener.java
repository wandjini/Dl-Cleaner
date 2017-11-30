package com.liferay.dl.cleaner.messaging.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.LockLocalServiceUtil;
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
	 * @param companyId
	 * @param userId
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void getDlFilesWithNoReferenceInWebContent(long companyId, long userId) throws SystemException, PortalException{
		int start = 0;
		int end = 1000;
		DynamicQuery dynamicQuery = null;; 
		long count = DLFileEntryLocalServiceUtil.dynamicQueryCount(getDlFileDynanicQuery(companyId));
		int orphanedFileVersionSize = 0;

		_log.debug("Total number of DLFileVersions: " + count);
		String comment = StringPool.BLANK;
		while (start < count) {
			_log.debug("Processing (start, end): (" + start + ", " + end + ")" );
			dynamicQuery = getDlFileDynanicQuery(companyId);
			dynamicQuery.setLimit(start, end);
			@SuppressWarnings("unchecked")
			List<DLFileEntry> dlFileEntries = 
					DLFileEntryLocalServiceUtil.dynamicQuery(dynamicQuery);
			
			for (DLFileEntry dlFileEntry : dlFileEntries) {
										
				try {
					
					WcReferencedFileLocalServiceUtil.getWcReferencedFilesByCompanyAndFileUUID(dlFileEntry.getCompanyId(), dlFileEntry.getUuid());
					
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
					String type = StringPool.BLANK;
					String filePath = StringPool.BLANK;
					if(!dynamicElements.isEmpty()){
						for(Element dynamicElement:dynamicElements){
							elementAttributes = dynamicElement.attributes();
							if(!elementAttributes.isEmpty()){
								for(Attribute elementAttribute:elementAttributes){
									if(elementAttribute.getName().equals("type") ){
										type = elementAttribute.getValue();
										if(type.equals(_DOCUMENT_LIBRARY) || type.equals(_IMAGE) || type.equals(_TEXT_AREA)){
											contentElements = dynamicElement.elements("dynamic-content");
											if(!contentElements.isEmpty()){
												for(Element contentElement: contentElements){
													filePath = getFilePathFromElement(contentElement, type);
													if(Validator.isNotNull(filePath)){
														valuesToProcess.add(JSONFactoryUtil.createJSONObject().put("groupId", journalArticle.getGroupId())
															.put("filePath", filePath)
															.put("articleId", journalArticle.getArticleId())
															.put("companyId", journalArticle.getCompanyId())
															.put("type", type));
													}
												}
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
				
				String[] files = null;
				String dlFileUuId  = StringPool.BLANK;
				String articleId = StringPool.BLANK;
				long companyId = 0;
				long articleGroupId = 0;
				boolean orfan = false;
				String type = StringPool.BLANK;
				for(JSONObject fileToProcess: valuesToProcess){
					files = fileToProcess.getString("filePath").split(";");
					
					for(int i = 0; i < files.length; i++){
						type = fileToProcess.getString("type").equals(_IMAGE) ? _IMAGE : _DOCUMENT_LIBRARY;
						String[] parameters = files[i].split(StringPool.SLASH);
						if(type.equals(_IMAGE)){
							String[] splitSrc =  parameters[parameters.length -1 ].split("=");
							dlFileUuId = splitSrc[1].split("&")[0];
						}
						else{
							dlFileUuId = parameters[parameters.length -1 ];
						}
						articleGroupId = fileToProcess.getLong("groupId");
						articleId = fileToProcess.getString("articleId");
						companyId = fileToProcess.getLong("companyId");
						
						try{
							if(type.equals(_IMAGE)){
								Image image = ImageLocalServiceUtil.getImage(Long.valueOf(dlFileUuId));
								orfan = image == null;
								
							}
							else{
								DLFileEntry dlFileEntry =  DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndCompanyId(dlFileUuId, companyId);
								orfan = dlFileEntry == null;
							}
							
							WcReferencedFileLocalServiceUtil.getWcReferencedFilesByCompanyAndFileUUID(companyId, dlFileUuId);
							
						}catch (NoSuchWcReferencedFileException e) {
							WcReferencedFileLocalServiceUtil.addWcReferencedFile(userId, articleGroupId, dlFileUuId, articleId, type, orfan );
						}
						
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
	 * @param companyId
	 * @return
	 */
	private DynamicQuery getDlFileDynanicQuery(long companyId){
		DynamicQuery dynamicQuery = DLFileEntryLocalServiceUtil.dynamicQuery();
		if(companyId > 0){
			dynamicQuery.add(RestrictionsFactoryUtil.eq("companyid", companyId));
		}
		return dynamicQuery;
	}
	
	/**
	 * This method provide dynamicQuery for JournalArticle
	 * 
	 * @param companyId
	 * @return
	 */
	private DynamicQuery getJournalArticleDynanicQuery(long companyId){
		DynamicQuery dynamicQuery = JournalArticleLocalServiceUtil.dynamicQuery();
		if(companyId > 0){
			dynamicQuery.add(RestrictionsFactoryUtil.eq("companyid", companyId));
		}
		return dynamicQuery;
	}	
	
	
	/**
	 * 
	 * This Method retrieve the filePath based on element type
	 * 
	 * @param element
	 * @param type
	 * @return
	 */
	private String getFilePathFromElement(Element element, String type){
		
		String result = "";
		if(type.equals(_DOCUMENT_LIBRARY) || type.equals(_IMAGE)){
			result = element.getText();
		}
		else if(type.equals(_TEXT_AREA)){
			String html = element.getText();
			
			if(Validator.isNotNull(html)){
				org.jsoup.nodes.Document document = Jsoup.parse(html);
				Elements anchors = document.select("a[href]"); 
				anchors.addAll(document.select("img"));
				if(!anchors.isEmpty()){
					StringBuilder sb = new StringBuilder();
					String data = StringPool.BLANK;
					int size = anchors.size();
					for(int i = 0; i < anchors.size(); i++){
						data = anchors.get(i).attr("href");
						if(Validator.isNull(data)){
							data = anchors.get(i).attr("src");
						}
						if(data.startsWith("/documents")){
							sb.append(anchors.get(i).data() +( (i < size - 1) ? ";" : "") );
						}
					}
					result = sb.toString();
				}
			}
		}
		
		return result;
	}
	
	private final String _DOCUMENT_LIBRARY = "document_library";
	private final String _TEXT_AREA = "text_area";
	private final String _IMAGE = "image";
}
