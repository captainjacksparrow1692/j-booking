package uzumtech.j_booking.configuration.props;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfig {

    String host;
    int port;
    int database;
    String username;
    String password;

    // Тайм-ауты соединения
    int timeout;
    int shutdownTimeout;

    // Настройки времени жизни кэша (в секундах)
    int defaultTtl;  // Для общих данных
    int bookingTtl;  // Для бронирований (обычно короткий)
    int hotelTtl;    // Для списка отелей (обычно длинный)
}