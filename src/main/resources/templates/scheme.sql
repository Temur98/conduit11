create table users (
                       id bigint generated always as IDENTITY primary key ,
                       email varchar,
                       username varchar(50) unique,
                       password varchar(50)
);
create table profile (
                       id bigint generated always as IDENTITY primary key ,
                       email varchar,
                       username varchar(50) unique,
                       password varchar(50),
                       bio text default '',
                       image_path text default '',
                       following boolean default false,
                       user_id bigint,
                       foreign key (user_id) references users(id)
);

alter table users add constraint unique_email unique (email);
alter table users alter column password set DATA TYPE varchar;
alter table profile alter column password set DATA TYPE varchar;