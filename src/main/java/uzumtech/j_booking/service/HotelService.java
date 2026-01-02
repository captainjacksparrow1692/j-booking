package uzumtech.j_booking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.j_booking.dto.response.HotelCardResponse;
import uzumtech.j_booking.dto.response.HotelDetailResponse;

public interface HotelService {
    // Убедись, что тут Page, а не List
    Page<HotelCardResponse> getAllHotelCards(Pageable pageable);

    HotelDetailResponse getHotelDetails(Long id);
}