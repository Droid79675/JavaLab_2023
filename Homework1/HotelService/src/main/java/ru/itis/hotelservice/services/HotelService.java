package ru.itis.hotelservice.services;

import ru.itis.hotelservice.dto.HotelDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getListByCityName(String cityName);
}
