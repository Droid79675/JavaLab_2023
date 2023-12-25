package ru.itis.hotelservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import ru.itis.hotelservice.models.Hotel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Hotel", description = "Отель")
public class HotelDto {

    @Schema(description = "id-шник", example = "1")
    private Long id;

    @Schema(description = "Наименование отеля", example = "Moscow hotel", maxLength = 255)
    private String name;

    @Schema(description = "Город, в котором отель находится", example = "Moscow", maxLength = 80)
    private String cityName;

    public static HotelDto from(Hotel hotel) {
        return HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .cityName(hotel.getCityName())
                .build();
    }

    public static List<HotelDto> from(List<Hotel> hotelList) {
        return hotelList.stream()
                .map(HotelDto::from)
                .collect(Collectors.toList());
    }
}
