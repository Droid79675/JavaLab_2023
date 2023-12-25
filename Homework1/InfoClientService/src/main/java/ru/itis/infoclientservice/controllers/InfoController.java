package ru.itis.infoclientservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.infoclientservice.dto.CinemaDto;
import ru.itis.infoclientservice.dto.HotelDto;
import ru.itis.infoclientservice.dto.InfoDto;
import ru.itis.infoclientservice.services.HotelService;
import ru.itis.infoclientservice.services.CinemaService;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class InfoController {

    HotelService hotelsService;
    CinemaService cinemasService;


    @GetMapping("/info")
    public ResponseEntity<InfoDto> getInfo(@RequestParam("cityName") String cityName) {
        List<CinemaDto> cinemas = cinemasService.getCinema(cityName, "getcinemasfromrussia");
        System.out.println("56");
        List<HotelDto> hotels = hotelsService.getHotel(cityName, "gethotelsfromrussia");

        return ResponseEntity.ok(InfoDto.builder()
                .cinemas(cinemas)
                .hotels(hotels)
                .build());
    }
}
