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
 * This class is a wrapper for {@link WcReferencedFile}.
 * </p>
 *
 * @author guywandji
 * @see WcReferencedFile
 * @generated
 */
public class WcReferencedFileWrapper implements WcReferencedFile,
	ModelWrapper<WcReferencedFile> {
	public WcReferencedFileWrapper(WcReferencedFile wcReferencedFile) {
		_wcReferencedFile = wcReferencedFile;
	}

	@Override
	public Class<?> getModelClass() {
		return WcReferencedFile.class;
	}

	@Override
	public String getModelClassName() {
		return WcReferencedFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("wcRefencedFileId", getWcRefencedFileId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("wcUrlTitle", getWcUrlTitle());
		attributes.put("dlFileUuId", getDlFileUuId());
		attributes.put("orphan", getOrphan());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long wcRefencedFileId = (Long)attributes.get("wcRefencedFileId");

		if (wcRefencedFileId != null) {
			setWcRefencedFileId(wcRefencedFileId);
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

		String wcUrlTitle = (String)attributes.get("wcUrlTitle");

		if (wcUrlTitle != null) {
			setWcUrlTitle(wcUrlTitle);
		}

		String dlFileUuId = (String)attributes.get("dlFileUuId");

		if (dlFileUuId != null) {
			setDlFileUuId(dlFileUuId);
		}

		Boolean orphan = (Boolean)attributes.get("orphan");

		if (orphan != null) {
			setOrphan(orphan);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this wc referenced file.
	*
	* @return the primary key of this wc referenced file
	*/
	@Override
	public long getPrimaryKey() {
		return _wcReferencedFile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this wc referenced file.
	*
	* @param primaryKey the primary key of this wc referenced file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wcReferencedFile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the wc refenced file ID of this wc referenced file.
	*
	* @return the wc refenced file ID of this wc referenced file
	*/
	@Override
	public long getWcRefencedFileId() {
		return _wcReferencedFile.getWcRefencedFileId();
	}

	/**
	* Sets the wc refenced file ID of this wc referenced file.
	*
	* @param wcRefencedFileId the wc refenced file ID of this wc referenced file
	*/
	@Override
	public void setWcRefencedFileId(long wcRefencedFileId) {
		_wcReferencedFile.setWcRefencedFileId(wcRefencedFileId);
	}

	/**
	* Returns the group ID of this wc referenced file.
	*
	* @return the group ID of this wc referenced file
	*/
	@Override
	public long getGroupId() {
		return _wcReferencedFile.getGroupId();
	}

	/**
	* Sets the group ID of this wc referenced file.
	*
	* @param groupId the group ID of this wc referenced file
	*/
	@Override
	public void setGroupId(long groupId) {
		_wcReferencedFile.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this wc referenced file.
	*
	* @return the company ID of this wc referenced file
	*/
	@Override
	public long getCompanyId() {
		return _wcReferencedFile.getCompanyId();
	}

	/**
	* Sets the company ID of this wc referenced file.
	*
	* @param companyId the company ID of this wc referenced file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wcReferencedFile.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this wc referenced file.
	*
	* @return the user ID of this wc referenced file
	*/
	@Override
	public long getUserId() {
		return _wcReferencedFile.getUserId();
	}

	/**
	* Sets the user ID of this wc referenced file.
	*
	* @param userId the user ID of this wc referenced file
	*/
	@Override
	public void setUserId(long userId) {
		_wcReferencedFile.setUserId(userId);
	}

	/**
	* Returns the user uuid of this wc referenced file.
	*
	* @return the user uuid of this wc referenced file
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wcReferencedFile.getUserUuid();
	}

	/**
	* Sets the user uuid of this wc referenced file.
	*
	* @param userUuid the user uuid of this wc referenced file
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_wcReferencedFile.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this wc referenced file.
	*
	* @return the user name of this wc referenced file
	*/
	@Override
	public java.lang.String getUserName() {
		return _wcReferencedFile.getUserName();
	}

	/**
	* Sets the user name of this wc referenced file.
	*
	* @param userName the user name of this wc referenced file
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_wcReferencedFile.setUserName(userName);
	}

	/**
	* Returns the create date of this wc referenced file.
	*
	* @return the create date of this wc referenced file
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _wcReferencedFile.getCreateDate();
	}

	/**
	* Sets the create date of this wc referenced file.
	*
	* @param createDate the create date of this wc referenced file
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_wcReferencedFile.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this wc referenced file.
	*
	* @return the modified date of this wc referenced file
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _wcReferencedFile.getModifiedDate();
	}

	/**
	* Sets the modified date of this wc referenced file.
	*
	* @param modifiedDate the modified date of this wc referenced file
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wcReferencedFile.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the wc url title of this wc referenced file.
	*
	* @return the wc url title of this wc referenced file
	*/
	@Override
	public java.lang.String getWcUrlTitle() {
		return _wcReferencedFile.getWcUrlTitle();
	}

	/**
	* Sets the wc url title of this wc referenced file.
	*
	* @param wcUrlTitle the wc url title of this wc referenced file
	*/
	@Override
	public void setWcUrlTitle(java.lang.String wcUrlTitle) {
		_wcReferencedFile.setWcUrlTitle(wcUrlTitle);
	}

	/**
	* Returns the dl file uu ID of this wc referenced file.
	*
	* @return the dl file uu ID of this wc referenced file
	*/
	@Override
	public java.lang.String getDlFileUuId() {
		return _wcReferencedFile.getDlFileUuId();
	}

	/**
	* Sets the dl file uu ID of this wc referenced file.
	*
	* @param dlFileUuId the dl file uu ID of this wc referenced file
	*/
	@Override
	public void setDlFileUuId(java.lang.String dlFileUuId) {
		_wcReferencedFile.setDlFileUuId(dlFileUuId);
	}

	/**
	* Returns the orphan of this wc referenced file.
	*
	* @return the orphan of this wc referenced file
	*/
	@Override
	public boolean getOrphan() {
		return _wcReferencedFile.getOrphan();
	}

	/**
	* Returns <code>true</code> if this wc referenced file is orphan.
	*
	* @return <code>true</code> if this wc referenced file is orphan; <code>false</code> otherwise
	*/
	@Override
	public boolean isOrphan() {
		return _wcReferencedFile.isOrphan();
	}

	/**
	* Sets whether this wc referenced file is orphan.
	*
	* @param orphan the orphan of this wc referenced file
	*/
	@Override
	public void setOrphan(boolean orphan) {
		_wcReferencedFile.setOrphan(orphan);
	}

	/**
	* Returns the type of this wc referenced file.
	*
	* @return the type of this wc referenced file
	*/
	@Override
	public java.lang.String getType() {
		return _wcReferencedFile.getType();
	}

	/**
	* Sets the type of this wc referenced file.
	*
	* @param type the type of this wc referenced file
	*/
	@Override
	public void setType(java.lang.String type) {
		_wcReferencedFile.setType(type);
	}

	@Override
	public boolean isNew() {
		return _wcReferencedFile.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_wcReferencedFile.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _wcReferencedFile.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wcReferencedFile.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _wcReferencedFile.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _wcReferencedFile.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wcReferencedFile.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wcReferencedFile.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_wcReferencedFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_wcReferencedFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wcReferencedFile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WcReferencedFileWrapper((WcReferencedFile)_wcReferencedFile.clone());
	}

	@Override
	public int compareTo(
		com.liferay.dl.cleaner.model.WcReferencedFile wcReferencedFile) {
		return _wcReferencedFile.compareTo(wcReferencedFile);
	}

	@Override
	public int hashCode() {
		return _wcReferencedFile.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.dl.cleaner.model.WcReferencedFile> toCacheModel() {
		return _wcReferencedFile.toCacheModel();
	}

	@Override
	public com.liferay.dl.cleaner.model.WcReferencedFile toEscapedModel() {
		return new WcReferencedFileWrapper(_wcReferencedFile.toEscapedModel());
	}

	@Override
	public com.liferay.dl.cleaner.model.WcReferencedFile toUnescapedModel() {
		return new WcReferencedFileWrapper(_wcReferencedFile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wcReferencedFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wcReferencedFile.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wcReferencedFile.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WcReferencedFileWrapper)) {
			return false;
		}

		WcReferencedFileWrapper wcReferencedFileWrapper = (WcReferencedFileWrapper)obj;

		if (Validator.equals(_wcReferencedFile,
					wcReferencedFileWrapper._wcReferencedFile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public WcReferencedFile getWrappedWcReferencedFile() {
		return _wcReferencedFile;
	}

	@Override
	public WcReferencedFile getWrappedModel() {
		return _wcReferencedFile;
	}

	@Override
	public void resetOriginalValues() {
		_wcReferencedFile.resetOriginalValues();
	}

	private WcReferencedFile _wcReferencedFile;
}