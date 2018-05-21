# laundry-room-booking
============================================================================

Java version: 1.8
Memory database: H2
Build tool : Maven
Packaging : WAR

Spring components:
Boot, Data, Rest

Build:
$ mvn clean install

Test: 
$ mvn clean test

Run:
$ mvn spring-boot:run


Test URLS:
==================================================

Book a time in a laundry room
==============================
Endpoint: localhost:8080/api/v1/booking

Request
Method: POST

Body:

    {
        "laundryRoomId": 1,
        "apartmentNumber": "201",
        "timeslot": {
        	"from": "2018-05-25T10:00:00.000Z",
        	"to": "2018-05-25T12:00:00.000Z"
        }
    }

Response
201(created), 409(conflict)

Body:

    {
        "id": 3,
        "laundry_room_id": 1,
        "apartment_number": "201",
        "from": "2018-05-25T10:00:00.000Z",
        "to": "2018-05-25T12:00:00.000Z"
    }
 
Cancel a booking
================
Endpoint: localhost:8080/api/v1/booking/1

Request
Method: DELETE

Response
200(ok), 204(No Content)

List all bookings
=================

Endpoint: localhost:8080/api/v1/booking

Request
Method: GET

Response
200(ok)

Body:

    [
        {
            "id": 1,
            "laundry_room_id": 1,
            "apartment_number": "201",
            "from": "2018-05-20T18:00:00.000Z",
            "to": "2018-05-20T20:00:00.000Z"
        },
        {
            "id": 2,
            "laundry_room_id": 1,
            "apartment_number": "301",
            "from": "2018-05-20T20:00:00.000Z",
            "to": "2018-05-20T22:00:00.000Z"
        },
        {
            "id": 3,
            "laundry_room_id": 2,
            "apartment_number": "202",
            "from": "2018-05-20T18:00:00.000Z",
            "to": "2018-05-20T20:00:00.000Z"
        },
        {
            "id": 4,
            "laundry_room_id": 2,
            "apartment_number": "302",
            "from": "2018-05-20T20:00:00.000Z",
            "to": "2018-05-20T22:00:00.000Z"
        }
    ]

List bookings by laundry room id
================================

Endpoint: localhost:8080/api/v1/booking?laundry-room-id=1

Request
Method: GET

Response
200(ok), 204(no content)

Body:

    [
        {
            "id": 1,
            "laundry_room_id": 1,
            "apartment_number": "201",
            "from": "2018-05-20T18:00:00.000Z",
            "to": "2018-05-20T20:00:00.000Z"
        },
        {
            "id": 2,
            "laundry_room_id": 1,
            "apartment_number": "301",
            "from": "2018-05-20T20:00:00.000Z",
            "to": "2018-05-20T22:00:00.000Z"
        }
    ]
