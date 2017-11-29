create index IX_EA70AEE6 on DlCleaner_FileToClean (groupId, deleted);

create index IX_22FBBC76 on DlCleaner_LostFile (groupId, deleted);

create index IX_E9D9D4E8 on DlCleaner_UnusedFile (groupId, deleted);