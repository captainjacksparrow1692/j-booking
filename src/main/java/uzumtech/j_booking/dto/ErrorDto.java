package uzumtech.j_booking.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorDto(
        int code,
        String message,
        String errorCode,
        LocalDateTime timestamp
) {
}
