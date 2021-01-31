create schema airtel_money;

CREATE TABLE airtel_money.t_transaction (
    id VARCHAR PRIMARY KEY,
    payermsisdn VARCHAR NOT NULL,
    payeemsisdn VARCHAR NOT NULL,
    amount NUMERIC NOT NULL,
    transactionid VARCHAR NOT NULL UNIQUE,
    transactionstatus VARCHAR,
    createdon TIMESTAMP,
    transactiontype VARCHAR
);