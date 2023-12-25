package ru.itis.cinemaservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import ru.itis.cinemaservice.models.Cinema;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Cinema", description = "Кинотеатр")
public class CinemaDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Наименование кинотеатра", example = "Kazan five stars", maxLength = 255)
    private String name;

    @Schema(description = "Город, где находится отель", example = "Казань", maxLength = 100)
    private String cityName;


    public static CinemaDto from(Cinema cinema) {
        return CinemaDto.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .cityName(cinema.getCityName())
                .build();
    }

    public static List<CinemaDto> from(List<Cinema> cinemaList) {
        return cinemaList.stream()
                .map(CinemaDto::from)
                .collect(Collectors.toList());
    }
}
