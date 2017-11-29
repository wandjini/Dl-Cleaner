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

package com.liferay.dl.cleaner.model.impl;

import com.liferay.dl.cleaner.model.LostFile;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LostFile in entity cache.
 *
 * @author guywandji
 * @see LostFile
 * @generated
 */
public class LostFileCacheModel implements CacheModel<LostFile>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{lostFileId=");
		sb.append(lostFileId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", dlFileVersionId=");
		sb.append(dlFileVersionId);
		sb.append(", dlFileTitle=");
		sb.append(dlFileTitle);
		sb.append(", deleted=");
		sb.append(deleted);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LostFile toEntityModel() {
		LostFileImpl lostFileImpl = new LostFileImpl();

		lostFileImpl.setLostFileId(lostFileId);
		lostFileImpl.setGroupId(groupId);
		lostFileImpl.setCompanyId(companyId);
		lostFileImpl.setUserId(userId);

		if (userName == null) {
			lostFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			lostFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			lostFileImpl.setCreateDate(null);
		}
		else {
			lostFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			lostFileImpl.setModifiedDate(null);
		}
		else {
			lostFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		lostFileImpl.setFileEntryId(fileEntryId);
		lostFileImpl.setDlFileVersionId(dlFileVersionId);

		if (dlFileTitle == null) {
			lostFileImpl.setDlFileTitle(StringPool.BLANK);
		}
		else {
			lostFileImpl.setDlFileTitle(dlFileTitle);
		}

		lostFileImpl.setDeleted(deleted);

		if (comment == null) {
			lostFileImpl.setComment(StringPool.BLANK);
		}
		else {
			lostFileImpl.setComment(comment);
		}

		lostFileImpl.resetOriginalValues();

		return lostFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lostFileId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fileEntryId = objectInput.readLong();
		dlFileVersionId = objectInput.readLong();
		dlFileTitle = objectInput.readUTF();
		deleted = objectInput.readBoolean();
		comment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lostFileId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(fileEntryId);
		objectOutput.writeLong(dlFileVersionId);

		if (dlFileTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dlFileTitle);
		}

		objectOutput.writeBoolean(deleted);

		if (comment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comment);
		}
	}

	public long lostFileId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long fileEntryId;
	public long dlFileVersionId;
	public String dlFileTitle;
	public boolean deleted;
	public String comment;
}