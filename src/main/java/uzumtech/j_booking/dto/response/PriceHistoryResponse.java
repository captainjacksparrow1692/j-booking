package uzumtech.j_booking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceHistoryResponse(
        Long roomId,
        BigDecimal oldPrice,
        BigDecimal newPrice,
        LocalDateTime changeDate,
        String changedBy
) {
}
