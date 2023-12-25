package ru.itis.hotelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotelservice.models.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> getAllByCityName(String cityName);
}
