package uzumtech.j_booking.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import uzumtech.j_booking.configuration.props.RedisProps;
import uzumtech.j_booking.constant.Constant;

import java.time.Duration;
import java.util.Map;

@EnableCaching
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RedisConfiguration {

    RedisProps props;

    // Используем JSON сериализатор, чтобы данные в Redis были читаемыми (не бинарными)
    @Bean
    RedisSerializer<Object> redisJsonSerializer() {
        return RedisSerializer.json();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        var configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(props.getHost());
        configuration.setPort(props.getPort());
        configuration.setDatabase(props.getDatabase());

        // Устанавливаем пароль, если он есть в пропсах
        if (props.getPassword() != null && !props.getPassword().isBlank()) {
            configuration.setPassword(props.getPassword());
        }

        var lettuceClientConfiguration = LettuceClientConfiguration
                .builder()
                .commandTimeout(Duration.ofMillis(props.getTimeout()))
                .shutdownTimeout(Duration.ofMillis(props.getShutdownTimeout()))
                .build();

        return new LettuceConnectionFactory(configuration, lettuceClientConfiguration);
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory, RedisSerializer<Object> redisJsonSerializer) {
        // Базовая конфигурация кэша
        var defaultConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(props.getDefaultTtl())) // В Props обычно секунды
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(redisJsonSerializer));

        // Настройка индивидуального TTL для каждого типа данных из твоих констант
        Map<String, RedisCacheConfiguration> perCacheTtl = Map.of(
                // Бронирования живут недолго (10 мин по умолчанию в yml)
                Constant.BOOKING_CACHE, defaultConfiguration.entryTtl(Duration.ofSeconds(props.getBookingTtl())),

                // Отели живут долго (24 часа по умолчанию в yml)
                Constant.HOTEL_CACHE, defaultConfiguration.entryTtl(Duration.ofSeconds(props.getHotelTtl()))
        );

        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(defaultConfiguration)
                .withInitialCacheConfigurations(perCacheTtl)
                .transactionAware() // Кэш будет комититься только после успешной транзакции в БД
                .build();
    }
}