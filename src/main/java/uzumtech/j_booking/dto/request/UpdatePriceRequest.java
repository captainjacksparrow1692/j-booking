package uzumtech.j_booking.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdatePriceRequest(
        @NotNull
        Long roomId,

        @NotNull
        @FutureOrPresent
        LocalDate date,

        @NotNull
        @Positive
        BigDecimal newPrice
) {
}
