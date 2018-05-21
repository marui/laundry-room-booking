package com.laundry.room.booking.event;

import com.laundry.room.booking.exception.TimeslotException;

import java.time.LocalDateTime;

public class BookingTimeslot {

    private LocalDateTime from;

    private LocalDateTime to;

    public BookingTimeslot() {

    }

    public void varifyDignity(LocalDateTime from, LocalDateTime to) {
        if (from.isAfter(to) || to.isBefore(LocalDateTime.now()) || from.getHour() < 7 || to.getHour() > 22) {
            throw new TimeslotException();
        }
    }

    public BookingTimeslot(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

}
