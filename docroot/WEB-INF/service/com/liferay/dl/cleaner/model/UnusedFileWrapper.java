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

package com.liferay.dl.cleaner.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UnusedFile}.
 * </p>
 *
 * @author guywandji
 * @see UnusedFile
 * @generated
 */
public class UnusedFileWrapper implements UnusedFile, ModelWrapper<UnusedFile> {
	public UnusedFileWrapper(UnusedFile unusedFile) {
		_unusedFile = unusedFile;
	}

	@Override
	public Class<?> getModelClass() {
		return UnusedFile.class;
	}

	@Override
	public String getModelClassName() {
		return UnusedFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("unusedFileId", getUnusedFileId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("dlFileVersionId", getDlFileVersionId());
		attributes.put("dlFileTitle", getDlFileTitle());
		attributes.put("deleted", getDeleted());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long unusedFileId = (Long)attributes.get("unusedFileId");

		if (unusedFileId != null) {
			setUnusedFileId(unusedFileId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long dlFileVersionId = (Long)attributes.get("dlFileVersionId");

		if (dlFileVersionId != null) {
			setDlFileVersionId(dlFileVersionId);
		}

		String dlFileTitle = (String)attributes.get("dlFileTitle");

		if (dlFileTitle != null) {
			setDlFileTitle(dlFileTitle);
		}

		Boolean deleted = (Boolean)attributes.get("deleted");

		if (deleted != null) {
			setDeleted(deleted);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	/**
	* Returns the primary key of this unused file.
	*
	* @return the primary key of this unused file
	*/
	@Override
	public long getPrimaryKey() {
		return _unusedFile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this unused file.
	*
	* @param primaryKey the primary key of this unused file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_unusedFile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the unused file ID of this unused file.
	*
	* @return the unused file ID of this unused file
	*/
	@Override
	public long getUnusedFileId() {
		return _unusedFile.getUnusedFileId();
	}

	/**
	* Sets the unused file ID of this unused file.
	*
	* @param unusedFileId the unused file ID of this unused file
	*/
	@Override
	public void setUnusedFileId(long unusedFileId) {
		_unusedFile.setUnusedFileId(unusedFileId);
	}

	/**
	* Returns the group ID of this unused file.
	*
	* @return the group ID of this unused file
	*/
	@Override
	public long getGroupId() {
		return _unusedFile.getGroupId();
	}

	/**
	* Sets the group ID of this unused file.
	*
	* @param groupId the group ID of this unused file
	*/
	@Override
	public void setGroupId(long groupId) {
		_unusedFile.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this unused file.
	*
	* @return the company ID of this unused file
	*/
	@Override
	public long getCompanyId() {
		return _unusedFile.getCompanyId();
	}

	/**
	* Sets the company ID of this unused file.
	*
	* @param companyId the company ID of this unused file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_unusedFile.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this unused file.
	*
	* @return the user ID of this unused file
	*/
	@Override
	public long getUserId() {
		return _unusedFile.getUserId();
	}

	/**
	* Sets the user ID of this unused file.
	*
	* @param userId the user ID of this unused file
	*/
	@Override
	public void setUserId(long userId) {
		_unusedFile.setUserId(userId);
	}

	/**
	* Returns the user uuid of this unused file.
	*
	* @return the user uuid of this unused file
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _unusedFile.getUserUuid();
	}

	/**
	* Sets the user uuid of this unused file.
	*
	* @param userUuid the user uuid of this unused file
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_unusedFile.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this unused file.
	*
	* @return the user name of this unused file
	*/
	@Override
	public java.lang.String getUserName() {
		return _unusedFile.getUserName();
	}

	/**
	* Sets the user name of this unused file.
	*
	* @param userName the user name of this unused file
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_unusedFile.setUserName(userName);
	}

	/**
	* Returns the create date of this unused file.
	*
	* @return the create date of this unused file
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _unusedFile.getCreateDate();
	}

	/**
	* Sets the create date of this unused file.
	*
	* @param createDate the create date of this unused file
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_unusedFile.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this unused file.
	*
	* @return the modified date of this unused file
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _unusedFile.getModifiedDate();
	}

	/**
	* Sets the modified date of this unused file.
	*
	* @param modifiedDate the modified date of this unused file
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_unusedFile.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the file entry ID of this unused file.
	*
	* @return the file entry ID of this unused file
	*/
	@Override
	public long getFileEntryId() {
		return _unusedFile.getFileEntryId();
	}

	/**
	* Sets the file entry ID of this unused file.
	*
	* @param fileEntryId the file entry ID of this unused file
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_unusedFile.setFileEntryId(fileEntryId);
	}

	/**
	* Returns the dl file version ID of this unused file.
	*
	* @return the dl file version ID of this unused file
	*/
	@Override
	public long getDlFileVersionId() {
		return _unusedFile.getDlFileVersionId();
	}

	/**
	* Sets the dl file version ID of this unused file.
	*
	* @param dlFileVersionId the dl file version ID of this unused file
	*/
	@Override
	public void setDlFileVersionId(long dlFileVersionId) {
		_unusedFile.setDlFileVersionId(dlFileVersionId);
	}

	/**
	* Returns the dl file title of this unused file.
	*
	* @return the dl file title of this unused file
	*/
	@Override
	public java.lang.String getDlFileTitle() {
		return _unusedFile.getDlFileTitle();
	}

	/**
	* Sets the dl file title of this unused file.
	*
	* @param dlFileTitle the dl file title of this unused file
	*/
	@Override
	public void setDlFileTitle(java.lang.String dlFileTitle) {
		_unusedFile.setDlFileTitle(dlFileTitle);
	}

	/**
	* Returns the deleted of this unused file.
	*
	* @return the deleted of this unused file
	*/
	@Override
	public boolean getDeleted() {
		return _unusedFile.getDeleted();
	}

	/**
	* Returns <code>true</code> if this unused file is deleted.
	*
	* @return <code>true</code> if this unused file is deleted; <code>false</code> otherwise
	*/
	@Override
	public boolean isDeleted() {
		return _unusedFile.isDeleted();
	}

	/**
	* Sets whether this unused file is deleted.
	*
	* @param deleted the deleted of this unused file
	*/
	@Override
	public void setDeleted(boolean deleted) {
		_unusedFile.setDeleted(deleted);
	}

	/**
	* Returns the comment of this unused file.
	*
	* @return the comment of this unused file
	*/
	@Override
	public java.lang.String getComment() {
		return _unusedFile.getComment();
	}

	/**
	* Sets the comment of this unused file.
	*
	* @param comment the comment of this unused file
	*/
	@Override
	public void setComment(java.lang.String comment) {
		_unusedFile.setComment(comment);
	}

	@Override
	public boolean isNew() {
		return _unusedFile.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_unusedFile.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _unusedFile.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_unusedFile.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _unusedFile.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _unusedFile.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_unusedFile.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _unusedFile.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_unusedFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_unusedFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_unusedFile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UnusedFileWrapper((UnusedFile)_unusedFile.clone());
	}

	@Override
	public int compareTo(com.liferay.dl.cleaner.model.UnusedFile unusedFile) {
		return _unusedFile.compareTo(unusedFile);
	}

	@Override
	public int hashCode() {
		return _unusedFile.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.dl.cleaner.model.UnusedFile> toCacheModel() {
		return _unusedFile.toCacheModel();
	}

	@Override
	public com.liferay.dl.cleaner.model.UnusedFile toEscapedModel() {
		return new UnusedFileWrapper(_unusedFile.toEscapedModel());
	}

	@Override
	public com.liferay.dl.cleaner.model.UnusedFile toUnescapedModel() {
		return new UnusedFileWrapper(_unusedFile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _unusedFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _unusedFile.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_unusedFile.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UnusedFileWrapper)) {
			return false;
		}

		UnusedFileWrapper unusedFileWrapper = (UnusedFileWrapper)obj;

		if (Validator.equals(_unusedFile, unusedFileWrapper._unusedFile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public UnusedFile getWrappedUnusedFile() {
		return _unusedFile;
	}

	@Override
	public UnusedFile getWrappedModel() {
		return _unusedFile;
	}

	@Override
	public void resetOriginalValues() {
		_unusedFile.resetOriginalValues();
	}

	private UnusedFile _unusedFile;
}