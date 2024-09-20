alter table estetica.users add unique(mail);
alter table estetica.users alter column mail SET NOT NULL;