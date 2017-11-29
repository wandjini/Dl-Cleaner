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
 * This class is a wrapper for {@link LostFile}.
 * </p>
 *
 * @author guywandji
 * @see LostFile
 * @generated
 */
public class LostFileWrapper implements LostFile, ModelWrapper<LostFile> {
	public LostFileWrapper(LostFile lostFile) {
		_lostFile = lostFile;
	}

	@Override
	public Class<?> getModelClass() {
		return LostFile.class;
	}

	@Override
	public String getModelClassName() {
		return LostFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lostFileId", getLostFileId());
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
		Long lostFileId = (Long)attributes.get("lostFileId");

		if (lostFileId != null) {
			setLostFileId(lostFileId);
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
	* Returns the primary key of this lost file.
	*
	* @return the primary key of this lost file
	*/
	@Override
	public long getPrimaryKey() {
		return _lostFile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this lost file.
	*
	* @param primaryKey the primary key of this lost file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lostFile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the lost file ID of this lost file.
	*
	* @return the lost file ID of this lost file
	*/
	@Override
	public long getLostFileId() {
		return _lostFile.getLostFileId();
	}

	/**
	* Sets the lost file ID of this lost file.
	*
	* @param lostFileId the lost file ID of this lost file
	*/
	@Override
	public void setLostFileId(long lostFileId) {
		_lostFile.setLostFileId(lostFileId);
	}

	/**
	* Returns the group ID of this lost file.
	*
	* @return the group ID of this lost file
	*/
	@Override
	public long getGroupId() {
		return _lostFile.getGroupId();
	}

	/**
	* Sets the group ID of this lost file.
	*
	* @param groupId the group ID of this lost file
	*/
	@Override
	public void setGroupId(long groupId) {
		_lostFile.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this lost file.
	*
	* @return the company ID of this lost file
	*/
	@Override
	public long getCompanyId() {
		return _lostFile.getCompanyId();
	}

	/**
	* Sets the company ID of this lost file.
	*
	* @param companyId the company ID of this lost file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_lostFile.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this lost file.
	*
	* @return the user ID of this lost file
	*/
	@Override
	public long getUserId() {
		return _lostFile.getUserId();
	}

	/**
	* Sets the user ID of this lost file.
	*
	* @param userId the user ID of this lost file
	*/
	@Override
	public void setUserId(long userId) {
		_lostFile.setUserId(userId);
	}

	/**
	* Returns the user uuid of this lost file.
	*
	* @return the user uuid of this lost file
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lostFile.getUserUuid();
	}

	/**
	* Sets the user uuid of this lost file.
	*
	* @param userUuid the user uuid of this lost file
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_lostFile.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this lost file.
	*
	* @return the user name of this lost file
	*/
	@Override
	public java.lang.String getUserName() {
		return _lostFile.getUserName();
	}

	/**
	* Sets the user name of this lost file.
	*
	* @param userName the user name of this lost file
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_lostFile.setUserName(userName);
	}

	/**
	* Returns the create date of this lost file.
	*
	* @return the create date of this lost file
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _lostFile.getCreateDate();
	}

	/**
	* Sets the create date of this lost file.
	*
	* @param createDate the create date of this lost file
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_lostFile.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this lost file.
	*
	* @return the modified date of this lost file
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _lostFile.getModifiedDate();
	}

	/**
	* Sets the modified date of this lost file.
	*
	* @param modifiedDate the modified date of this lost file
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_lostFile.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the file entry ID of this lost file.
	*
	* @return the file entry ID of this lost file
	*/
	@Override
	public long getFileEntryId() {
		return _lostFile.getFileEntryId();
	}

	/**
	* Sets the file entry ID of this lost file.
	*
	* @param fileEntryId the file entry ID of this lost file
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_lostFile.setFileEntryId(fileEntryId);
	}

	/**
	* Returns the dl file version ID of this lost file.
	*
	* @return the dl file version ID of this lost file
	*/
	@Override
	public long getDlFileVersionId() {
		return _lostFile.getDlFileVersionId();
	}

	/**
	* Sets the dl file version ID of this lost file.
	*
	* @param dlFileVersionId the dl file version ID of this lost file
	*/
	@Override
	public void setDlFileVersionId(long dlFileVersionId) {
		_lostFile.setDlFileVersionId(dlFileVersionId);
	}

	/**
	* Returns the dl file title of this lost file.
	*
	* @return the dl file title of this lost file
	*/
	@Override
	public java.lang.String getDlFileTitle() {
		return _lostFile.getDlFileTitle();
	}

	/**
	* Sets the dl file title of this lost file.
	*
	* @param dlFileTitle the dl file title of this lost file
	*/
	@Override
	public void setDlFileTitle(java.lang.String dlFileTitle) {
		_lostFile.setDlFileTitle(dlFileTitle);
	}

	/**
	* Returns the deleted of this lost file.
	*
	* @return the deleted of this lost file
	*/
	@Override
	public java.lang.Boolean getDeleted() {
		return _lostFile.getDeleted();
	}

	/**
	* Sets the deleted of this lost file.
	*
	* @param deleted the deleted of this lost file
	*/
	@Override
	public void setDeleted(java.lang.Boolean deleted) {
		_lostFile.setDeleted(deleted);
	}

	/**
	* Returns the comment of this lost file.
	*
	* @return the comment of this lost file
	*/
	@Override
	public java.lang.String getComment() {
		return _lostFile.getComment();
	}

	/**
	* Sets the comment of this lost file.
	*
	* @param comment the comment of this lost file
	*/
	@Override
	public void setComment(java.lang.String comment) {
		_lostFile.setComment(comment);
	}

	@Override
	public boolean isNew() {
		return _lostFile.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_lostFile.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _lostFile.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lostFile.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _lostFile.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _lostFile.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_lostFile.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _lostFile.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_lostFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_lostFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_lostFile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LostFileWrapper((LostFile)_lostFile.clone());
	}

	@Override
	public int compareTo(com.liferay.dl.cleaner.model.LostFile lostFile) {
		return _lostFile.compareTo(lostFile);
	}

	@Override
	public int hashCode() {
		return _lostFile.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.dl.cleaner.model.LostFile> toCacheModel() {
		return _lostFile.toCacheModel();
	}

	@Override
	public com.liferay.dl.cleaner.model.LostFile toEscapedModel() {
		return new LostFileWrapper(_lostFile.toEscapedModel());
	}

	@Override
	public com.liferay.dl.cleaner.model.LostFile toUnescapedModel() {
		return new LostFileWrapper(_lostFile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _lostFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lostFile.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_lostFile.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LostFileWrapper)) {
			return false;
		}

		LostFileWrapper lostFileWrapper = (LostFileWrapper)obj;

		if (Validator.equals(_lostFile, lostFileWrapper._lostFile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public LostFile getWrappedLostFile() {
		return _lostFile;
	}

	@Override
	public LostFile getWrappedModel() {
		return _lostFile;
	}

	@Override
	public void resetOriginalValues() {
		_lostFile.resetOriginalValues();
	}

	private LostFile _lostFile;
}