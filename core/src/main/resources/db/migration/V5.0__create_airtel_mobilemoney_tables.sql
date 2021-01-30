CREATE TABLE airtel_money.t_transaction (
    id VARCHAR PRIMARY KEY,
    payermsisdn VARCHAR NOT NULL,
    payeemsisdn VARCHAR NOT NULL,
    amount DOUBLE NOT NULL DEFAULT,
    transactionid VARCHAR NOT NULL UNIQUE,
    transactionstatus VARCHAR,
    createdon TIMESTAMP,
    transactiontype VARCHAR
);