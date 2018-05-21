package com.laundry.room.booking.repo;

import com.laundry.room.booking.domain.ApartmentRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRoomRepository extends JpaRepository<ApartmentRoom, Integer> {

    @Query("select a from ApartmentRoom a where a.number = :number")
    List<ApartmentRoom> findByRoomNumber(@Param("number") String number);

}
