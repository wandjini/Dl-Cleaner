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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.dl.cleaner.service.http.LostFileServiceSoap}.
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.http.LostFileServiceSoap
 * @generated
 */
public class LostFileSoap implements Serializable {
	public static LostFileSoap toSoapModel(LostFile model) {
		LostFileSoap soapModel = new LostFileSoap();

		soapModel.setLostFileId(model.getLostFileId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setDlFileVersionId(model.getDlFileVersionId());
		soapModel.setDlFileTitle(model.getDlFileTitle());
		soapModel.setDeleted(model.getDeleted());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static LostFileSoap[] toSoapModels(LostFile[] models) {
		LostFileSoap[] soapModels = new LostFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LostFileSoap[][] toSoapModels(LostFile[][] models) {
		LostFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LostFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LostFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LostFileSoap[] toSoapModels(List<LostFile> models) {
		List<LostFileSoap> soapModels = new ArrayList<LostFileSoap>(models.size());

		for (LostFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LostFileSoap[soapModels.size()]);
	}

	public LostFileSoap() {
	}

	public long getPrimaryKey() {
		return _lostFileId;
	}

	public void setPrimaryKey(long pk) {
		setLostFileId(pk);
	}

	public long getLostFileId() {
		return _lostFileId;
	}

	public void setLostFileId(long lostFileId) {
		_lostFileId = lostFileId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getDlFileVersionId() {
		return _dlFileVersionId;
	}

	public void setDlFileVersionId(long dlFileVersionId) {
		_dlFileVersionId = dlFileVersionId;
	}

	public String getDlFileTitle() {
		return _dlFileTitle;
	}

	public void setDlFileTitle(String dlFileTitle) {
		_dlFileTitle = dlFileTitle;
	}

	public boolean getDeleted() {
		return _deleted;
	}

	public boolean isDeleted() {
		return _deleted;
	}

	public void setDeleted(boolean deleted) {
		_deleted = deleted;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	private long _lostFileId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _fileEntryId;
	private long _dlFileVersionId;
	private String _dlFileTitle;
	private boolean _deleted;
	private String _comment;
}