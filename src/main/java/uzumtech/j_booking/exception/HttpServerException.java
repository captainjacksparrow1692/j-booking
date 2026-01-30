package uzumtech.j_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import uzumtech.j_booking.constant.enums.ErrorType;

import static uzumtech.j_booking.constant.enums.Error.HTTP_SERVICE_ERROR_CODE;

@Getter
public class HttpServerException extends BusinessException {
    public HttpServerException(String message, HttpStatusCode status) {
        super(HTTP_SERVICE_ERROR_CODE.getCode(), message, ErrorType.EXTERNAL, HttpStatus.valueOf(status.value()));
    }
}
