GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;
FLUSH PRIVILEGES;
create user if not exists 'user'@'%' Identified by 'password';
GRANT insert, update on *.* to 'user'@'%' identified by 'password';
FLUSH PRIVILEGES;
/*drop database db_ticketbook;*/
create database if not exists db_ticketBook;
use db_ticketBook;
/*drop table tbl_event;*/
create table if not exists tbl_venue(
	venueIDPK int not null auto_increment primary key,
    venueName varchar(30),
    houseNumber varchar(7),
    addressLine1 varchar(30) not null,
	addressLine2 varchar(30)not null,
	city varchar(30) not null,
	postCode varchar(8) not null,
    capacity int not null
);
create table if not exists tbl_event(
	eventIDPK int not null auto_increment primary key,
    eventStartDate date not null,
	eventPrice int not null,
	ticketsSold int,
    venueIDFK int not null,
	foreign key(venueIDFK)references tbl_venue(venueIDPK)
);
/*drop table tbl_band;*/
create table if not exists tbl_band(
	bandIDPK int not null auto_increment primary key,
    bandName varchar(60) not null,
    bandDescription varchar(600)
);
/*drop table tbl_person;*/
create table if not exists tbl_person(
	personIDPK int not null auto_increment primary key,
	personFirstName varchar(15),
    personLastName varchar(15),
    personDateOfBirth date not null,
    personAdmin boolean not null,
    personUSername char(16) not null,
    personPassword char(16) not null
);
create table if not exists tbl_bandMember(
    bandMemberIDPK int not null auto_increment primary key,
    bandMemberFirstName varchar(15),
    bandMemberLastName varchar(15),
	bandIDFK int,
	foreign key(bandIDFK)references tbl_band(bandIDPK)
);
/*drop table tbl_ordert;*/
create table if not exists tbl_order(
	orderIDPK int not null auto_increment primary key,
    personIDFK int not null,
    datePurchased date not null,
    foreign key(personIDFK)references tbl_person(personIDPK)
);
/*drop table tbl_orderItem;*/
create table if not exists tbl_orderItem(
	orderIDFK int,
    eventIDFK int,
	foreign key(orderIDFK)references tbl_order(orderIDPK),
	foreign key(eventIDFK)references tbl_event(eventIDPK),
    primary key (orderIDFK, eventIDFK)
);
/*drop table tbl_wishlist;*/
create table if not exists tbl_wishlist(
	wishListIDPK int not null auto_increment primary key,
	personIDFK int,
    eventIDFK int,
    venueIDFK int,
    bandIDFK int,
    bandMemberIDFK int,
	foreign key(venueIDFK)references tbl_venue(venueIDPK),
	foreign key(bandIDFK)references tbl_band(bandIDPK),
	foreign key(eventIDFK)references tbl_event(eventIDPK),
	foreign key(personIDFK)references tbl_person(personIDPK),
    foreign key(bandMemberIDFK)references tbl_bandMember(bandMemberIDPK)
);
/*drop table tbl_eventlist;*/
create table if not exists tbl_eventlist(
	bandIDFK int,
    eventIDFK int,
	foreign key(bandIDFK)references tbl_band(bandIDPK),
	foreign key(eventIDFK)references tbl_event(eventIDPK),
    primary key (bandIDFK, eventIDFK)
);
/*drop table tbl_musicians;*/
create table if not exists tbl_musicians(
	bandIDFK int,
    personIDFK int,
    foreign key(bandIDFK)references tbl_band(bandIDPK),
	foreign key(personIDFK)references tbl_person(personIDPK),
    primary key (bandIDFK, personIDFK)
);
