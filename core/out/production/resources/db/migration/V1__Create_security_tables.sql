create table t_user(
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) UNIQUE,
    password TEXT,
    account_locked BOOLEAN NOT NULL DEFAULT FALSE,
    account_expired BOOLEAN NOT NULL DEFAULT FALSE,
    cred_expired BOOLEAN NOT NULL DEFAULT FALSE,
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    modified_on TIMESTAMP
);

create table t_authority (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) REFERENCES t_user(username),
    authority VARCHAR(20)
);

create table t_group (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    note TEXT,
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    modified_on TIMESTAMP
);

create table t_group_authority (
    id SERIAL PRIMARY KEY,
    group_id INTEGER NOT NULL,
    authority VARCHAR(20)
 );


create table t_group_members(
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    group_id INTEGER NOT NULL
);
