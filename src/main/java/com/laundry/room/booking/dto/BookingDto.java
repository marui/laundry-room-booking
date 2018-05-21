package com.laundry.room.booking.dto;


import java.time.LocalDateTime;

public class BookingDto {

    private final Integer id;
    private final Integer laundry_room_id;
    private final String apartment_number;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public BookingDto(Integer id, Integer laundry_room_id, String apartment_number, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.laundry_room_id = laundry_room_id;
        this.apartment_number = apartment_number;
        this.from = from;
        this.to = to;
    }


    @Override
    public String toString() {
        return "BookingDto{" +
                "id='" + id + '\'' +
                "laundry_room_id='" + laundry_room_id + '\'' +
                "apartment_number='" + apartment_number + '\'' +
                "from='" + from + '\'' +
                "to='" + to + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Integer getLaundry_room_id() {
        return laundry_room_id;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
