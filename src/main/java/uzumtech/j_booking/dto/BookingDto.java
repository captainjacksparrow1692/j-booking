package uzumtech.j_booking.dto;

import lombok.Builder;

@Builder
public record BookingDto(
        String key, String correlationId, String message
) {
}
