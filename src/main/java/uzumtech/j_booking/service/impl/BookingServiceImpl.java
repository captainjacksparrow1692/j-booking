package uzumtech.j_booking.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.j_booking.constant.Constant;
import uzumtech.j_booking.dto.request.BookingCreateRequest;
import uzumtech.j_booking.dto.response.BookingResponse;
import uzumtech.j_booking.entity.Booking;
import uzumtech.j_booking.mapper.BookingMapper;
import uzumtech.j_booking.repository.BookingRepository;
import uzumtech.j_booking.service.BookingService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Qualifier("bookingKafkaTemplate")
    private final KafkaTemplate<String, BookingResponse> kafkaTemplate;

    @Override
    @Transactional
    @CacheEvict(value = Constant.BOOKING_CACHE, allEntries = true)
    public BookingResponse createBooking(BookingCreateRequest request) {
        log.info("Creating booking for user ID: {}", request.userId());

        Booking entity = bookingMapper.toEntity(request);
        Booking saved = bookingRepository.save(entity);
        BookingResponse response = bookingMapper.toDto(saved);

        kafkaTemplate.send(Constant.BOOKING_CREATED_TOPIC, response);
        return response;
    }

    @Override
    @Cacheable(value = Constant.BOOKING_CACHE, key = "'detail_' + #id")
    public BookingResponse getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDto)
                .orElseThrow(() -> new RuntimeException(Constant.BOOKING_NOT_FOUND + id));
    }

    @Override
    public List<BookingResponse> getUserBookings(Long userId) {
        return List.of();
    }

    @Override
    // Теперь этот метод точно перекрывает (override) метод из интерфейса
    @Cacheable(
            value = Constant.BOOKING_CACHE,
            key = "'user_' + #userId + '_page_' + #pageable.pageNumber"
    )
    public Page<BookingResponse> getUserBookings(Long userId, Pageable pageable) {
        log.info("Fetching bookings for user: {}", userId);
        return bookingRepository.findAllByUserId(userId, pageable)
                .map(bookingMapper::toDto);
    }
}