
    create table Games (
        id int4 not null,
        endTime timestamp,
        result varchar(255),
        savedTime timestamp,
        startTime timestamp,
        user1 int4,
        user2 int4,
        primary key (id)
    );

    create table StatusOfGame (
        id int4 not null,
        board varchar(255),
        game_id int4,
        occupiedBy varchar(255),
        primary key (id)
    );

    create table Users (
        id int4 not null,
        email varchar(255),
        password varchar(255),
        username varchar(255),
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

    create sequence hibernate_sequence;
