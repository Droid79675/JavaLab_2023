package ru.itis.cinemaservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.cinemaservice.dto.StandardResponseDto;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<StandardResponseDto> handleRestException(RestException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(StandardResponseDto.builder()
                        .message(e.getMessage())
                        .build());
    }
}
