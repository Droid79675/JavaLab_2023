package ru.itis.cinemaservice.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.cinemaservice.dto.CinemaDto;

import java.util.List;

@Tags(value =
@Tag(name = "Cinemas"))
@RequestMapping("/api/cinemas")
public interface CinemasApi {

    @Operation(summary = "Получение списка кинотеатров по имени города.", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
                    })
    })
    @GetMapping
    ResponseEntity<List<CinemaDto>> getCinemas(
            @Parameter(description = "Имя города", example = "Moscow") @RequestParam("cityName") String cityName);


}
