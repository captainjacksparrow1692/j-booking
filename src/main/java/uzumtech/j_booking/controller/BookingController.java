package uzumtech.j_booking.controller;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzumtech.j_booking.dto.request.BookingCreateRequest;
import uzumtech.j_booking.dto.response.BookingResponse;
import uzumtech.j_booking.dto.response.HotelDetailResponse;
import uzumtech.j_booking.service.BookingService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingController {
    BookingService bookingService;

    //создание брони
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid
            @RequestBody
            BookingCreateRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(request));
    }

    //история бронирования конкретного пользователя
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<BookingResponse>> getUserBookings(
            @PathVariable
            Long userId, Pageable pageable
    ){
        return ResponseEntity.ok(bookingService.getUserBookings(userId, pageable));
    }

    //просмотр брони по айди
    public ResponseEntity<BookingResponse> getBookingById(
            @PathVariable
            Long id
    ){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
}
