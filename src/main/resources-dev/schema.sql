create type token_type as enum ('ACCESS', 'REFRESH');

create table tb_member (
	seq SERIAL primary key,
	email varchar(100) not null,
	password varchar(1024) not null,
	nickname varchar(300) not null,
	email_auth_yn boolean default false not null,
	create_dt timestamp default now() not null,
	update_dt timestamp default now() not null,
	del_yn boolean default false not null
);

create table tb_token (
	token varchar(1024) primary key,
	token_type token_type not null,
	member_seq SERIAL not null,
	create_dt timestamp default now() not null,
	update_dt timestamp default now() not null,
	del_yn boolean default false not null,
	constraint fk_token foreign key (member_seq) references tb_member(seq)
);