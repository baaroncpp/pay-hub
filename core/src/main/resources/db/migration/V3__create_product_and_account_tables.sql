

create table t_otp (
	id BIGSERIAL primary key,
	type varchar(20) not null,
	status varchar(20) not null,-- VERIFIED ETC
	code varchar(5) not null,
	user_id integer references t_user(id),
	agent_id bigint references t_agent(id),
	created_on timestamp not null default now(),
	modified_on timestamp
);

comment on table t_otp is 'OTP that wil be sent for customer registration or any other system registrations needed';
comment on column t_otp.type is 'The type of otp like password verification otp, registration otp etc -- refer to enum in code';
comment on column t_otp.status is 'A flag indicating whether the code in pending verification, verified, cancelled or failed verification';
comment on column t_otp.code is 'The code that is meant to be verified';
comment on column t_otp.user_id is 'If present, it represents the user to which the code has been sent';
comment on column t_otp.agent_id is 'If present, it represents the agent to which the code has been sent';

create table t_agent_previous_pin (
	id BIGSERIAL primary key,
	agent_id BIGINT not null references t_agent(id),
	pin text not null,
	removal_time timestamp not null,
	note text,
    created_on timestamp not null default now(),
    modified_on timestamp
);


comment on table t_agent_previous_pin is 'When a user requests for a pin reset, the old pin will be stored in this table';

create table t_user_previous_password (
	id BIGSERIAL primary key,
	user_id BIGINT not null references t_user(id),
	password text not null,
	removal_time timestamp not null,
	note text,
    created_on timestamp not null default now(),
    modified_on timestamp

);

comment on table t_user_previous_password is 'When a user requests for a password reset, the old password will be stored in this table';

create table t_account_grouping (
    id BIGSERIAL primary key,
    name VARCHAR(100) unique not null,
    note text,
    can_bulk_liquidate boolean not null default false,
    created_on timestamp not null default now(),
    modified_on timestamp,
    created_by integer not null references t_user(id),
    modified_by integer references t_user(id)
);

comment on table t_account_grouping is 'A group of accounts that can be managed in group operations eg cellulant accounts of all products provided by cellulant';
comment on column t_account_grouping.name is 'Name of the group';

create table t_account (

	id BIGSERIAL primary key,
	name varchar(100),
	code varchar(6) UNIQUE not null,
	type integer, -- collection, bulk payment, service provider etc
	grouping bigint references t_account_grouping(id),
	balance_to_notify_at NUMERIC not null,
	balance_notification_sent_on timestamp not null default now(),
	available_balance NUMERIC not null default 0,
	status integer not null,
	status_description text not null,
	activated_on timestamp,
	activated_by integer references t_user(id),
	suspended_on timestamp,
	suspended_by integer references t_user(id),
    closed_on timestamp,
    closed_by integer references t_user(id),
	created_on timestamp not null default now(),
	modified_on timestamp,
	created_by integer not null references t_user(id),
	modified_by integer references t_user(id)

);


comment on table t_account is 'The accounts that are involved in handling trasactions eg commssions, charge etc';
comment on column t_account.name is 'The name of this account';
comment on column t_account.code is 'A short 1 - 6 character code representing this account';
comment on column t_account.type is 'The type of account in question. This can be collection, commission, main account etc';
comment on column t_account.grouping is 'The group to which this account belongs';
comment on column t_account.balance_notification_sent_on is 'The last time on which a notification was sent for a low balance on this account';
comment on column t_account.balance_to_notify_at is 'The minimum available balance amount at which a notification should be sent';
comment on column t_account.available_balance is 'The available balance on this account that can actually be transacted with';
comment on column t_account.status is 'Flag to indicate the whether the account is operable';
comment on column t_account.status_description is 'A description of the status at which the account is at';
comment on column t_account.activated_on is 'The date on which this account was activated';
comment on column t_account.suspended_on is 'The date on which this account was suspended if at all';
comment on column t_account.closed_on is 'The date on which this account was closed if it is closed';
comment on column t_account.activated_by is 'The individual that activated this account';
comment on column t_account.suspended_by is 'The individual (payvault) that suspended this account';
comment on column t_account.closed_by is 'The individual (payvault) that closed this account';


