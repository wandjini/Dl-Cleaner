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
import com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil;

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
public class WcReferencedFileClp extends BaseModelImpl<WcReferencedFile>
	implements WcReferencedFile {
	public WcReferencedFileClp() {
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
	public long getPrimaryKey() {
		return _wcRefencedFileId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWcRefencedFileId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wcRefencedFileId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getWcRefencedFileId() {
		return _wcRefencedFileId;
	}

	@Override
	public void setWcRefencedFileId(long wcRefencedFileId) {
		_wcRefencedFileId = wcRefencedFileId;

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setWcRefencedFileId",
						long.class);

				method.invoke(_wcReferencedFileRemoteModel, wcRefencedFileId);
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

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_wcReferencedFileRemoteModel, groupId);
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

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_wcReferencedFileRemoteModel, companyId);
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

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_wcReferencedFileRemoteModel, userId);
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

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_wcReferencedFileRemoteModel, userName);
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

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_wcReferencedFileRemoteModel, createDate);
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

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_wcReferencedFileRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWcUrlTitle() {
		return _wcUrlTitle;
	}

	@Override
	public void setWcUrlTitle(String wcUrlTitle) {
		_wcUrlTitle = wcUrlTitle;

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setWcUrlTitle", String.class);

				method.invoke(_wcReferencedFileRemoteModel, wcUrlTitle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDlFileUuId() {
		return _dlFileUuId;
	}

	@Override
	public void setDlFileUuId(String dlFileUuId) {
		_dlFileUuId = dlFileUuId;

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setDlFileUuId", String.class);

				method.invoke(_wcReferencedFileRemoteModel, dlFileUuId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getOrphan() {
		return _orphan;
	}

	@Override
	public boolean isOrphan() {
		return _orphan;
	}

	@Override
	public void setOrphan(boolean orphan) {
		_orphan = orphan;

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setOrphan", boolean.class);

				method.invoke(_wcReferencedFileRemoteModel, orphan);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_wcReferencedFileRemoteModel != null) {
			try {
				Class<?> clazz = _wcReferencedFileRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_wcReferencedFileRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWcReferencedFileRemoteModel() {
		return _wcReferencedFileRemoteModel;
	}

	public void setWcReferencedFileRemoteModel(
		BaseModel<?> wcReferencedFileRemoteModel) {
		_wcReferencedFileRemoteModel = wcReferencedFileRemoteModel;
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

		Class<?> remoteModelClass = _wcReferencedFileRemoteModel.getClass();

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

		Object returnValue = method.invoke(_wcReferencedFileRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			WcReferencedFileLocalServiceUtil.addWcReferencedFile(this);
		}
		else {
			WcReferencedFileLocalServiceUtil.updateWcReferencedFile(this);
		}
	}

	@Override
	public WcReferencedFile toEscapedModel() {
		return (WcReferencedFile)ProxyUtil.newProxyInstance(WcReferencedFile.class.getClassLoader(),
			new Class[] { WcReferencedFile.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WcReferencedFileClp clone = new WcReferencedFileClp();

		clone.setWcRefencedFileId(getWcRefencedFileId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setWcUrlTitle(getWcUrlTitle());
		clone.setDlFileUuId(getDlFileUuId());
		clone.setOrphan(getOrphan());
		clone.setType(getType());

		return clone;
	}

	@Override
	public int compareTo(WcReferencedFile wcReferencedFile) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				wcReferencedFile.getCreateDate());

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

		if (!(obj instanceof WcReferencedFileClp)) {
			return false;
		}

		WcReferencedFileClp wcReferencedFile = (WcReferencedFileClp)obj;

		long primaryKey = wcReferencedFile.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{wcRefencedFileId=");
		sb.append(getWcRefencedFileId());
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
		sb.append(", wcUrlTitle=");
		sb.append(getWcUrlTitle());
		sb.append(", dlFileUuId=");
		sb.append(getDlFileUuId());
		sb.append(", orphan=");
		sb.append(getOrphan());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.dl.cleaner.model.WcReferencedFile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>wcRefencedFileId</column-name><column-value><![CDATA[");
		sb.append(getWcRefencedFileId());
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
			"<column><column-name>wcUrlTitle</column-name><column-value><![CDATA[");
		sb.append(getWcUrlTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dlFileUuId</column-name><column-value><![CDATA[");
		sb.append(getDlFileUuId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orphan</column-name><column-value><![CDATA[");
		sb.append(getOrphan());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _wcRefencedFileId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _wcUrlTitle;
	private String _dlFileUuId;
	private boolean _orphan;
	private String _type;
	private BaseModel<?> _wcReferencedFileRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.dl.cleaner.service.ClpSerializer.class;
}