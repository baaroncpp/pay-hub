-- password admin
insert into t_user(username, password) values('admin', '$2a$04$i3zwEXB8DA8VSIpEDNvXc.03k6f/jcX3Me7SRu4XamNhLmq0mDO2G');
insert into t_authority(username, authority) values('admin','ROLE_ADMIN.write');

-- password user
insert into t_user(username, password) values('user', '$2a$04$N/8aVsf2XM.nbU1Lwu92k..aHCHVyik785CoZs/rttztaUaP1hKYq');
insert into t_authority(username,authority) values('user','ROLE_USER.read');