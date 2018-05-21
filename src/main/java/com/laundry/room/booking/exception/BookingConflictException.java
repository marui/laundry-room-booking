package com.laundry.room.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Booking timeslot is conflicted")
public class BookingConflictException extends RuntimeException {
}
