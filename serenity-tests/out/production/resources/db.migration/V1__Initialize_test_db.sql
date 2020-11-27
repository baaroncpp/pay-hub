insert into t_user(username, password)
values('admin', '$2a$04$lcVPCpEk5DOCCAxOMleFcOJvIiYURH01P9rx1Y/pl.wJpkNTfWO6u');
insert into t_authority(username, authority)
values('admin','ROLE_ADMIN.write');
insert into t_user(username, password)
values('user', '$2a$04$nbz5hF5uzq3qsjzY8ZLpnueDAvwj4x0U9SVtLPDROk4vpmuHdvG3a');
insert into t_authority(username,authority)
values('user','ROLE_USER.read');