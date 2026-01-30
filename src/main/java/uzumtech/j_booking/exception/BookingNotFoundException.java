package uzumtech.j_booking.exception;

import org.springframework.http.HttpStatus;
import uzumtech.j_booking.constant.enums.ErrorType;

import static uzumtech.j_booking.constant.enums.Error.BOOKING_NOT_FOUND_ERROR_CODE;

public class BookingNotFoundException extends BusinessException {
    public BookingNotFoundException(String message) {
        super(BOOKING_NOT_FOUND_ERROR_CODE.getCode(), message, ErrorType.INTERNAL, HttpStatus.CONFLICT);
    }
}
