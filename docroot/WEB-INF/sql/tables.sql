create table DlCleaner_UnusedFile (
	unusedFileId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fileEntryId LONG,
	dlFileVersionId LONG,
	dlFileTitle VARCHAR(75) null,
	dlFileUuId TEXT null,
	deleted BOOLEAN,
	comment_ TEXT null
);

create table DlCleaner_WcReferencedFile (
	wcRefencedFileId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	wcUrlTitle TEXT null,
	dlFileUuId TEXT null,
	orphan BOOLEAN,
	type_ VARCHAR(75) null
);