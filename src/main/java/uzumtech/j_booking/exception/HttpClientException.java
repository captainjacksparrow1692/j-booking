package uzumtech.j_booking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import uzumtech.j_booking.constant.enums.ErrorType;

import static uzumtech.j_booking.constant.enums.Error.HTTP_CLIENT_ERROR_CODE;

@Getter
public class HttpClientException extends BusinessException{
    public HttpClientException(String message, HttpStatusCode status) {
        super(HTTP_CLIENT_ERROR_CODE.getCode(), message, ErrorType.EXTERNAL, HttpStatus.valueOf(status.value()));
    }
}
