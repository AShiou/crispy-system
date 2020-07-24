create table if not exists Decoration (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Cake (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists Cake_Decorations (
    cake bigint not null,
    decoration varchar(4) not null
);

alter table Cake_Decorations
    add foreign key (cake) references Cake(id);
alter table Cake_Decorations
    add foreign key (decoration) references Decoration(id);

create table if not exists Cake_Order (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists Cake_Order_Cakes (
    cakeOrder bigint not null,
    cake bigint not null
);

alter table Cake_Order_Cakes
    add foreign key (cakeOrder) references Cake_Order(id);
alter table Cake_Order_Cakes
    add foreign key (cake) references Cake(id);

