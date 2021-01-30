CREATE TABLE mtn_bundles.t_mtn_bundle (
    id VARCHAR PRIMARY KEY,
    bundle_name VARCHAR UNIQUE,
    category VARCHAR,
    description VARCHAR
);

CREATE TABLE mtn_bundles.t_mtn_bundle_payment (
    id VARCHAR PRIMARY KEY,
    bundleid VARCHAR UNIQUE NOT NULL,
    amount DOUBLE NOT NULL,
    createdon TIMESTAMP,
    customernumber VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    transactionid VARCHAR NOT NULL,
    description VARCHAR NOT NULL
);