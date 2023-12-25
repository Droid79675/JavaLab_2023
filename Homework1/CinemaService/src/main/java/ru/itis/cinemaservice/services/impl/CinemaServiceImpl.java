package ru.itis.cinemaservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.itis.cinemaservice.dto.CinemaDto;
import ru.itis.cinemaservice.repositories.CinemaRepository;
import ru.itis.cinemaservice.services.CinemaService;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    CinemaRepository cinemaRepository;

    @Override
    public List<CinemaDto> readListByCityName(String cityName) {
        return CinemaDto.from(cinemaRepository.getAllByCityName(cityName));
    }
}
