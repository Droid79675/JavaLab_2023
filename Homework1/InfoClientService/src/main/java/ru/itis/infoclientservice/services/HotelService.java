package ru.itis.infoclientservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.infoclientservice.dto.HotelDto;

import java.util.List;

@FeignClient(name = "hotels-service", url = "${feign.hotels-service.url}")
public interface HotelService {

    @GetMapping(value = "/api/hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<HotelDto> getHotel(
            @RequestParam("cityName") String cityName,
            @RequestParam("api-key") String apiKey);

}
