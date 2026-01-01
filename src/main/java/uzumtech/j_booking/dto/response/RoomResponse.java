package uzumtech.j_booking.dto.response;

import uzumtech.j_booking.constant.enums.Amenity;
import uzumtech.j_booking.constant.enums.BoardBasis;

import java.math.BigDecimal;
import java.util.Set;

public record RoomResponse (
        Long id,
        String name,
        Integer capacity,
        BoardBasis boardBasis,
        BigDecimal pricePerNight,
        Set<Amenity> amenities,
        boolean isAvailable
){
}
