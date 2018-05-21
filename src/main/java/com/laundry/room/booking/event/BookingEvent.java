package com.laundry.room.booking.event;

public class BookingEvent {

    private Integer laundryRoomId;
    private String apartmentNumber;
    private BookingTimeslot timeslot;

    public BookingEvent() {}

    public BookingEvent(Integer laundryRoomId, String apartmentNumber, BookingTimeslot timeslot) {

        this.laundryRoomId = laundryRoomId;
        this.apartmentNumber = apartmentNumber;
        this.timeslot = timeslot;
    }

    public Integer getLaundryRoomId() {
        return laundryRoomId;
    }

    public void setLaundryRoomId(Integer laundryRoomId) {
        this.laundryRoomId = laundryRoomId;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public BookingTimeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(BookingTimeslot timeslot) {
        this.timeslot = timeslot;
    }
}

