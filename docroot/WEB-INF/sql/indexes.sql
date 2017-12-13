create index IX_EA70AEE6 on DlCleaner_FileToClean (groupId, deleted);

create index IX_22FBBC76 on DlCleaner_LostFile (groupId, deleted);
create unique index IX_D5A61C91 on DlCleaner_LostFile (groupId, fileEntryId, dlFileVersionId);

create index IX_60F5A4EA on DlCleaner_UnusedFile (companyId, deleted);
create index IX_E9D9D4E8 on DlCleaner_UnusedFile (groupId, deleted);
create unique index IX_16941F5F on DlCleaner_UnusedFile (groupId, fileEntryId, dlFileVersionId);

create index IX_D37A8D23 on DlCleaner_WcOrphanFile (groupId);
create unique index IX_683ABB76 on DlCleaner_WcOrphanFile (groupId, dlFileUuId);

create unique index IX_F8B061E3 on DlCleaner_WcReferencedFile (companyId, dlFileUuId);
create index IX_B3C8210C on DlCleaner_WcReferencedFile (companyId, orphan);
create index IX_73D5B992 on DlCleaner_WcReferencedFile (groupId);
create unique index IX_4803B965 on DlCleaner_WcReferencedFile (groupId, dlFileUuId);
create unique index IX_3E41959C on DlCleaner_WcReferencedFile (groupId, dlFileUuId, articleId);