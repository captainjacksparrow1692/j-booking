package uzumtech.j_booking.dto;

import lombok.Builder;

@Builder
public record BookingNotificationEvent(
        Long userId,
        String email,
        String message,
        String templateName
) {
}
