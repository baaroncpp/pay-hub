
ALTER TABLE core.t_user_meta ADD CONSTRAINT agent_and_id_unique UNIQUE (user_id,agent_id);
ALTER TABLE core.t_account ADD CONSTRAINT account_name_unique_1 UNIQUE (name);


INSERT INTO core.t_user_meta
(first_name,last_name,user_id,phone_number,created_by,gender,birth_date,country_id,identification,identification_NUMBER)
values
('Test','User',1,'256777110054',1,'MALE',now(),1,'PASSPORT','123456 6');