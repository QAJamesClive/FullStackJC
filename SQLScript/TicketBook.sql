drop database if exists db_ticketbook;
create database if not exists db_ticketBook;
use db_ticketBook;
/*drop table tbl_event;*/
create table if not exists tbl_venue(
	venueIDPK int not null auto_increment primary key,
    venueName varchar(30),
    houseNumber varchar(7),
    longitude float  not null,
	latitude float  not null,
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
	personFirstName varchar(15),
    personLastName varchar(15),
    personDateOfBirth date,
    personAdmin boolean not null,
    personUsernamePK char(16) not null primary key,
    personPassword char(16) not null
);
/*drop table tbl_ordert;*/
create table if not exists tbl_order(
	orderIDPK int not null auto_increment primary key,
    personUsernameFK char(16) not null,
    datePurchased date not null,
    foreign key(personUsernameFK)references tbl_person(personUsernamePK)
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
	personUsernameFK char(16),
    eventIDFK int,
    venueIDFK int,
    bandIDFK int,
	foreign key(venueIDFK)references tbl_venue(venueIDPK),
	foreign key(bandIDFK)references tbl_band(bandIDPK),
	foreign key(eventIDFK)references tbl_event(eventIDPK),
	foreign key(personUsernameFK)references tbl_person(personUsernamePK)
);
/*drop table tbl_eventlist;*/
create table if not exists tbl_eventlist(
	bandIDFK int,
    eventIDFK int,
	foreign key(bandIDFK)references tbl_band(bandIDPK),
	foreign key(eventIDFK)references tbl_event(eventIDPK),
    primary key (bandIDFK, eventIDFK)
);


Insert into tbl_band(bandName, bandDescription) values("Linkin Park","A good band description");
Insert into tbl_band(bandName, bandDescription) values("Blue","Not a green band");
Insert into tbl_band(bandName, bandDescription) values("Funky Stuff","A good bandito description");
Insert into tbl_band(bandName, bandDescription) values("The killers","A goodish band");


Insert into tbl_Venue(venueName,longitude,latitude,city,postcode,capacity) values("The tops casino",150.644,-34.397,"city","WS14 NE",2);
Insert into tbl_Venue(venueName,longitude,latitude,city,postcode,capacity) values("Scales",-34.397,150.644,"city","WS14 NE",5);



Insert into tbl_event(eventName, eventDescription, eventStartDate, eventPrice, venueIDFK) values("Dirty mondays","Emo-punk","2018-08-22 20:30:00",3000,1);
Insert into tbl_event(eventName, eventDescription, eventStartDate,eventPrice, venueIDFK) values("Potato party","Just playing potatoes","2018-08-22 20:30:00",4000,1);
Insert into tbl_event(eventName, eventDescription, eventStartDate,eventPrice, venueIDFK) values("Sugar ray dance","Super sweet, super fun!","2018-08-22 20:30:00",2000,2);
Insert into tbl_event(eventName, eventDescription, eventStartDate,eventPrice, venueIDFK) values("Fleebus","Alt-Rock'nd'Roll","2018-08-22 20:30:00",1000,2);

Insert into tbl_person(personFirstName,personLastName,personAdmin,personUsernamePK,personPassword) values("First","Last",true,"Username","password");
Insert into tbl_person(personFirstName,personLastName,personAdmin,personUsernamePK,personPassword) values("James","Clive",true,"JClive94","password");

Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(1,1);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(1,2);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(1,3);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(2,4);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(2,2);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(2,3);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(3,1);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(3,2);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(3,3);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(4,4);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(4,2);
Insert into tbl_eventlist(bandIDFK,eventIDFK) Values(4,3);
