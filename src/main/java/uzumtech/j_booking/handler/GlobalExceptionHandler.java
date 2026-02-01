package uzumtech.j_booking.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import uzumtech.j_booking.constant.enums.ErrorType;
import uzumtech.j_booking.dto.ErrorDto;
import uzumtech.j_booking.exception.BusinessException;

import static uzumtech.j_booking.constant.enums.Error.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //обработка кастомных ошибок
    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorDto> handleApplicationException(BusinessException e) {
        log.error("Error: {}", e.getMessage(), e);

        var errorBody = ErrorDto
                .builder()
                .code(e.getCode())
                .type(e.getErrorType())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatus()).body(errorBody);
    }

    //валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation error: {}", e.getMessage(), e);

        var validationErrors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    }else{
                        return error.getDefaultMessage();
                    }
                })
                .toList();

        var error = ErrorDto
                .builder()
                .type(ErrorType.VALIDATION)
                .code(VALIDATION_ERROR_CODE.getCode())
                .message(VALIDATION_ERROR_CODE.getMessage())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    //URL ошибки
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException : {}", e.getMessage(), e);

        var error = ErrorDto
                .builder()
                .type(ErrorType.INTERNAL)
                .code(INVALID_REQUEST_PARAM_ERROR_CODE.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    //JSON ошибки
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException : {}", e.getMessage(), e);

        var error = ErrorDto
                .builder()
                .type(ErrorType.INTERNAL)
                .code(JSON_NOT_VALID_ERROR_CODE.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException : {}", e.getMessage(), e);

        var error = ErrorDto
                .builder()
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    //timeout
    @ExceptionHandler(ResourceAccessException.class)
    private ResponseEntity<ErrorDto> handleResourceAccessException(ResourceAccessException e) {
        log.error("ResourceAccessException : {}", e.getMessage(), e);

        var error = ErrorDto
                .builder()
                .type(ErrorType.INTERNAL)
                .code(INTERNAL_TIMEOUT_ERROR_CODE.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(error);
    }

    //общий обработчик
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorDto> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);

        var error = ErrorDto
                .builder()
                .type(ErrorType.INTERNAL)
                .code(INTERNAL_SERVICE_ERROR_CODE.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
