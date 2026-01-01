package uzumtech.j_booking.entity;

import jakarta.persistence.*;
import uzumtech.j_booking.constant.enums.BookingStatus;
import uzumtech.j_booking.constant.enums.CancellationPolicyType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    private Long userId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer guestCount;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // pending, ect.

    @Enumerated(EnumType.STRING)
    private CancellationPolicyType cancellationPolicy;
    private LocalDateTime holdUntil; //время до которого нужно оплатить

    @Column(name = "created_at")
    private LocalDateTime createdAt =  LocalDateTime.now();
}
