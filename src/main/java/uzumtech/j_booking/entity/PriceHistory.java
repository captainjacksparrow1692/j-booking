package uzumtech.j_booking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private LocalDateTime changeDate;
    private String changeBy;
}