package uzumtech.j_booking.dto.response;

import uzumtech.j_booking.constant.enums.Amenity;

import java.math.BigDecimal;
import java.util.Set;

public record HotelCardResponse(
        Long Id,
        String name,
        String city,
        Double rating,
        String brand,
        BigDecimal minPricePerNight,
        Set<Amenity> amenities
) {
}
