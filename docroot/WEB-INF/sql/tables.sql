create table DlCleaner_FileToClean (
	fileToCleanId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dlFileEntryId LONG,
	dlFileTitle VARCHAR(75) null,
	deleted BOOLEAN
);

create table DlCleaner_LostFile (
	lostFileId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fileEntryId LONG,
	dlFileVersionId LONG,
	dlFileTitle VARCHAR(75) null,
	deleted BOOLEAN,
	comment_ VARCHAR(75) null
);

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
	deleted BOOLEAN,
	comment_ VARCHAR(75) null
);