create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities
(username varchar(50) not null,
 authority varchar(50) not null,
 constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert into public.users values ('user', '{noop}Test@Security_123456', '1');
insert into public.users values ('admin', '{bcrypt}$2a$12$xObqVRS6jC7HXj/WRueEe.BkWFRDmwLmwTOclq/uh0.TgTkC7u49q', '1');

insert into public.authorities values ('user', 'read');
insert into public.authorities values ('admin', 'admin');

