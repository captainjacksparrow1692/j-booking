package uzumtech.j_booking.entity;
import  uzumtech.j_booking.entity.Room;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "room_availability")
public class RoomAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate date;
    private Integer availableCount; // Сколько номеров такого типа свободно на эту дату
    private BigDecimal price; // Тариф на конкретный день (поддержка динамических цен)
}