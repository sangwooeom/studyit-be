create type token_type as enum ('ACCESS', 'REFRESH');

create table tb_member (
	seq SERIAL primary key,
	email varchar(100) not null,
	password varchar(1024) not null,
	nickname varchar(300) not null,
	create_dt timestamp default now(),
	update_dt timestamp default now(),
	del_yn boolean default false
);

create table tb_token (
	token varchar(1024) primary key,
	token_type token_type not null,
	member_seq SERIAL not null,
	create_dt timestamp default now(),
	update_dt timestamp default now(),
	del_yn boolean default false,
	constraint fk_token foreign key (member_seq) references tb_member(seq)
);