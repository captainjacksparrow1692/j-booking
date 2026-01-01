package uzumtech.j_booking.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import uzumtech.j_booking.constant.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingResponse (
        Long bookingId,
        String hotelName,
        String roomType,
        BookingStatus status,
        BigDecimal totalAmount,
        @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
        LocalDateTime holdUntil,
        String cancellationPolicy
){
}
