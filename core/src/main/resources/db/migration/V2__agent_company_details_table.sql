
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
	iso_numeric INTEGER unique not null,
	created_on timestamp not null default now(),
	modified_on timestamp

);

comment on table t_country is 'Holds list of all countries supported in the system';
comment on column t_country.name is 'The actual recognized name of the country';
comment on column t_country.iso_alpha_2 is 'The two letter identifier of country based on ISO standards';
comment on column t_country.iso_alpha_3 is 'The three letter identifier of the country based on ISO standards';
comment on column t_country.iso_numeric is 'The numeric representation/dialing code of the country';


create table t_district (
	id BIGSERIAL primary key,
	country_id INTEGER references t_country(id),
	name VARCHAR(50),
	region VARCHAR(50),
    created_on timestamp not null default now(),
    modified_on timestamp
);

comment on table t_district is 'Holds list of districts specifically for Uganda and other countries that may support same system';
comment on column t_district.country_id is 'The country to which this district belongs, temporarily because Uganda is structured by district';
comment on column t_district.name is 'The name identifier for the district eg Kampala';
comment on column t_district.region is 'The region to which this district belongs like Central or Western';

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
	contact_identification VARCHAR(20) not null,
	contact_identification_number varchar(50) not null,
	contact_identification_path text,
	contact_phone_number varchar(25),
	email varchar(100),
	pv_registration_serial varchar(50),
	note text,
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);

create index t_company_name_idx on t_company (business_name);
create index t_company_email_idx on t_company (email);

comment on table t_company is 'Company details for companies that are registered with payvault';
comment on column t_company.business_name is 'The registered legal name of the business registered';
comment on column t_company.nature_of_business is 'The kind of business in which it is registered for eg IT, construction etc';
comment on column t_company.physical_address is 'The officially recognized location of offices  for the company';
comment on column t_company.district is 'The district to which this company is affiliated';
comment on column t_company.tin_number is 'The tax identification number for the company';
comment on column t_company.registration_country is 'The country in which the company has been officially registered';
comment on column t_company.contact_person is 'The full name of one of the directors of the company to act as the first point of contact';
comment on column t_company.contact_identification is 'And identification document for the contact person';
comment on column t_company.contact_identification_number is 'The ID number for the document presented as identification';
comment on column t_company.contact_identification_path is 'The path to storage of a copy of the identification document';
comment on column t_company.contact_phone_number is 'The contact phone number of the companies first point of contact';
comment on column t_company.email is 'The official email of the company';
comment on column t_company.phone_number is 'The official phone number for the company';
comment on column t_company.pv_registration_serial is 'The serial number of the form submitted during registration of this company on payvault';



create table t_agent (
	id BIGSERIAL primary key,
	type VARCHAR(20) not NULL,
	approved_by INTEGER references t_user(id),
	external_id VARCHAR(20) unique not null,
	pin text,
	username  VARCHAR(20) UNIQUE not null,
	activated_on TIMESTAMP,
	last_reactivated_on TIMESTAMP,
	non_locked BOOLEAN not null DEFAULT true,
	non_disabled BOOLEAN not null DEFAULT true,
	non_locked_pin BOOLEAN not null default true,
	pin_last_updated_on TIMESTAMP,
	last_pin_lock_reason text,
	terms_and_conditions INTEGER not null references t_terms_and_conditions(id),
	company_id BIGINT references t_company(id),
	enrolled_by BIGINT references t_agent(id),
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);


comment on table t_agent is 'Holds list of agents that are supposed can transact through payvault';
comment on column t_agent.type is 'Show whether the agent is a super agent or an ordinary agent';
comment on column t_agent.approved_by is 'The final approving payvault admin that led to the activation of this agent';
comment on column t_agent.external_id is 'A general identifier of this agent outside the database inform of a short code';
comment on column t_agent.pin is 'A hashed 5 digit pin used by the agent to perform transactions';
comment on column t_agent.username is 'The app login usernames for the agent';
comment on column t_agent.activated_on is 'The timestamp of very first activation of the agent';
comment on column t_agent.last_reactivated_on is 'The timestamp for the last time the agent was reactivated after being deactivated';
comment on column t_agent.non_disabled is 'Flag to show if the agent account has been disabled. A disabled account cannot login';
comment on column t_agent.non_locked is 'Flag to show if agent account has been locked. A locked account can login but cannot transact';
comment on column t_agent.non_locked_pin is 'Flag to show if pin is locked. A locked pin cannot be used to login until its reset';
comment on column t_agent.pin_last_updated_on is 'Date on which the pin was last updated';
comment on column t_agent.terms_and_conditions is 'A foreign key to the terms and conditions consented to by the agent';
comment on column t_agent.last_pin_lock_reason is 'The reason for which the pin was last locked for';
comment on column t_agent.company_id is 'The company for the agent, if registered with one';
comment on column t_agent.enrolled_by is 'The super agent that enrolled this agent, if its not a super agent';

create table t_user_meta (

	id BIGSERIAL primary key,
	first_name VARCHAR(50) not NULL,
	last_name varchar(50) not NULL,
	middle_name varchar(50),
	user_id INTEGER REFERENCES t_user(id),
	agent_id BIGINT references t_agent(id) ,
	phone_number VARCHAR(25),
	phone_number_2 VARCHAR(25),
	image_path text,
	display_name VARCHAR(50),
	gender VARCHAR(6) not null,
	birth_date DATE not NULL,
	email VARCHAR(100),
	non_verified_email boolean not null default true,
	non_verified_phone_number boolean not null default true,
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
comment on column t_user_meta.first_name is 'The first name of the user';
comment on column t_user_meta.middle_name is 'The middle name of the user';
comment on column t_user_meta.user_id is 'Reference to the user id that this record belongs to';
comment on column t_user_meta.agent_id is 'Reference to the agent id that this record belongs to';
comment on column t_user_meta.phone_number is 'The phone number of the user';
comment on column t_user_meta.phone_number_2 is 'The alternative phone number of the user';
comment on column t_user_meta.display_name is 'Any display name that may be desired by the user';
comment on column t_user_meta.gender is 'The gender of the user male or female';
comment on column t_user_meta.birth_date is 'The users birth date';
comment on column t_user_meta.email is 'The verified email of the user';
