package uzumtech.j_booking.dto.response;

import uzumtech.j_booking.constant.enums.AccommodationType;
import uzumtech.j_booking.constant.enums.Amenity;
import uzumtech.j_booking.dto.response.RoomResponse;

import java.util.List;
import java.util.Set;

public record HotelDetailResponse (
        Long id,
        String name,
        String description,
        String city,
        String address,
        Double rating,
        AccommodationType type,
        List<RoomResponse> rooms,
        Set<Amenity> amenities
){
}