create table t_product (
	id SERIAL primary key,
	product_account varchar(6) references t_account(code),
	name varchar(50) unique not null,
	non_active boolean not null default true,
	provider varchar(50),
	category VARCHAR(20),
	product_code varchar(10) unique not null,
	official_name varchar(50),
	has_charge BOOLEAN not null default false,
	has_tariff boolean not null default false,
	sms_notify boolean not null default false,
	created_on timestamp not null default now(),
	created_by integer not null references t_user(id),
	modified_on timestamp,
	modified_by integer not null references t_user(id)
);


comment on table t_product is 'All items that are sellable within the system are products, before a transaction can be done, there must be a product for it';
comment on column t_product.product_account is 'The transactional account that is attached to this product';
comment on column t_product.name is 'The name identifier for this product';
comment on column t_product.non_active is 'Flag to indicate if product is active or not';
comment on column t_product.provider is 'The integration partner if any that owns this product';
comment on column t_product.category is 'The category to which this product belongs, refer to enums in code';
comment on column t_product.product_code is 'A unqiue code to identify the product within payvault';
comment on column t_product.official_name is 'The public known name of this product';
comment on column t_product.has_charge is 'If this product has a charge';
comment on column t_product.has_tariff is 'If this product has a tariff';
comment on column t_product.sms_notify is 'Flag to indicate if SMS should be sent when this product is transacted with';

create table t_product_charge (
	id BIGSERIAL primary key,
	name VARCHAR(100),
	type VARCHAR(10), -- tariff or percentage charge or normal charge
	charge_amount NUMERIC,
	charge_percent DECIMAL,
	from_amount NUMERIC,
	to_amount NUMERIC,
	tariff_group_id VARCHAR(20),
	non_active BOOLEAN not null default true,
	created_on TIMESTAMP not null default NOW(),
	created_by integer not null references t_user(id),
	modified_by integer not null references t_user(id),
	modified_on timestamp
);


comment on table t_product_charge is 'The charges that will be incurred by an agent/customer for using this product. If there is no charge then it will be set to 0';
comment on column t_product_charge.name is 'The name of the product';
comment on column t_product_charge.type is 'The type of charge that is being applied, whether its a tariff, flat_charge or percentage charge';
comment on column t_product_charge.charge_amount is 'The actual charge amount for tariff or flat charge';
comment on column t_product_charge.charge_percent is 'The percentage charge if its a percent charge';
comment on column t_product_charge.from_amount is 'The start amount for a tariff charge';
comment on column t_product_charge.to_amount is 'The end amount for a tariff charge';
comment on column t_product_charge.tariff_group_id is 'The grouping for a set of tariff charges';
comment on column t_product_charge.non_active is 'Flag to indicate if this tariff is active or disabled';


create table t_account_transaction ( -- useful on product_transaction
	id VARCHAR(32) primary KEY,
	account_id bigint not null references t_account(id),
	transaction_type varchar(20) , -- t_withdraw or t_deposit b_withdraw, b_deposit reversal etc
	non_reversal boolean not null default true,
	status varchar(20) not null,
	status_description text,
	balance_before NUMERIC not null,
	balance_after NUMERIC not null,
	external_transaction_id varchar(32) not null,
	created_on timestamp not null default now(),
	modified_on timestamp
);

comment on table t_account_transaction is 'List of transactions that have been processed on these accounts';
comment on column t_account_transaction.account_id is 'The id of the account on which this transaction has been performed';
comment on column t_account_transaction.transaction_type is 'Whether its a debit (increasing) or a credit (reducing) on value on account';
comment on column t_account_transaction.non_reversal is 'A flag to indicate that this transaction has not been reversed';
comment on column t_account_transaction.status is 'A flag to indicate the status of this transaction in the system';
comment on column t_account_transaction.status_description is 'A description of the status above';
comment on column t_account_transaction.balance_before is 'The balance before this transaction was performed';
comment on column t_account_transaction.balance_after is 'The balance after this transaction was performed';
comment on column t_account_transaction.external_transaction_id is 'The id of this transaction on third party systems';


create table t_agent_product (

	id BIGSERIAL primary key,
	product_code varchar(10) references t_product(product_code),
	non_active boolean not null default true,
	agent_id bigint not null references t_agent(id),
    created_on timestamp not null default now(),
    modified_on timestamp
);

comment on table  t_agent_product is 'List of products that can be sold by the agent';
