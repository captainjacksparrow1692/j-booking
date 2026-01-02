package uzumtech.j_booking.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable; // ВАЖНО: именно этот импорт, не jakarta.persistence!
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uzumtech.j_booking.constant.Constant;
import uzumtech.j_booking.dto.response.HotelCardResponse;
import uzumtech.j_booking.dto.response.HotelDetailResponse;
import uzumtech.j_booking.mapper.HotelMapper;
import uzumtech.j_booking.repository.HotelRepository;
import uzumtech.j_booking.service.HotelService;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    @Cacheable(
            value = Constant.HOTEL_CACHE,
            key = "'cards_page_' + #pageable.pageNumber + '_size_' + #pageable.pageSize"
    )
    public Page<HotelCardResponse> getAllHotelCards(Pageable pageable) {
        log.info("Fetching hotel cards from DB");
        return hotelRepository.findAll(pageable)
                .map(hotelMapper::toCardResponse);
    }

    @Override
    @Cacheable(value = Constant.HOTEL_CACHE, key = "'detail_' + #id")
    public HotelDetailResponse getHotelDetails(Long id) {
        log.info("Fetching hotel details for id: {}", id);
        return hotelRepository.findById(id)
                .map(hotelMapper::toDetailResponse)
                .orElseThrow(() -> new RuntimeException(Constant.HOTEL_NOT_FOUND + id));
    }
}