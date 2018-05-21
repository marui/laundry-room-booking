package com.laundry.room.booking.service;

import com.laundry.room.booking.dto.BookingDto;
import com.laundry.room.booking.event.BookingEvent;
import com.laundry.room.booking.event.BookingTimeslot;
import com.laundry.room.booking.exception.NonExistEntityException;
import com.laundry.room.booking.exception.NotNullException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @org.junit.Test(expected = NullPointerException.class)
    public void createBookingTest() {
        bookingService.createBooking(null);
        bookingService.createBooking(new BookingEvent());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void cancelBookingTest() {
        bookingService.cancelBooking(30);
    }

    @Test
    public void getBookingsTest() {
        Collection<BookingDto> dto = bookingService.getBookings();
        assertEquals(dto.size(), 2);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void getBookingByLaundryRoomIdTest() {
        bookingService.getBookingByLaundryRoomId(3);
    }
}