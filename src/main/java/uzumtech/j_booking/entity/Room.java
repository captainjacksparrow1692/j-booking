package uzumtech.j_booking.entity;

import jakarta.persistence.*;
import uzumtech.j_booking.constant.enums.Amenity;
import uzumtech.j_booking.constant.enums.BoardBasis;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel; // К какому отелю относится номер

    private String name; // Например: "Deluxe Double Room", "Standard Suite"

    private Integer capacity; // Максимальное число гостей (требование: "кол-во гостей")

    private Integer totalRooms; // Общее количество номеров этой категории в отеле

    @Enumerated(EnumType.STRING)
    private BoardBasis boardBasis; // Тип питания (RO, BB, HB, FB, AI)

    @ElementCollection(targetClass = Amenity.class)
    @CollectionTable(name = "room_amenities", joinColumns = @JoinColumn(name = "room_id"))
    @Enumerated(EnumType.STRING)
    private Set<Amenity> amenities; // Удобства именно в номере (кондиционер, ТВ, мини-бар)

    private BigDecimal basePrice; // Базовая цена (от которой строятся тарифы)
}
