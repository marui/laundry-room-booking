package com.laundry.room.booking.service;

import com.laundry.room.booking.domain.ApartmentRoom;
import com.laundry.room.booking.domain.Booking;
import com.laundry.room.booking.domain.LaundryRoom;
import com.laundry.room.booking.dto.BookingDto;
import com.laundry.room.booking.event.BookingEvent;
import com.laundry.room.booking.event.BookingTimeslot;
import com.laundry.room.booking.exception.BookingConflictException;
import com.laundry.room.booking.exception.NonExistEntityException;
import com.laundry.room.booking.exception.NotNullException;
import com.laundry.room.booking.repo.ApartmentRoomRepository;
import com.laundry.room.booking.repo.BookingRepository;
import com.laundry.room.booking.repo.LaundryRoomRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private LaundryRoomRepository laundryRoomRepo;

    @Autowired
    private ApartmentRoomRepository apartmentRoomRepo;

    private static final Logger LOGGER = Logger.getLogger(BookingService.class.getName());

    public BookingService() {

    }

    public BookingDto createBooking(BookingEvent data) {

        if(data == null){
            LOGGER.log(Level.FINE, "Input data is empty");
            throw new NotNullException();
        }

        if(data.getLaundryRoomId() == null){
            LOGGER.log(Level.FINER, "Laundry room id is empty");
            throw new NotNullException();
        }

        if(data.getApartmentNumber() == null){
            LOGGER.log(Level.FINER, "Appartment number is empty");
            throw new NotNullException();
        }

        if(data.getTimeslot() == null){
            LOGGER.log(Level.FINER, "Time slot is empty");
            throw new NotNullException();
        }

        if(data.getTimeslot().getTo() == null){
            LOGGER.log(Level.FINER, "Time to is empty");
            throw new NotNullException();
        }

        if(data.getTimeslot().getFrom() == null){
            LOGGER.log(Level.FINER, "Time from is empty");
            throw new NotNullException();
        }

        BookingTimeslot timeslot = data.getTimeslot();
        LocalDateTime from = timeslot.getFrom();
        LocalDateTime to = timeslot.getTo();
        timeslot.varifyDignity(from, to);

        Booking booking = new Booking();

        String apartment_no = data.getApartmentNumber();
        try {
            ApartmentRoom apartmentRoom = apartmentRoomRepo.findByRoomNumber(apartment_no).iterator().next();
            booking.setApartmentRoom(apartmentRoom);
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.FINER, "Apartment room does not exist");
            throw new NonExistEntityException();
        }

        Integer laundry_room_id = data.getLaundryRoomId();
        try {
            LaundryRoom laundryRoom = laundryRoomRepo.getOne(laundry_room_id);
            booking.setLaundryRoom(laundryRoom);

            List<Booking> bookings = bookingRepo.findByLaundryRoom(laundryRoom);
            bookings = bookings.stream().filter(r -> !(from.isAfter(r.getTo()) || to.isBefore(r.getFrom()))).collect(Collectors.toList());
            if (bookings != null && !bookings.isEmpty()) {
                LOGGER.log(Level.INFO, "Time slot conflicts");
                throw new BookingConflictException();
            }

            booking.setFrom(from);
            booking.setTo(to);
        } catch (EntityNotFoundException e) {
            LOGGER.log(Level.FINER, "Laundry room does not exist");
            throw new NonExistEntityException();
        }
        try {
            bookingRepo.save(booking);
            return domainDtoMapper(Collections.singletonList(booking)).iterator().next();
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.FINE, "SQL error when save booking");
            throw new NonExistEntityException();
        }
    }

    public void cancelBooking(Integer id) {
        try {
            Booking booking = bookingRepo.getOne(id);
            bookingRepo.delete(booking);
        } catch (EntityNotFoundException e) {
            LOGGER.log(Level.FINER, "Booking does not exist");
            throw new NonExistEntityException();
        }
    }

    public Collection<BookingDto> getBookings() {
        return domainDtoMapper(bookingRepo.findAll());
    }

    public Collection<BookingDto> getBookingByLaundryRoomId(Integer laundryRoomId) {
        try {
            LaundryRoom laundryRoom = laundryRoomRepo.getOne(laundryRoomId);
            return domainDtoMapper(bookingRepo.findByLaundryRoom(laundryRoom));
        } catch (EntityNotFoundException e) {
            LOGGER.log(Level.FINER, "Laundry room does not exist");
            throw new NonExistEntityException();
        }
    }

    private Collection<BookingDto> domainDtoMapper(Collection<Booking> bookings) {
        return bookings.stream()
                .map(b -> new BookingDto(
                        b.getId(),
                        b.getLaundryRoom().getId(),
                        b.getApartmentRoom().getNumber(),
                        b.getFrom(),
                        b.getTo()
                ))
                .collect(Collectors.toList());
    }
}
