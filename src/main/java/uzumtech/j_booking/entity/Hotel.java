package uzumtech.j_booking.entity;

import jakarta.persistence.*;
import uzumtech.j_booking.constant.enums.AccommodationType;
import uzumtech.j_booking.constant.enums.Amenity;

import java.util.Set;

@Entity
@Table(name = "hotels", schema = "public")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;

    @Enumerated(EnumType.STRING)
    private AccommodationType type; // hotel, hostel, etc.

    private String brand;
    private Double rating;

    @ElementCollection(targetClass = Amenity.class)
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Enumerated(EnumType.STRING)
    private Set<Amenity> amenities;
}
