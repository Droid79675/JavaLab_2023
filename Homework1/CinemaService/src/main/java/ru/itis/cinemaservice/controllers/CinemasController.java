package ru.itis.cinemaservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.cinemaservice.controllers.api.CinemasApi;
import ru.itis.cinemaservice.dto.CinemaDto;
import ru.itis.cinemaservice.services.CinemaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CinemasController implements CinemasApi {

    CinemaService cinemaService;

    @Override
    public ResponseEntity<List<CinemaDto>> getCinemas(String cityName) {
        return ResponseEntity.ok(cinemaService.readListByCityName(cityName));
    }
}
