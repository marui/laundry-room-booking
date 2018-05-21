package com.laundry.room.booking.controller;

import com.laundry.room.booking.dto.BookingDto;
import com.laundry.room.booking.event.BookingEvent;
import com.laundry.room.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value="/api/v1/booking")
public class BookingController{

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value="", method=RequestMethod.POST)
    @ResponseBody
    public BookingDto create(@RequestBody BookingEvent data){
        return bookingService.createBooking(data);
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    @ResponseBody
    public Collection<BookingDto> getBookings(@RequestParam(value="laundry-room-id", required=false) Integer laundryRoomId){
        if(laundryRoomId != null) {
            return bookingService.getBookingByLaundryRoomId(laundryRoomId);
        }
        return bookingService.getBookings();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> cancelBooking(@PathVariable("id") Integer id){
        bookingService.cancelBooking(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
