create table city
(
    id          varchar(255) not null
        primary key,
    latitude    bigint       not null,
    longitude   bigint       not null,
    name        varchar(255) null,
    create_time datetime     null,
    update_time datetime     null
);

create table weather
(
    id                    varchar(255)   not null
        primary key,
    condition_description varchar(255)   null,
    humidity              int            not null,
    temp_avg              decimal(19, 4) null,
    temp_max              decimal(19, 4) null,
    temp_min              decimal(19, 4) null,
    visibility            int            not null,
    weather_condition     varchar(255)   null,
    wind_speed            double         not null,
    city_id               varchar(255)   null,
    create_time           datetime       null,
    update_time           datetime       null,
    constraint FK_City
        foreign key (city_id) references city (id)
);

