package ru.itis.infoclientservice.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoDto {

    private List<HotelDto> hotels;

    private List<CinemaDto> cinemas;

}
