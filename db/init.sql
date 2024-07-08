create table if not exists tenant (
    id serial primary key,
    name VARCHAR(50) UNIQUE
);

--drop table if exists address_scan;

create table if not exists address_scan (
	 id SERIAL primary key,
     ip VARCHAR(25) not null,
	 port integer not null,
     time_out integer not null,
     exposed boolean not null DEFAULT false,
     status VARCHAR(10) not null,
     tenant_id integer not null,
     foreign key (tenant_id) references tenant(id)
);