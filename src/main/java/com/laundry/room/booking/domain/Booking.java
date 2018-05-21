package com.laundry.room.booking.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="time_from")
	private LocalDateTime from;

	@Column(name="time_to")
	private LocalDateTime to;

	@ManyToOne
	@JoinColumn(name = "apartment_room", referencedColumnName = "id")
	@JsonBackReference
	private ApartmentRoom apartmentRoom;

    @ManyToOne
    @JoinColumn(name = "laundry_room", referencedColumnName = "id")
    @JsonBackReference
    private LaundryRoom laundryRoom;
	
	public Booking() {
	}
	
	public Booking(LaundryRoom laundryRoom, ApartmentRoom apartmentRoom, LocalDateTime from, LocalDateTime to) {
	    this.laundryRoom = laundryRoom;
		this.apartmentRoom = apartmentRoom;
		this.from = from;
		this.to = to;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

    public LaundryRoom getLaundryRoom() {
        return laundryRoom;
    }

    public void setLaundryRoom(LaundryRoom laundryRoom) {
        this.laundryRoom = laundryRoom;
    }

    public ApartmentRoom getApartmentRoom() {
        return apartmentRoom;
    }

    public void setApartmentRoom(ApartmentRoom apartmentRoom) {
        this.apartmentRoom = apartmentRoom;
    }
}
