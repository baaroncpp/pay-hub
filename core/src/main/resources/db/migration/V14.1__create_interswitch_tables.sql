CREATE SCHEMA interswitch;

create table interswitch.t_paymentitem(
	paymentcode VARCHAR PRIMARY KEY,
    interswitch_billerid VARCHAR NOT NULL,
    isamountfixed BOOLEAN NOT NULL,
    paymentitemname VARCHAR NOT NULL,
    amount VARCHAR NOT NULL,
    code VARCHAR NULL,
    currencycode VARCHAR NULL,
    currencysymbol VARCHAR NULL,
    createdon TIMESTAMP NOT NULL
);

create table interswitch.t_payment_notification(
    requestreference varchar primary key,
    amount varchar not null,
    surcharge varchar not null,
    customerid varchar not null,
    customermobile varchar not null,
    transactionref  varchar not null,
    customeremail varchar,
    paymentcode varchar not null,
    narration varchar,
    depositorname varchar,
    alternatecustomerid varchar,
    productreference varchar,
    appversion varchar
);

create table interswitch.t_payment_notification_response(
    id varchar primary key,
    responsemessage varchar,
    responsecode varchar,
    customer varchar not null,
    rechargepin varchar,
    transfercode varchar,
    requestreference varchar,
    transactionref varchar not null,
    additionalinfo varchar,
    createdon timestamp not null
);