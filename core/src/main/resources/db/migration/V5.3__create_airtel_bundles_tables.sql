create table airtel_data.t_bundle(
	id VARCHAR PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    code VARCHAR NOT NULL,
    durationdays INTEGER NOT NULL,
    price DOUBLE NOT NULL
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
    bundlepaymentid UNIQUE VARCHAR NOT NULL,
    responsedescription VARCHAR,
    bundlepaymentstatus VARCHAR NOT NULL
);