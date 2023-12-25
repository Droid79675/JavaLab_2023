package ru.itis.cinemaservice.services;

import ru.itis.cinemaservice.dto.CinemaDto;

import java.util.List;

public interface CinemaService {

    List<CinemaDto> readListByCityName(String cityName);
}
