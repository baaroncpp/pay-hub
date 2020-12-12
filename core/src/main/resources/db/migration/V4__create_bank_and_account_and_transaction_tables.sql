create table t_bank_account(
	id SERIAL primary key,
	bank_name varchar(100) not null,
	account_name varchar(100) not null,
	account_number varchar(100) not null,
	branch varchar(100) not null,
	swift_code varchar(3),
	currency varchar(3),
	country_id integer not null references t_country(id),
	created_on timestamp not null default now(),
	modified_on timestamp
);

comment on table  t_agent_product is 'List of products that can be sold by the agent';


create table t_bank_deposits (
	id VARCHAR(32) not null primary key,
	agent_id bigint not null references t_agent(id),
	amount_payed Money,
	amount_captured Money,
	status integer not null,
 	bank_transaction_id varchar(50),
	bank_id varchar(50),
	pay_slip_image_path text,
	pay_slip_timestamp timestamp not null,
	captured_on timestamp,
	captured_by integer references t_user(id),
	note text,
	created_on timestamp,
	created_by integer references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)
);

comment on table  t_bank_deposits is 'Deposits that have been made from by agents to top up their agent accounts';

create table t_agent_customer (
	 id VARCHAR(32) primary key,
	 agent_id bigint not null references t_agent(id),
	 account_id varchar(32),-- agent id and account id form composite key
	 customer_name varchar(200),
	 last_trasacted_on timestamp,
	 last_transaction_amount Money,
	 last_transaction_product integer references t_product(id),
	 total_transactions bigint not null default 1,
	 created_on timestamp not null default now(),
	 modified_on timestamp

);

comment on table  t_agent_customer is 'All customers that have transacted through an agent, the customer is identified by a the account id and agent code';

create table t_product_commission_template (
	id SERIAL primary key,
	type integer not null, --  percent, normal chunk etc
	whole_amount Money,
	percent_amount Decimal,
	currency character(3),
	created_on timestamp not null default now(),
	created_by integer references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);

comment on table  t_product_commission_template is 'A template for commission from which commissions may be generated.';


-- can be created from template above
create table t_product_commission (
	id BIGSERIAL primary key,
	t_agent_id bigint not null references t_agent(id),
	type integer not null,
	whole_amount  Money,
	percent_amount Decimal,
	currency character(3),
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);

comment on table  t_product_commission is 'Commissions that has been earned by agent';


create table t_transaction (

	id VARCHAR(32) not null primary key,
	external_transaction_id varchar(50),
	provider_transaction_id varchar(50),
	product_id integer not null references t_product(id),
	agent_id bigint not null references t_agent(id),
	customer_id varchar(32) not null references t_agent_customer(id),
	amount Money,
	non_reversed boolean not null default true,
	status integer not null , -- pending success etc
	created_on timestamp not null default now(),
	modified_on timestamp not null default now()
);

comment on table  t_transaction is 'All transactions that have been done in the system';