ALTER TABLE t_agent ADD COLUMN approval_status VARCHAR(9) NOT NULL DEFAULT 'PENDING';

create table core.t_agent_approval
(
	id bigserial primary key,
	agent_id integer references core.t_agent(id),
	approver_1 int8 references core.t_user(id),
	approver_2 int8 references core.t_user(id),
	first_approve_on timestamp,
	second_approve_on timestamp,
	note text,
	approval_count int4,
	status VARCHAR not null default 'PENDING',
	created_on timestamp not null default now(),
	created_by int8 not null references core.t_user(id)

);