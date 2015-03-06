
    create table Games (
        id integer not null ,
        endTime timestamp,
        user1 int4,
        user2 int4,
        result varchar(255),
        savedTime timestamp,
        startTime timestamp,
        primary key (id)
    );

create table StatusOfGame (
        id integer not null ,
	game_id integer references Games(id),
        board varchar(255),
        occupiedBy varchar(255),
        primary key (id)
    );
    create table Users (
        id integer not null ,
        username varchar(255),
        email varchar(255),
        password varchar(255),
        primary key (id)
    );
    
    alter table Games 
        add constraint FK_2bt79vp22oclklt5knohdbopc 
        foreign key (user1) 
        references Users;

    alter table Games 
        add constraint FK_4asutxshnluko8xhsw4pdsvhf 
        foreign key (user2) 
        references Users;
    
    insert into  Users values(1,'cysun','cysun@localhost.localdomain','abcd');
    
    insert into  Games   values(1,now(),1,null,'win',null,now());
    insert into  Games   values(2,now(),1,null,'loss',null,now());    
    insert into  Games values(3,now(),1,null,null,now(),now());
    
    insert into  StatusOfGame values(1,1,'X,X,X,O,O,_,_,_,_','cysun');
    insert into  StatusOfGame values(2,3,'O,O,_,_,X,X,_,_,_','AI');
    
    create sequence hibernate_sequence START WITH 10;