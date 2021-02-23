ALTER TABLE core.t_agent ADD COLUMN initial_password_reset BOOLEAN not null DEFAULT false;


CREATE TABLE core.t_sms_template (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   category VARCHAR(50) NOT NULL,
   content text not null,
   status VARCHAR(11) NOT NULL DEFAULT 'ACTIVE',
   created_on timestamp default now(),
   modified_on timestamp
);


INSERT INTO core.t_sms_template (name,category,content)
VALUES
('AGENT_REGISTERED','OTP','Account creation is successful for MSISDN ##MSISDN##, PIN ##PIN##. Please modify your PIN using short code option. Thank you.'),
('ACCOUNT_LOCKED','NOTIFICATION','Transaction Failed with TXN Id : ##TRANSACTION_ID##, Exceeded maximum tries (5 times). The account has been locked.'),
('TRANSACTION_SUCCESSFUL','NOTIFICATION','Top up of ##CURRENCY## ##AMOUNT## for ##PRODUCT## is successful. Balance: ##BALANCE_CURRENCY## ##BALANCE_AMOUNT## Trans ID: ##TRANSACTION_ID## Txn Date: ##DATE##'),
('PAYMENT','NOTIFICATION','Paid ##CURRENCY## ##AMOUNT## to ##PRODUCT## for Customer: ##CUSTOMER## successfully. Balance: ##BALANCE_CURRENCY## ##AMOUNT## Trans ID: ##TRANSACTION_ID## Txn Date: ##DATE##'),
('TRANSACTION_TIMEOUT','NOTIFICATION','TransactionId : ##TRANSACTION_ID## cash amount of ##CURRENCY## ##AMOUNT## expired.'),
('ACCOUNT_UNLOCK','NOTIFICATION','Your account has been successfully unlocked. Thank you for using PayHub Money'),
('PIN_RESET','OTP','Your PIN has been reset. NEW PIN ##PIN## valid for 48hrs'),
('FLOAT_ALLOCATION','NOTIFICATION','Allocation to your MSISDN ##MSISDN## of ##CURRENCY## ##AMOUNT## is successful. Your balance is ##BALANCE_CURRENCY##.Txn Id ##TRANSACTION_ID##. Tax ##TAX_CURRNCY## ##TAX_AMOUNT##'),
('LOW_BALANCE','NOTIFICATION','Your transaction TXN Id : ##TRANSACTION_ID## ,cannot be completed due to insufficient balance. Thank you.'),
('COMMISSION_PAYMENT','NOTIFICATION','Commission Payment is successful for PayHub. Amount : ##CURRENCY## ##AMOUNT##');


CREATE TABLE core.t_sms_messages (
    id VARCHAR(32) NOT NULL PRIMARY KEY,
    recipient VARCHAR(20) NOT NULL,
    provider VARCHAR(20),
    content TEXT NOT NULL,
    status VARCHAR(8),
    status_description TEXT,
    is_delivered boolean not null default FALSE,
    created_on TIMESTAMP NOT NULL DEFAULT NOW(),
    modified_on timestamp
);


CREATE TABLE core.t_provider_configuration(
    id SERIAL PRIMARY KEY,
    provider_name VARCHAR(50),
    endpoint TEXT,
    username VARCHAR(100),
    password VARCHAR(100),
    api_key VARCHAR(100),
    connection_port INTEGER,
    connection_timeout INTEGER NOT NULL DEFAULT 1000,
    read_timeout INTEGER NOT NULL DEFAULT 3000,
    write_timeout INTEGER NOT NULL DEFAULT 3000,
    created_on TIMESTAMP not null default now(),
    modified_on timestamp,
    created_by integer not null references core.t_user(id),
    modified_by integer references core.t_user(id)
);

INSERT INTO core.t_provider_configuration (provider_name,endpoint,username,password,api_key,connection_port,created_by)
values
('SKYSMS','http://skylinesms.com','unkown','uknown',null,8080,1);
