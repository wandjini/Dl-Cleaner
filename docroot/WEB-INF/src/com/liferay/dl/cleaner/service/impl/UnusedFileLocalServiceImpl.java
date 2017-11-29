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

import com.liferay.dl.cleaner.model.UnusedFile;
import com.liferay.dl.cleaner.service.base.UnusedFileLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;

/**
 * The implementation of the unused file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.dl.cleaner.service.UnusedFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.base.UnusedFileLocalServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil
 */
public class UnusedFileLocalServiceImpl extends UnusedFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil} to access the unused file local service.
	 */
	
	/**
	 * <p>This method is used to add a new unused file</p> 
	 * 
	 * @param fileEntryId
	 * @param userId
	 * @return
	 * @throws SystemException
	 * @throws PortalException 
	 */
	public UnusedFile addUnusedFile(long userId, long fileEntryId, long fileVersionId, String comment) throws SystemException, PortalException{
		
		Date now = new Date(); 
		long unusedFileId = counterLocalService.increment(UnusedFile.class.getName());
		UnusedFile unusedFile = unusedFilePersistence.create(unusedFileId);
		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		
		if(dlFileEntry == null){
			unusedFile = null;
		}
		else{
			unusedFile.setFileEntryId(fileEntryId);
			unusedFile.setCompanyId(dlFileEntry.getCompanyId());
			unusedFile.setGroupId(dlFileEntry.getGroupId());
			if(userId > 0){
				User user = userLocalService.fetchUser(userId);
				unusedFile.setUserId(userId);
				unusedFile.setUserName(user.getFullName());	
			}
			unusedFile.setDlFileVersionId(fileVersionId);
			unusedFile.setCreateDate(now);
			unusedFile.setModifiedDate(now);
			unusedFile.setDeleted(false);
			unusedFile.setComment(comment);
			
			unusedFile = unusedFilePersistence.update(unusedFile);
			resourceLocalService.addModelResources(unusedFile, null);
		}
		
		return unusedFile;
	}
	
	/**
	 * 
	 * <p>This method is used to retrieve File by groupId and state</p>
	 * 
	 * @param groupId
	 * @param deleted
	 * @param start
	 * @param end
	 * @return
	 * @throws SystemException 
	 */
	public List<UnusedFile> getUnusedFilesByGroupAndState(long groupId, boolean deleted, int start, int end) throws SystemException{
		
		return unusedFilePersistence.findByGroup_Deleted(groupId, deleted, start, end);
	}
	
	/**
	 * This method is used to get the total amount of files
	 * 
	 * @param groupId
	 * @param deleted
	 * @return
	 * @throws SystemException
	 */
	public int countUnusedFilesByGroupAndState(long groupId, boolean deleted) throws SystemException{
		return unusedFilePersistence.countByGroup_Deleted(groupId, deleted);
	}
	
	/**
	 * Method used to clean the document and library
	 * 
	 * @param unusedFileId
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void cleanUnusedFile(long unusedFileId, String fileVersion, long userId) throws SystemException, PortalException{
		
		UnusedFile unusedFile = unusedFilePersistence.findByPrimaryKey(unusedFileId);
		DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(unusedFile.getDlFileVersionId());
		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.deleteFileVersion(userId, unusedFile.getFileEntryId(), dlFileVersion.getVersion());
		if(dlFileEntry != null){
			unusedFile.setDeleted(true);
			unusedFilePersistence.update(unusedFile);
		}
	}
	
}