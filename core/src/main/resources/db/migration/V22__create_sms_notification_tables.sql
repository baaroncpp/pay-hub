create schema notification;

create table notification.t_sms_notification(
    id varchar(32) primary key,
    message varchar,
    sms_delivery varchar(20) not null,
    notification_type varchar(20) not null,
    reference varchar(32) not null unique,
    status varchar(20) not null,
    created_on timestamp not null default now(),
    updated_on timestamp
);

CREATE INDEX reference_index ON notification.t_sms_notification(reference);