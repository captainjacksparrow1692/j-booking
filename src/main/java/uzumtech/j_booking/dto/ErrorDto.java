package uzumtech.j_booking.dto;

import lombok.Builder;
import uzumtech.j_booking.constant.enums.ErrorType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorDto(
        int code,
        String message,
        ErrorType type,
        LocalDateTime timestamp,
        List<String> validationErrors
) {
}
