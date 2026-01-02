package uzumtech.j_booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uzumtech.j_booking.constant.Constant; // Твой класс с константами
import uzumtech.j_booking.dto.request.BookingCreateRequest;
import uzumtech.j_booking.dto.response.BookingResponse;
import uzumtech.j_booking.entity.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    // Используем константу из твоего класса Constant для статуса по умолчанию
    @Mapping(target = "status", constant = Constant.STATUS_PENDING)
    Booking toEntity(BookingCreateRequest dto);

    // Пример маппинга вложенных объектов (например, имя отеля из сущности Hotel)
    @Mapping(target = "hotelName", source = "hotel.name")
    @Mapping(target = "roomNumber", source = "room.number")
    BookingResponse toDto(Booking entity);
}