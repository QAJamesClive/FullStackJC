GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;
FLUSH PRIVILEGES;
create user if not exists 'user'@'%' Identified by 'password';
GRANT insert, update on *.* to 'user'@'%' identified by 'password';
FLUSH PRIVILEGES;
drop database if exists db_ticketbook;
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
	postcode varchar(8) not null,
    capacity int not null
);
create table if not exists tbl_event(
	eventIDPK int not null auto_increment primary key,
    eventName varchar(30),
	eventDescription varchar(30),
    eventStartDate datetime not null,
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
    personUsername char(16) not null,
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


Insert into tbl_band(bandName, bandDescription) values("A good band name","A good band description");
Insert into tbl_band(bandName, bandDescription) values("A band name","A band description");
Insert into tbl_band(bandName, bandDescription) values("A good bandito name","A good bandito description");
Insert into tbl_band(bandName, bandDescription) values("The killers","A goodish band");


Insert into tbl_Venue(venueName,addressLine1,addressLine2,city,postcode,capacity) values("club1","street","town","city","WS14 NE",2);
Insert into tbl_Venue(venueName,addressLine1,addressLine2,city,postcode,capacity) values("club2","street","town","city","WS14 NE",5);
Insert into tbl_Venue(venueName,addressLine1,addressLine2,city,postcode,capacity) values("club3","street","town","city","WS14 NE",1);
Insert into tbl_Venue(venueName,addressLine1,addressLine2,city,postcode,capacity) values("club4","street","town","city","WS14 NE",1);


Insert into tbl_event(eventName, eventDescription, eventStartDate, eventPrice, venueIDFK) values("Dirty mondays","A goodish band","2018-08-22 20:30:00",3000,1);
Insert into tbl_event(eventName, eventDescription, eventStartDate,eventPrice, venueIDFK) values("Potato party","A goodish band","2018-08-22 20:30:00",4000,1);
Insert into tbl_event(eventName, eventDescription, eventStartDate,eventPrice, venueIDFK) values("Sugar ray dance","A goodish band","2018-08-22 20:30:00",2000,2);
Insert into tbl_event(eventName, eventDescription, eventStartDate,eventPrice, venueIDFK) values("Fleebus","A goodish band","2018-08-22 20:30:00",1000,2);

Insert into tbl_person(personFirstName,personLastName,personDateOfBirth,personAdmin,personUserName,personPassword) values("First","Last",07/05/1994,true,"Username","password");
Insert into tbl_person(personFirstName,personLastName,personDateOfBirth,personAdmin,personUserName,personPassword) values("James","Clive",07/05/1994,true,"JClive94","password");
