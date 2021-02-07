ALTER TABLE core.t_agent_approval ADD COLUMN note_2 ;
ALTER TABLE core.t_agent_approval ADD COLUMN modified_on TIMESTAMP;
ALTER TABLE core.t_agent_approval ADD COLUMN modified_by INTEGER REFERENCES core.t_user(id);

CREATE TABLE core.t_configuration(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    category VARCHAR(50),
    actual_value TEXT NOT NULL,
    default_value TEXT,
    note TEXT,
    country_id INTEGER NOT NULL REFERENCES core.t_country(id),
    created_by INTEGER REFERENCES core.t_user(id),
    modified_by INTEGER references core.t_user(id),
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    modified_on TIMESTAMP
);

INSERT INTO core.t_configuration (name,actual_value,default_value,note,created_by)
VALUES
('AGENT_CREATE_SMS','Dear %s, your account with PayHub has been successfully created. Login with your pin to begin transacting','Dear %s, your account with PayHub has been successfully created. Login with your pin to begin transacting',
'SMS that will be sent to every transacting agent after first time registration on the platform',1);


CREATE TABLE core.t_account_mapping (
    id SERIAL PRIMARY KEY,
    bank_id INTEGER REFERENCES core.t_bank(id),
    product_id INTEGER REFERENCES core.t_product(id),
    agent_id BIGINT REFERENCES core.t_agent(id),
    account_id NUMERIC NOT NULL REFERENCES core.t_account(id),
    status VARCHAR(20),
    created_on timestamp not null default now(),
    modified_on timestamp,
    created_by integer references core.t_user(id),
    modified_by integer references core.t_user(id)
);

create index t_acc_mapping_bk_index on core.t_account_mapping (bank_id);
create index t_acc_mapping_pdt_index on core.t_account_mapping (product_id);
create index t_acc_mapping_agt_index on core.t_account_mapping (agent_id);
create index t_acc_mapping_act_index on core.t_account_mapping (account_id);




