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
comment on column t_bank_account.bank_name is 'The name of the bank for which the account belongs';
comment on column t_bank_account.account_name is 'The name of the account';
comment on column t_bank_account.account_number is 'The bank designated account number';
comment on column t_bank_account.branch is 'The branch at which the account was created';
comment on column t_bank_account.swift_code is 'The bank swift code';
comment on column t_bank_account.currency is 'The currency for which this bank account was created';


create table t_bank_deposits (
	id VARCHAR(32) not null primary key,
	agent_id bigint not null references t_agent(id),
	deposit_amount Money,
	status VARCHAR(15) not null,
 	bank_reference varchar(50),
	bank_id varchar(50),
	pay_slip_image_path text,
	pay_slip_timestamp timestamp not null,
	note text,
	created_on timestamp,
	created_by integer references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)
);

comment on table  t_bank_deposits is 'Deposits that have been made from by agents to top up their agent accounts';
comment on column t_bank_deposits.agent_id is 'The agent that made the deposit and requested for top up';
comment on column t_bank_deposits.deposit_amount is 'The actual amount that the agent has paid in the bank';
comment on column t_bank_deposits.status is 'The status of this agent top up request';
comment on column t_bank_deposits.bank_reference is 'The id on the bank slip submitted';
comment on column t_bank_deposits.bank_id is 'The id of the bank from (t_bank)';
comment on column t_bank_deposits.pay_slip_image_path is 'A photocopy/image of the cash in slip';
comment on column t_bank_deposits.pay_slip_timestamp is 'The time indicated on the payslip at which the cashin was captured';



create table t_agent_customer (
	 id VARCHAR(32) primary key,
	 agent_id bigint not null references t_agent(id),
	 account_id varchar(32),-- agent id and account id form composite key
	 customer_name varchar(200),
	 last_transacted_on timestamp,
	 last_transaction_amount Money,
	 last_transaction_product integer references t_product(id),
	 total_transactions bigint not null default 1,
	 created_on timestamp not null default now(),
	 modified_on timestamp

);

comment on table  t_agent_customer is 'All customers that have transacted through an agent, the customer is identified by a the account id and agent code';
comment on column t_agent_customer.agent_id is 'The agent to which this customer belongs';
comment on column t_agent_customer.customer_name is 'The name captured during customer verification if any';
comment on column t_agent_customer.account_id is 'The account for the customer at third party provider';
comment on column t_agent_customer.last_transacted_on is 'The last time the customer transacted';
comment on column t_agent_customer.last_transaction_product is 'The last product that was transacted by this customer';
comment on column t_agent_customer.total_transactions is 'Total transactions that has been performed by this customer with this agent';


create table t_product_commission_template (
	id SERIAL primary key,
	type varchar(15) not null, --  percent, normal chunk etc
	flat_amount Money,
	percent_amount Decimal,
	currency character(3),
	active boolean not null default true,
	created_on timestamp not null default now(),
	created_by integer references t_user(id),
	modified_on timestamp,
	modified_by integer references t_user(id)

);

comment on table  t_product_commission_template is 'A template for commission from which commissions may be generated.';
comment on column  t_product_commission_template.flat_amount is 'The amount that is ';


-- can be created from template above
create table t_product_commission (
	id BIGSERIAL primary key,
	t_agent_id bigint not null references t_agent(id),
	type varchar(15) not null,
	flat_amount  Money,
	percent_amount Decimal,
	active boolean not null default true,
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
	reversed boolean not null default false,
	status varchar(15) not null , -- pending success etc
	provider_status varchar(15),
	provider_status_description text,
	created_on timestamp not null default now(),
	modified_on timestamp not null default now()
);

comment on table  t_transaction is 'All transactions that have been done in the system';