package com.laundry.room.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "apartment_room")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApartmentRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String number;

    private String owner;

    public ApartmentRoom() {
    }

    public ApartmentRoom(Integer id, String number, String owner) {
        this.id = id;
        this.number = number;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

