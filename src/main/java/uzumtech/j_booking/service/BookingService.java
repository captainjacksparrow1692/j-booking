package uzumtech.j_booking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.j_booking.dto.request.BookingCreateRequest;
import uzumtech.j_booking.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingCreateRequest dto);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getUserBookings(Long userId);
    Page<BookingResponse> getUserBookings(Long userId, Pageable pageable);
}
