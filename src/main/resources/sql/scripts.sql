create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities
(username varchar(50) not null,
 authority varchar(50) not null,
 constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

create table customer (
    id      bigserial    primary key,
    email   varchar(255) not null unique,
    password varchar(500) not null,
    role    varchar(50)  not null
);

create table error_codes (
    id          bigserial    primary key,
    code        int          not null unique,
    name        varchar(100) not null unique,
    description text         not null
);

insert into public.error_codes (code, name, description) values (1001, 'INVALID_CREDENTIALS', 'The provided username or password is incorrect.');
insert into public.error_codes (code, name, description) values (1002, 'USER_NOT_FOUND', 'No account found with the provided email address.');
insert into public.error_codes (code, name, description) values (1003, 'USER_ALREADY_EXISTS', 'An account with this email address already exists.');
insert into public.error_codes (code, name, description) values (1004, 'ACCESS_DENIED', 'You do not have permission to access this resource.');

insert into public.customer (email, password, role) values ('admin@example.com', '{bcrypt}$2a$12$xObqVRS6jC7HXj/WRueEe.BkWFRDmwLmwTOclq/uh0.TgTkC7u49q', 'admin');
insert into public.customer (email, password, role) values ('user@example.com', '{noop}Test@Security_123456', 'read');

insert into public.users values ('user', '{noop}Test@Security_123456', '1');
insert into public.users values ('admin', '{bcrypt}$2a$12$xObqVRS6jC7HXj/WRueEe.BkWFRDmwLmwTOclq/uh0.TgTkC7u49q', '1');

insert into public.authorities values ('user', 'read');
insert into public.authorities values ('admin', 'admin');

