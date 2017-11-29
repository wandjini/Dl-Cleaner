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

import com.liferay.dl.cleaner.model.LostFile;
import com.liferay.dl.cleaner.service.base.LostFileLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.InvalidFileVersionException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;

/**
 * The implementation of the lost file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.dl.cleaner.service.LostFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.base.LostFileLocalServiceBaseImpl
 * @see com.liferay.dl.cleaner.service.LostFileLocalServiceUtil
 */
public class LostFileLocalServiceImpl extends LostFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.dl.cleaner.service.LostFileLocalServiceUtil} to access the lost file local service.
	 */
	
	
	private final static Log _log = LogFactoryUtil.getLog(LostFileLocalServiceImpl.class);
	
	/**
	 * <p>This method is used to add a new lost file</p> 
	 * 
	 * @param fileEntryId
	 * @param userId
	 * @return
	 * @throws SystemException
	 * @throws PortalException 
	 */
	public LostFile addLostFile(long userId, long fileEntryId, long dlFileVersionId, String comment ) throws SystemException, PortalException{
		
		Date now = new Date(); 
		long lostFileId = counterLocalService.increment(LostFile.class.getName());
		LostFile lostFile = lostFilePersistence.create(lostFileId);
		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		
		if(dlFileEntry == null){
			lostFile = null;
		}
		else{
			lostFile.setFileEntryId(fileEntryId);
			lostFile.setCompanyId(dlFileEntry.getCompanyId());
			lostFile.setGroupId(dlFileEntry.getGroupId());
			if(userId > 0){
				User user = userLocalService.fetchUser(userId);
				lostFile.setUserId(userId);
				lostFile.setUserName(user.getFullName());	
			}
			lostFile.setCreateDate(now);
			lostFile.setModifiedDate(now);
			lostFile.setDeleted(false);
			lostFile.setDlFileVersionId(dlFileVersionId);
			lostFile.setDlFileTitle(dlFileEntry.getTitle());
			lostFile.setComment(comment);
			lostFile = lostFilePersistence.update(lostFile);
			resourceLocalService.addModelResources(lostFile, null);
		}
		
		return lostFile;
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
	public List<LostFile> getLostFilesByGroupAndState(long groupId, boolean deleted, int start, int end) throws SystemException{
		
		return lostFilePersistence.findByGroup_Deleted(groupId, deleted, start, end);
	}
	
	/**
	 * This method is used to get the total amount of files
	 * 
	 * @param groupId
	 * @param deleted
	 * @return
	 * @throws SystemException
	 */
	public int countLostFilesByGroupAndState(long groupId, boolean deleted) throws SystemException{
		return lostFilePersistence.countByGroup_Deleted(groupId, deleted);
	}
	
	/**
	 * Method used to clean the document and library
	 * 
	 * @param lostFileId
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void cleanLostFile(long userId, long lostFileId) throws SystemException, PortalException{
		
		LostFile lostFile = lostFilePersistence.findByPrimaryKey(lostFileId);
		
		DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(lostFile.getDlFileVersionId());
		DLFileEntry dlFileEntry = null;
		try {
			if (dlFileVersion.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				dlFileVersion.setStatus(WorkflowConstants.STATUS_APPROVED);

				DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion);
			}

			dlFileEntry = DLFileEntryLocalServiceUtil.deleteFileVersion(
				userId, dlFileVersion.getFileEntryId(),
				dlFileVersion.getVersion());

			
			_log.error(
				"DlFileVersion " + dlFileVersion.getVersion() + " deleted and dlFileEntry updated");
		}
		catch(InvalidFileVersionException e) {
			if (dlFileVersion.getVersion().equals("PWC")) {
				_log.error(dlFileVersion.getVersion() + " is blocked, we proceed to cancel checkout");

				DLFileEntryLocalServiceUtil.cancelCheckOut(dlFileVersion.getUserId(), dlFileVersion.getFileEntryId());

			
			}

			else{
				if("Cannot delete the only approved file version".equals(e.getMessage())) {
					dlFileEntry = DLFileEntryLocalServiceUtil.deleteFileEntry(dlFileVersion.getFileEntryId());
	
					_log.error("deleted FileEntry " + dlFileVersion.getFileEntryId());
				}
				else {
					_log.error("DlFileVersion " + dlFileVersion.getVersion() + " could not be deleted.",e);
				}
			}
		}
	
		if(dlFileEntry != null){
		
			lostFile.setDeleted(true);
			lostFilePersistence.update(lostFile);
		}
	}
	
	

}