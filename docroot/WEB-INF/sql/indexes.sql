create index IX_60F5A4EA on DlCleaner_UnusedFile (companyId, deleted);
create index IX_E9D9D4E8 on DlCleaner_UnusedFile (groupId, deleted);
create unique index IX_16941F5F on DlCleaner_UnusedFile (groupId, fileEntryId, dlFileVersionId);

create unique index IX_F8B061E3 on DlCleaner_WcReferencedFile (companyId, dlFileUuId);
create unique index IX_F8B061E3 on DlCleaner_WcReferencedFile (companyId, dlFileUuId[$COLUMN_LENGTH:2000000$]);
create index IX_B3C8210C on DlCleaner_WcReferencedFile (companyId, orphan);
create index IX_73D5B992 on DlCleaner_WcReferencedFile (groupId);
create unique index IX_4803B965 on DlCleaner_WcReferencedFile (groupId, dlFileUuId);
create unique index IX_4803B965 on DlCleaner_WcReferencedFile (groupId, dlFileUuId[$COLUMN_LENGTH:2000000$]);