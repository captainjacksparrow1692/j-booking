package uzumtech.j_booking.constant.enums;

import lombok.Getter;

@Getter
public enum Error {

    // --- Системные и общие ошибки (10xxx) ---
    INTERNAL_SERVICE_ERROR_CODE(10001, "System not available"),
    EXTERNAL_SERVICE_FAILED_ERROR_CODE(10002, "External service not available"),
    HANDLER_NOT_FOUND_ERROR_CODE(10003, "Handler not found"),
    JSON_NOT_VALID_ERROR_CODE(10004, "Json not valid"),
    VALIDATION_ERROR_CODE(10005, "Validation error"),
    INVALID_REQUEST_PARAM_ERROR_CODE(10006, "Invalid request param"),
    INTERNAL_TIMEOUT_ERROR_CODE(10007, "Internal timeout"),
    METHOD_NOT_SUPPORTED_ERROR_CODE(10008, "Method not supported"),
    MISSING_REQUEST_HEADER_ERROR_CODE(10009, "Missing request header"),
    HTTP_SERVICE_ERROR_CODE(10010, "Service error code"),
    HTTP_CLIENT_ERROR_CODE(10011, "Client error code"),

    // --- Ошибки Отелей (20xxx) ---
    HOTEL_NOT_FOUND_ERROR_CODE(20001, "Hotel not found"),
    HOTEL_ALREADY_EXISTS_ERROR_CODE(20002, "Hotel already exists"),
    HOTEL_INACTIVE_ERROR_CODE(20003, "Hotel is temporarily inactive"),
    HOTEL_NO_AVAILABLE_ROOMS_ERROR_CODE(20004, "No rooms available in this hotel"),

    // --- Ошибки Бронирования (30xxx) ---
    BOOKING_NOT_FOUND_ERROR_CODE(30001, "Booking not found"),
    INVALID_BOOKING_DATES_ERROR_CODE(30002, "Check-in date must be before check-out date"),
    BOOKING_ALREADY_CANCELLED_ERROR_CODE(30003, "Booking has already been cancelled"),
    PAST_DATE_BOOKING_ERROR_CODE(30004, "Cannot book for past dates"),
    ROOM_ALREADY_BOOKED_ERROR_CODE(30005, "Room is already booked for selected dates"),

    // --- Ошибки Пользователей и Доступа (40xxx) ---
    USER_NOT_FOUND_ERROR_CODE(40001, "User not found"),
    UNAUTHORIZED_ERROR_CODE(40002, "User is not authorized"),
    FORBIDDEN_ERROR_CODE(40003, "Access denied"),

    // --- Ошибки Инфраструктуры (50xxx) ---
    KAFKA_SEND_FAILED_ERROR_CODE(50001, "Failed to send message to Kafka"),
    REDIS_CONNECTION_FAILED_ERROR_CODE(50002, "Cache service connection failed");

    final int code;
    final String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}