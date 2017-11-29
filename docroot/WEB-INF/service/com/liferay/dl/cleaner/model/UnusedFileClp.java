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

import com.liferay.dl.cleaner.service.ClpSerializer;
import com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guywandji
 */
public class UnusedFileClp extends BaseModelImpl<UnusedFile>
	implements UnusedFile {
	public UnusedFileClp() {
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
	public long getPrimaryKey() {
		return _unusedFileId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUnusedFileId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _unusedFileId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getUnusedFileId() {
		return _unusedFileId;
	}

	@Override
	public void setUnusedFileId(long unusedFileId) {
		_unusedFileId = unusedFileId;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setUnusedFileId", long.class);

				method.invoke(_unusedFileRemoteModel, unusedFileId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_unusedFileRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_unusedFileRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_unusedFileRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_unusedFileRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_unusedFileRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_unusedFileRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setFileEntryId", long.class);

				method.invoke(_unusedFileRemoteModel, fileEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDlFileVersionId() {
		return _dlFileVersionId;
	}

	@Override
	public void setDlFileVersionId(long dlFileVersionId) {
		_dlFileVersionId = dlFileVersionId;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setDlFileVersionId", long.class);

				method.invoke(_unusedFileRemoteModel, dlFileVersionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDlFileTitle() {
		return _dlFileTitle;
	}

	@Override
	public void setDlFileTitle(String dlFileTitle) {
		_dlFileTitle = dlFileTitle;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setDlFileTitle", String.class);

				method.invoke(_unusedFileRemoteModel, dlFileTitle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDeleted() {
		return _deleted;
	}

	@Override
	public boolean isDeleted() {
		return _deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		_deleted = deleted;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setDeleted", boolean.class);

				method.invoke(_unusedFileRemoteModel, deleted);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getComment() {
		return _comment;
	}

	@Override
	public void setComment(String comment) {
		_comment = comment;

		if (_unusedFileRemoteModel != null) {
			try {
				Class<?> clazz = _unusedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setComment", String.class);

				method.invoke(_unusedFileRemoteModel, comment);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getUnusedFileRemoteModel() {
		return _unusedFileRemoteModel;
	}

	public void setUnusedFileRemoteModel(BaseModel<?> unusedFileRemoteModel) {
		_unusedFileRemoteModel = unusedFileRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _unusedFileRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_unusedFileRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			UnusedFileLocalServiceUtil.addUnusedFile(this);
		}
		else {
			UnusedFileLocalServiceUtil.updateUnusedFile(this);
		}
	}

	@Override
	public UnusedFile toEscapedModel() {
		return (UnusedFile)ProxyUtil.newProxyInstance(UnusedFile.class.getClassLoader(),
			new Class[] { UnusedFile.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		UnusedFileClp clone = new UnusedFileClp();

		clone.setUnusedFileId(getUnusedFileId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setFileEntryId(getFileEntryId());
		clone.setDlFileVersionId(getDlFileVersionId());
		clone.setDlFileTitle(getDlFileTitle());
		clone.setDeleted(getDeleted());
		clone.setComment(getComment());

		return clone;
	}

	@Override
	public int compareTo(UnusedFile unusedFile) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), unusedFile.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UnusedFileClp)) {
			return false;
		}

		UnusedFileClp unusedFile = (UnusedFileClp)obj;

		long primaryKey = unusedFile.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{unusedFileId=");
		sb.append(getUnusedFileId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append(", dlFileVersionId=");
		sb.append(getDlFileVersionId());
		sb.append(", dlFileTitle=");
		sb.append(getDlFileTitle());
		sb.append(", deleted=");
		sb.append(getDeleted());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.dl.cleaner.model.UnusedFile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>unusedFileId</column-name><column-value><![CDATA[");
		sb.append(getUnusedFileId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dlFileVersionId</column-name><column-value><![CDATA[");
		sb.append(getDlFileVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dlFileTitle</column-name><column-value><![CDATA[");
		sb.append(getDlFileTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deleted</column-name><column-value><![CDATA[");
		sb.append(getDeleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _unusedFileId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _fileEntryId;
	private long _dlFileVersionId;
	private String _dlFileTitle;
	private boolean _deleted;
	private String _comment;
	private BaseModel<?> _unusedFileRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.dl.cleaner.service.ClpSerializer.class;
}