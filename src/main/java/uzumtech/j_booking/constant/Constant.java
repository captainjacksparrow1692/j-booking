package uzumtech.j_booking.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    //Ключи для кэширования в Redis
    public static final String HOTEL_CACHE = "hotels";
    public static final String ROOM_CACHE = "rooms";
    public static final String BOOKING_CACHE = "bookings";
    public static final String MERCHANT_CACHE = "merchants";

    //Названия топиков Kafka (согласно функционалу бронирования)
    // Уведомление о создании нового бронирования
    public static final String BOOKING_CREATED_TOPIC = "booking_created_topic";
    // Уведомление об изменении статуса оплаты
    public static final String PAYMENT_STATUS_TOPIC = "payment_status_topic";
    // Уведомление для мерчанта (отеля) о новом заказе
    public static final String MERCHANT_NOTIFICATION_TOPIC = "merchant_notification_topic";

    //Сообщения об ошибках
    public static final String BOOKING_NOT_FOUND = "Booking not found with id: ";
    public static final String HOTEL_NOT_FOUND = "Hotel not found with id: ";
    public static final String ROOM_NOT_AVAILABLE = "Room is not available for selected dates";
    public static final String MERCHANT_NOT_FOUND = "Merchant not found";

    //Статусы бронирования
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_CANCELLED = "CANCELLED";
}