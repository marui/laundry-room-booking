INSERT INTO apartment_room(id, number, owner) VALUES (1, '201', 'Ma Rui');
INSERT INTO apartment_room(id, number, owner) VALUES (2, '202', 'Tom');
INSERT INTO laundry_room(id, name) VALUES (1, 'the big laundry room');
INSERT INTO laundry_room(id, name) VALUES (2, 'the small laundry room');
INSERT INTO booking(id, laundry_room, apartment_room, time_from, time_to) VALUES (1, 1, 1, '2018-05-25T17:00:00.000Z', '2018-05-25T19:00:00.000Z');
INSERT INTO booking(id, laundry_room, apartment_room, time_from, time_to) VALUES (2, 2, 2, '2018-05-25T20:00:00.000Z', '2018-05-25T22:00:00.000Z');
