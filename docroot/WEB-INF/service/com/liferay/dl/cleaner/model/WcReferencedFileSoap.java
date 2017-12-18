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
 * This class is used by SOAP remote services, specifically {@link com.liferay.dl.cleaner.service.http.WcReferencedFileServiceSoap}.
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.http.WcReferencedFileServiceSoap
 * @generated
 */
public class WcReferencedFileSoap implements Serializable {
	public static WcReferencedFileSoap toSoapModel(WcReferencedFile model) {
		WcReferencedFileSoap soapModel = new WcReferencedFileSoap();

		soapModel.setWcRefencedFileId(model.getWcRefencedFileId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWcUrlTitle(model.getWcUrlTitle());
		soapModel.setDlFileUuId(model.getDlFileUuId());
		soapModel.setOrphan(model.getOrphan());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static WcReferencedFileSoap[] toSoapModels(WcReferencedFile[] models) {
		WcReferencedFileSoap[] soapModels = new WcReferencedFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WcReferencedFileSoap[][] toSoapModels(
		WcReferencedFile[][] models) {
		WcReferencedFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WcReferencedFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WcReferencedFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WcReferencedFileSoap[] toSoapModels(
		List<WcReferencedFile> models) {
		List<WcReferencedFileSoap> soapModels = new ArrayList<WcReferencedFileSoap>(models.size());

		for (WcReferencedFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WcReferencedFileSoap[soapModels.size()]);
	}

	public WcReferencedFileSoap() {
	}

	public long getPrimaryKey() {
		return _wcRefencedFileId;
	}

	public void setPrimaryKey(long pk) {
		setWcRefencedFileId(pk);
	}

	public long getWcRefencedFileId() {
		return _wcRefencedFileId;
	}

	public void setWcRefencedFileId(long wcRefencedFileId) {
		_wcRefencedFileId = wcRefencedFileId;
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

	public String getWcUrlTitle() {
		return _wcUrlTitle;
	}

	public void setWcUrlTitle(String wcUrlTitle) {
		_wcUrlTitle = wcUrlTitle;
	}

	public String getDlFileUuId() {
		return _dlFileUuId;
	}

	public void setDlFileUuId(String dlFileUuId) {
		_dlFileUuId = dlFileUuId;
	}

	public boolean getOrphan() {
		return _orphan;
	}

	public boolean isOrphan() {
		return _orphan;
	}

	public void setOrphan(boolean orphan) {
		_orphan = orphan;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	private long _wcRefencedFileId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _wcUrlTitle;
	private String _dlFileUuId;
	private boolean _orphan;
	private String _type;
}