package ru.itis.infoclientservice.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaDto {

    private Long id;

    private String name;

    private String cityName;
}
