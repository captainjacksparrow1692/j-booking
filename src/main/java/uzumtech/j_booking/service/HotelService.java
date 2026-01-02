package uzumtech.j_booking.service;

import uzumtech.j_booking.dto.response.HotelCardResponse;
import uzumtech.j_booking.dto.response.HotelDetailResponse;

import java.util.List;

public interface HotelService {
    //для главной страницы или поиска
    List<HotelCardResponse> getAllHotelCards();
    //для страницы конкретного отеля
    List<HotelDetailResponse>  getHotelDetails();
}
