package ru.itis.infoclientservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.infoclientservice.dto.CinemaDto;

import java.util.List;

@FeignClient(name = "cinemas-service", url = "${feign.cinemas-service.url}")
public interface CinemaService {

    @GetMapping(value = "/api/cinemas", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CinemaDto> getCinema(
            @RequestParam("cityName") String cityName,
            @RequestParam("api-key") String apiKey);
}
