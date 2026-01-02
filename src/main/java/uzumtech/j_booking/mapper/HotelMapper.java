package uzumtech.j_booking.mapper;

import org.mapstruct.Mapper;
import uzumtech.j_booking.dto.response.HotelCardResponse;
import uzumtech.j_booking.dto.response.HotelDetailResponse;
import uzumtech.j_booking.entity.Hotel;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    // Маппинг для легкой карточки
    HotelCardResponse toCardResponse(Hotel entity);

    // Маппинг для полной информации
    HotelDetailResponse toDetailResponse(Hotel entity);
}
