package uzumtech.j_booking.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorDto(
        String message,
        String errorCode,
        LocalDateTime timestamp
) {
}
