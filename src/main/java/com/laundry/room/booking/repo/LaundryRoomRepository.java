package com.laundry.room.booking.repo;

import com.laundry.room.booking.domain.LaundryRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaundryRoomRepository extends JpaRepository<LaundryRoom, Integer> {

}
