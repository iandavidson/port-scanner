create schema if not exists port_scanner;

create table if not exists port_scanner.tenant (
    id serial primary key,
    name varchar(50) unique,
    creation_date date not null
);

create table if not exists port_scanner.port (
    id serial primary key,
    port integer not null,
    creation_date date not null,
    tenant_id integer not null,
    foreign key (tenant_id) references tenant(id)
);

create table if not exists port_scanner.address (
    id serial primary key,
    address varchar(25) not null,
    creation_date date not null,
    tenant_id integer not null,
    foreign key (tenant_id) references tenant(id)
);

create table if not exists port_scanner.session (
    id serial primary key,
    creation_date date not null,
    tenant_id integer not null,
    foreign key (tenant_id) references tenant(id)
);

create table if not exists port_scanner.scan_result (
	 id SERIAL primary key,
     address varchar(25) not null,
	 port integer not null,
     time_out integer not null,
     exposed boolean not null default false,
     status varchar(10) not null,
     creation_date date not null,
     session_id integer not null,
     foreign key (session_id) references session(id)
);