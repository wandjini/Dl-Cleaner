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

import com.liferay.dl.cleaner.model.WcReferencedFile;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WcReferencedFile in entity cache.
 *
 * @author guywandji
 * @see WcReferencedFile
 * @generated
 */
public class WcReferencedFileCacheModel implements CacheModel<WcReferencedFile>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{wcRefencedFileId=");
		sb.append(wcRefencedFileId);
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
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append(", dlFileUuId=");
		sb.append(dlFileUuId);
		sb.append(", orphan=");
		sb.append(orphan);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WcReferencedFile toEntityModel() {
		WcReferencedFileImpl wcReferencedFileImpl = new WcReferencedFileImpl();

		wcReferencedFileImpl.setWcRefencedFileId(wcRefencedFileId);
		wcReferencedFileImpl.setGroupId(groupId);
		wcReferencedFileImpl.setCompanyId(companyId);
		wcReferencedFileImpl.setUserId(userId);

		if (userName == null) {
			wcReferencedFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			wcReferencedFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			wcReferencedFileImpl.setCreateDate(null);
		}
		else {
			wcReferencedFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wcReferencedFileImpl.setModifiedDate(null);
		}
		else {
			wcReferencedFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (articleId == null) {
			wcReferencedFileImpl.setArticleId(StringPool.BLANK);
		}
		else {
			wcReferencedFileImpl.setArticleId(articleId);
		}

		if (dlFileUuId == null) {
			wcReferencedFileImpl.setDlFileUuId(StringPool.BLANK);
		}
		else {
			wcReferencedFileImpl.setDlFileUuId(dlFileUuId);
		}

		wcReferencedFileImpl.setOrphan(orphan);

		if (type == null) {
			wcReferencedFileImpl.setType(StringPool.BLANK);
		}
		else {
			wcReferencedFileImpl.setType(type);
		}

		wcReferencedFileImpl.resetOriginalValues();

		return wcReferencedFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		wcRefencedFileId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		articleId = objectInput.readUTF();
		dlFileUuId = objectInput.readUTF();
		orphan = objectInput.readBoolean();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(wcRefencedFileId);
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

		if (articleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(articleId);
		}

		if (dlFileUuId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dlFileUuId);
		}

		objectOutput.writeBoolean(orphan);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public long wcRefencedFileId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String articleId;
	public String dlFileUuId;
	public boolean orphan;
	public String type;
}