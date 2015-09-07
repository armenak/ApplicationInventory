create table    users (
  id     integer      not null auto_increment primary key,
  email  varchar(250) not null,
  name   varchar(64)  not null,
  passwd varchar(64)  not null
);
