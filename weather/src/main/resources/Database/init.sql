create database weather;
use weather;
create table if not exists weather_log
(
    id                  bigint auto_increment
    primary key,
    city                varchar(255) null,
    date                date         null,
    temporary           varchar(255) null,
    temporary_max       varchar(255) null,
    temporary_min       varchar(255) null,
    weather_description varchar(255) null,
    weather_type        varchar(255) null,
    created_by          varchar(255) null,
    created_date        datetime     null,
    updated_by          varchar(255) null,
    updated_date        datetime     null
    );

create index IDXlvm79qqs5j3ph62fx6xioi6gl
    on weather_log (city, date);

insert into weather_log (city, created_by, created_date, date, temporary, temporary_max, temporary_min, updated_by, updated_date, weather_description, weather_type)
values
    ('Ho Chi Minh City', 'admin', '2023-03-20 23:56:34', '2023-03-16', '25.01', '25.01', '25.01', 'admin', '2023-03-20 23:56:34', 'Heavy Rain', 'Rain'),
       ('Ho Chi Minh City', 'admin', '2023-03-20 23:56:34', '2023-03-17', '27.01', '27.01', '27.01', 'admin', '2023-03-20 23:56:34',
        'Sunny', 'Sunny'),
       ('Ho Chi Minh City', 'admin', '2023-03-20 23:56:34', '2023-03-18', '33.01', '33.01', '33.01', 'admin', '2023-03-20 23:56:34',
        'Few wind', 'Cloud'),
       ('Ho Chi Minh City', 'admin', '2023-03-20 23:56:34', '2023-03-19', '32.01', '32.01', '32.01', 'admin', '2023-03-20 23:56:34', 'Sunny', 'Sunny'),
       ('Ho Chi Minh City', 'admin', '2023-03-20 23:56:34', '2023-03-20', '30.01', '30.01', '30.01', 'admin', '2023-03-20 23:56:34', 'Cloudy', 'Cloud'),




