create table city
(
    id          varchar(36) not null unique
        primary key,
    latitude    double        not null,
    longitude   double       not null,
    name        varchar(255) null,
    create_time datetime     null default (UTC_TIMESTAMP()),
    update_time datetime     null default (UTC_TIMESTAMP())
);

create table weather
(
    id                    varchar(36)   not null unique
        primary key,
    condition_description varchar(255)   null,
    humidity              int            not null,
    temp_avg              double null,
    temp_max              double null,
    temp_min              double null,
    visibility            int            not null,
    weather_condition     varchar(255)   null,
    wind_speed            double         not null,
    city_id               varchar(255)   null,
    create_time           datetime       null default (UTC_TIMESTAMP()),
    update_time           datetime       null default (UTC_TIMESTAMP()),
    constraint FK_City
        foreign key (city_id) references city (id)
);

