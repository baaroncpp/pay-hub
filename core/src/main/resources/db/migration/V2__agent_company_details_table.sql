
create table t_terms_and_conditions (
	id SERIAL primary key,
	version_no varchar(4) unique not null,
	content text not null,
	is_active boolean not null default false,
	created_on timestamp not null default now(),
	modified_on timestamp,
	created_by integer not null references t_user(id),
	modified_by integer references t_user(id)

);


comment on table t_terms_and_conditions is 'Store all terms and conditions that will be used within the system. The content should be renderable to PDF';
comment on column t_terms_and_conditions.version_no is 'The version in the format v1.0,v2.0 etc of the terms and condition';
comment on column t_terms_and_conditions.is_active is 'True/False values indicating if the record can be used. only one record can be active a atime';
comment on column t_terms_and_conditions.content is 'The actual terms and conditions that will be accepted by the user';


create table t_country (
	id SERIAL primary key,
	name VARCHAR(50) not null,
	iso_alpha_2 CHARACTER(2) unique not null,
	iso_alpha_3 CHARACTER(3) unique not null,
	iso_numeric INTEGER unique not null

);

comment on table t_country is 'Holds list of all countries supported in the system';


create table t_district (
	id BIGSERIAL primary key,
	country_id INTEGER references t_country(id),
	name VARCHAR(50),
	region VARCHAR(50)
);

comment on table t_district is 'Holds list of districts specifically for Uganda and other countries that may support same system';

create table t_company (
	id BIGSERIAL primary key,
	business_name VARCHAR(200) not NULL,
	nature_of_business VARCHAR(100),
	physical_address text not NULL,
	phone_number VARCHAR(25) not null,
	district bigint references t_district(id),
	tin_number varchar(50) unique not null,
	registration_country INTEGER references t_country(id),
	contact_person varchar(100),
	contact_identification integer not null,
	contact_identification_number varchar(50) not null,
	contact_identification_path text,
	contact_phone_number varchar(25),
	email varchar(100),
	pv_registration_serial varchar(50),
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);

create index t_company_name_idx on t_company (business_name);
create index t_company_email_idx on t_company (email);

comment on table t_company is 'Company details for companies that are registered with payvault';

create table t_agent (
	id BIGSERIAL primary key,
	type INTEGER not NULL,
	approved_by INTEGER references t_user(id),
	external_id VARCHAR(20) unique not null,
	pin text,
	username  VARCHAR(20) UNIQUE not null,
	activated_on TIMESTAMP,
	non_expired BOOLEAN not null default true,
	non_locked BOOLEAN not null DEFAULT true,
	is_enabled BOOLEAN not null DEFAULT false,
	non_locked_pin BOOLEAN not null default true,
	pin_last_updated_on TIMESTAMP,
	last_pin_lock_reason text,
	terms_and_conditions INTEGER not null references t_terms_and_conditions(id),
	company_id BIGINT not null references t_company(id),
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);


comment on table t_agent is 'Holds list of agents that are supposed can transact through payvault';

create table t_user_meta (

	id BIGSERIAL primary key,
	first_name VARCHAR(50) not NULL,
	last_name varchar(50) not NULL,
	middle_ename varchar(50),
	user_id INTEGER REFERENCES t_user(id),
	agent_id BIGINT references t_agent(id) ,
	phone_number VARCHAR(25),
	phone_number_2 VARCHAR(25),
	image_path text,
	display_name VARCHAR(50),
	gender CHARACTER(1) not null,
	birth_date DATE not NULL,
	email VARCHAR(100),
	country_id INTEGER not null references t_country(id),
	identification VARCHAR(20) not null,
	identification_number VARCHAR(100) not null,
	identification_path text,
	created_on TIMESTAMP not null default NOW(),
	created_by INTEGER,
	modified_on TIMESTAMP,
	modified_by INTEGER

);

create index t_user_meta_phone_one_idx  on t_user_meta(phone_number);

comment on table t_user_meta is 'Additional user information for both agent and system users';