

create table t_otp (
	id BIGSERIAL primary key,
	type varchar(10) not null,
	status integer not null,-- VERIFIED ETC
	code varchar(5) not null,
	user_id integer references t_user(id),
	agent_id bigint references t_agent(id),
	created_on timestamp not null default now(),
	modified_on timestamp
);

comment on table t_otp is 'OTP that wil be sent for customer registration or any other system registrations needed';

create table t_agent_previous_pin (
	id BIGSERIAL primary key,
	agent_id BIGINT not null references t_agent(id),
	pin text not null,
	removal_time timestamp not null,
	note text
);


comment on table t_agent_previous_pin is 'When a user requests for a pin reset, the old pin will be stored in this table';

create table t_user_previous_password (
	id BIGSERIAL primary key,
	user_id BIGINT not null references t_user(id),
	pin text not null,
	removal_time timestamp not null,
	note text

);

comment on table t_user_previous_password is 'When a user requests for a password reset, the old password will be stored in this table';

create table t_account_grouping (
    id BIGSERIAL primary key,
    name VARCHAR(100),
    note text,
    can_bulk_liquidate boolean not null default false,
    created_on timestamp not null default now(),
    modified_on timestamp,
    created_by integer not null references t_user(id),
    modified_by integer references t_user(id)
);


create table t_account (

	id BIGSERIAL primary key,
	name varchar(100),
	code varchar(6) UNIQUE not null,
	type integer, -- collection, bulk payment, service provider etc
	grouping bigint references t_account_grouping(id),
	balance_to_notify_at Money not null,
	balance_notification_sent_on timestamp not null default now(),
	available_balance Money not null default 0,
	actual_balance Money not null default 0,
	status integer not null,
	status_description text not null,
	activated_on timestamp,
	activated_by integer references t_user(id),
	suspended_on timestamp,
	suspended_by timestamp,
	created_on timestamp not null default now(),
	modified_on timestamp,
	created_by integer not null references t_user(id),
	modified_by integer references t_user(id)

);


comment on table t_account is 'The accounts that are involved in handling trasactions eg commssions, charge etc';

create table t_product (
	id SERIAL primary key,
	product_account varchar(6) references t_account(code),
	name varchar(50) not null,
	is_enabled boolean not null default false,
	provider varchar(50),
	category VARCHAR(20),
	product_code varchar(10) unique not null,
	official_name varchar(50),
	has_charge BOOLEAN not null default false,
	has_tarrif boolean not null default false,
	sms_notifiy boolean not null default true,
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer not null references t_user(id)
);


comment on table t_product is 'All items that are sellable within the system are products, before a transaction can be done, there must be a product for it';

create table t_product_charge (
	id BIGSERIAL primary key,
	name VARCHAR(100),
	type VARCHAR(10), -- tariff or percentage charge or normal charge
	charge_amount money,
	charge_percent DECIMAL,
	from_amount MONEY,
	to_amount MONEY,
	tariff_group_id VARCHAR(20),
	status INTEGER not null,
	created_on TIMESTAMP not null default NOW(),
	created_by integer not null references t_user(id),
	modified_by integer not null references t_user(id),
	modified_on timestamp
);


comment on table t_product_charge is 'The charges that will be incured by an agent/customer for using this product. If there is no charge then it will be set to 0';


create table t_account_transaction ( -- useful on product_transaction
	id VARCHAR(32) primary KEY,
	account_id bigint not null references t_account(id),
	transaction_type integer , -- t_withdraw or t_deposit b_withdraw, b_deposit reversal etc
	non_reversal boolean not null default true,
	status integer not null,
	actual_balance_before Money not null,
	actual_balance_after Money not null,
	external_transaction_id varchar(32) not null,
	created_on timestamp not null default now(),
	modified_on timestamp
);

comment on table t_account_transaction is 'List of transactions that have been processed on these accounts';


create table t_agent_product (

	id BIGSERIAL primary key,
	product_code varchar(10) references t_product(product_code),
	status integer not null,
	agent_id bigint not null references t_agent(id)
);

comment on table  t_agent_product is 'List of products that can be sold by the agent';
