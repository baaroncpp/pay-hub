CREATE TABLE mtn_mobile_money.t_transaction_approvals (
    id VARCHAR PRIMARY KEY,
    transacitonid VARCHAR  UNIQUE NOT NULL,
    approvedon TIMESTAMP
);

CREATE TABLE mtn_mobile_money.t_transaction_approvals (
    id VARCHAR PRIMARY KEY,
    payermsisdn VARCHAR NOT NULL,
    payeemsisdn VARCHAR NOT NULL,
    amount DOUBLE NOT NULL DEFAULT,
    transactionid VARCHAR NOT NULL UNIQUE,
    transactionstatus VARCHAR,
    createdon TIMESTAMP,
    transactiontype VARCHAR
);