create table if not exists tenant (
    id serial primary key,
    name varchar(50) unique,
    creation_date date not null
);

create table if not exists port (
    id serial primary key,
    port integer not null,
    creation_date date not null,
    tenant_id integer not null,
    foreign key (tenant_id) references tenant(id)
);


create table if not exists address (
    id serial primary key,
    address varchar(25) not null,
    creation_date date not null,
    tenant_id integer not null,
    foreign key (tenant_id) references tenant(id)
);

create table if not exists session (
    id serial primary key,
    creation_date date not null,
    tenant_id integer not null,
    foreign key (tenant_id) references tenant(id)
);

create table if not exists scan_result (
	 id SERIAL primary key,
     address varchar(25) not null,
	 port integer not null,
     time_out integer not null,
     exposed boolean not null default false,
     status varchar(10) not null,
     tenant_id integer not null,
     creation_date date not null,
     session_id integer not null,
     foreign key (tenant_id) references tenant(id)
);