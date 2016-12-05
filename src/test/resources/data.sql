insert into station (name) values ('1');
insert into station (name) values ('2');
insert into station (name) values ('3');
insert into station (name) values ('4');
insert into station (name) values ('5');
insert into station (name) values ('6');
insert into station (name) values ('7');
insert into station (name) values ('8');
insert into station (name) values ('9');
insert into station (name) values ('Wien');
insert into station (name) values ('Berlin');
insert into station (name) values ('Cologne');
insert into station (name) values ('Brussels');
insert into station (name) values ('London');
insert into station (name) values ('Middle-Earth');
insert into station (name) values ('Hogwarts');
insert into station (name) values ('Mirror Land');
insert into station (name) values ('Narnia');
insert into station (name) values ('Bratislava');

insert into route (name) values ('1');
insert into route (name) values ('2');
insert into route (name) values ('3');
insert into route (name) values ('4');
insert into route (name) values ('5');
insert into route (name) values ('Trans Europa Express');
insert into route (name) values ('Through The Tunnel');
insert into route (name) values ('Hogwarts Express');
insert into route (name) values ('Through The Wardrobe');
insert into route (name) values ('Through The Looking Glass');

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

insert into route_station (id_route, id_station, ordinal) values (6, 7, 0);
insert into route_station (id_route, id_station, ordinal) values (6, 10, 2);
insert into route_station (id_route, id_station, ordinal) values (6, 19, 1);
insert into route_station (id_route, id_station, ordinal) values (6, 11, 3);

insert into route_station (id_route, id_station, ordinal) values (7, 11, 0);
insert into route_station (id_route, id_station, ordinal) values (7, 12, 1);
insert into route_station (id_route, id_station, ordinal) values (7, 13, 2);
insert into route_station (id_route, id_station, ordinal) values (7, 14, 3);

insert into route_station (id_route, id_station, ordinal) values (8, 14, 0);
insert into route_station (id_route, id_station, ordinal) values (8, 15, 1);
insert into route_station (id_route, id_station, ordinal) values (8, 16, 2);

insert into route_station (id_route, id_station, ordinal) values (9, 16, 0);
insert into route_station (id_route, id_station, ordinal) values (9, 18, 1);
insert into route_station (id_route, id_station, ordinal) values (9, 15, 2);

insert into route_station (id_route, id_station, ordinal) values (10, 16, 0);
insert into route_station (id_route, id_station, ordinal) values (10, 17, 1);

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