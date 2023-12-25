package ru.itis.hotelservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.itis.hotelservice.dto.HotelDto;
import ru.itis.hotelservice.repositories.HotelRepository;
import ru.itis.hotelservice.services.HotelService;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    HotelRepository hotelRepository;

    @Override
    public List<HotelDto> getListByCityName(String cityName) {
        return HotelDto.from(hotelRepository.getAllByCityName(cityName));
    }
}
