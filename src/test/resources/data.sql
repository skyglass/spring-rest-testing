insert into route (name) values ('1');
insert into route (name) values ('2');
insert into route (name) values ('3');
insert into route (name) values ('4');
insert into route (name) values ('5');

insert into station (name) values ('1');
insert into station (name) values ('2');
insert into station (name) values ('3');
insert into station (name) values ('4');
insert into station (name) values ('5');
insert into station (name) values ('6');
insert into station (name) values ('7');
insert into station (name) values ('8');
insert into station (name) values ('9');

insert into route_station (id_route, id_station, ordinal) values (1, 1, 0);
insert into route_station (id_route, id_station, ordinal) values (1, 2, 1);
insert into route_station (id_route, id_station, ordinal) values (1, 3, 2);

insert into route_station (id_route, id_station, ordinal) values (2, 3, 0);
insert into route_station (id_route, id_station, ordinal) values (2, 1, 1);
insert into route_station (id_route, id_station, ordinal) values (2, 4, 2);

insert into route_station (id_route, id_station, ordinal) values (3, 1, 0);
insert into route_station (id_route, id_station, ordinal) values (3, 9, 1);
insert into route_station (id_route, id_station, ordinal) values (3, 4, 2);
insert into route_station (id_route, id_station, ordinal) values (3, 7, 3);
insert into route_station (id_route, id_station, ordinal) values (3, 3, 4);

insert into route_station (id_route, id_station, ordinal) values (4, 4, 0);
insert into route_station (id_route, id_station, ordinal) values (4, 6, 1);
insert into route_station (id_route, id_station, ordinal) values (4, 8, 2);

insert into route_station (id_route, id_station, ordinal) values (5, 3, 0);
insert into route_station (id_route, id_station, ordinal) values (5, 6, 1);
insert into route_station (id_route, id_station, ordinal) values (5, 7, 2);
insert into route_station (id_route, id_station, ordinal) values (5, 8, 3);


insert into user (name, login, password, email) values ('Customer', 'customer', 'customer', 'customer@customer.com');
insert into user (name, login, password, email) values ('Admin', 'admin', 'admin', 'admin@admin.com');
insert into user (name, login, password, email) values ('Audit', 'audit', 'audit', 'audit@audit.com');

insert into authority (name) values ('ADMIN');
insert into authority (name) values ('CUSTOMER');
insert into authority (name) values ('SECURITY');
insert into authority (name) values ('SECURITY_WRITER');
insert into user_authority (id_user, id_authority) values (1, 2);
insert into user_authority (id_user, id_authority) values (2, 1);
insert into user_authority (id_user, id_authority) values (2, 2);
insert into user_authority (id_user, id_authority) values (2, 3);
insert into user_authority (id_user, id_authority) values (2, 4);
insert into user_authority (id_user, id_authority) values (3, 1);
insert into user_authority (id_user, id_authority) values (3, 2);
insert into user_authority (id_user, id_authority) values (3, 3);