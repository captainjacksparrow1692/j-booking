package uzumtech.j_booking.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uzumtech.j_booking.constant.enums.AccommodationType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HotelSearchRequest (
        @NotBlank
        String city,
        @NotNull
        LocalDate checkIn,
        LocalDate checkOut,
        @Min(1)
        Integer guestCount,
        AccommodationType type,
        BigDecimal maxBudget
){
}
