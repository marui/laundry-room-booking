package com.laundry.room.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The entity is not found")
public class NonExistEntityException extends RuntimeException {
}



