package ru.itis.cinemaservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "Сообщение от сервера. Например ошибки и статусы")
public class StandardResponseDto {

    @Schema(description = "Текст сообщения")
    private String message;
}
