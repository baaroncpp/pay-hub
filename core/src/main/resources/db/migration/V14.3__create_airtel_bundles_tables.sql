create schema airtel_data;

create table airtel_data.t_bundle(
	id VARCHAR PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    code VARCHAR NOT NULL,
    durationdays INTEGER NOT NULL,
    price NUMERIC NOT NULL
);

create table airtel_data.t_bundle_payment(
	id VARCHAR PRIMARY KEY,
    bundleid VARCHAR NOT NULL,
    createdon TIMESTAMP,
    status VARCHAR NOT NULL,
    customernumber VARCHAR NOT NULL
);

create table airtel_data.t_bundle_receipt(
	id VARCHAR PRIMARY KEY,
    bundlepaymentid VARCHAR NOT NULL UNIQUE,
    responsedescription VARCHAR,
    bundlepaymentstatus VARCHAR NOT NULL
);