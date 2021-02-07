create table core.t_cash_flow_request
(
	id BIGSERIAL primary key,
	external_reference VARCHAR(32) NOT NULL UNIQUE,
	amount NUMERIC NOT NULL,
	from_acc_transaction_id VARCHAR(32) UNIQUE references core.t_account_transaction(id),
	to_acc_transaction_id VARCHAR(32) UNIQUE references core.t_account_transaction(id),
    product_id INTEGER REFERENCES core.t_product(id),
    agent_id BIGINT REFERENCES core.t_agent(id),
    from_account_id BIGINT REFERENCES core.t_account(id),
    to_account_id BIGINT REFERENCES core.t_account(id),
	approver_1 int8 references core.t_user(id),
	approver_2 int8 references core.t_user(id),
	rejected_by int8 references core.t_user(id),
	flow_type VARCHAR(10), -- MAIN_TO_BUSINESS,  STOCK_WITHDRAW, PROVIDER_LIQUIDATION
	first_approve_on timestamp,
	second_approve_on timestamp,
	note_1 text,
	note_2 text,
	approval_count int4,
	status VARCHAR not null default 'PENDING',
	created_on timestamp not null default now(),
	created_by int8 not null references core.t_user(id),
	modified_on timestamp default now(),
    modified_by int8 references core.t_user(id)

);

create index t_cf_mapping_pdt_index on core.t_account_mapping (product_id);
create index t_cf_mapping_agt_index on core.t_account_mapping (agent_id);
create index t_acc_mapping_pdt_index on core.t_account_mapping (account_id);