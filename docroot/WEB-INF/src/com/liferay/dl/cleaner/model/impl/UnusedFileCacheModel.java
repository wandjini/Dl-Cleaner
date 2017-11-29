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

import com.liferay.dl.cleaner.model.UnusedFile;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UnusedFile in entity cache.
 *
 * @author guywandji
 * @see UnusedFile
 * @generated
 */
public class UnusedFileCacheModel implements CacheModel<UnusedFile>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{unusedFileId=");
		sb.append(unusedFileId);
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
	public UnusedFile toEntityModel() {
		UnusedFileImpl unusedFileImpl = new UnusedFileImpl();

		unusedFileImpl.setUnusedFileId(unusedFileId);
		unusedFileImpl.setGroupId(groupId);
		unusedFileImpl.setCompanyId(companyId);
		unusedFileImpl.setUserId(userId);

		if (userName == null) {
			unusedFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			unusedFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			unusedFileImpl.setCreateDate(null);
		}
		else {
			unusedFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			unusedFileImpl.setModifiedDate(null);
		}
		else {
			unusedFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		unusedFileImpl.setFileEntryId(fileEntryId);
		unusedFileImpl.setDlFileVersionId(dlFileVersionId);

		if (dlFileTitle == null) {
			unusedFileImpl.setDlFileTitle(StringPool.BLANK);
		}
		else {
			unusedFileImpl.setDlFileTitle(dlFileTitle);
		}

		unusedFileImpl.setDeleted(deleted);

		if (comment == null) {
			unusedFileImpl.setComment(StringPool.BLANK);
		}
		else {
			unusedFileImpl.setComment(comment);
		}

		unusedFileImpl.resetOriginalValues();

		return unusedFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		unusedFileId = objectInput.readLong();
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
		objectOutput.writeLong(unusedFileId);
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

	public long unusedFileId;
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