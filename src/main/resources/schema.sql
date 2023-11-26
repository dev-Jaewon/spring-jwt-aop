create table account
(
    id bigint not null,
    userId varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    primary key (id)
);