package uzumtech.j_booking.service;

import uzumtech.j_booking.dto.request.BookingCreateRequest;
import uzumtech.j_booking.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingCreateRequest dto);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getUserBookings(Long userId);
}
