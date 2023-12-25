package ru.itis.cinemaservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.cinemaservice.models.Cinema;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    List<Cinema> getAllByCityName(String cityName);
}
