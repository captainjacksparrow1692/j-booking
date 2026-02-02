package uzumtech.j_booking.configuration.kafka;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer; // ПРАВИЛЬНЫЙ ИМПОРТ
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import uzumtech.j_booking.configuration.props.KafkaProps;
import uzumtech.j_booking.dto.response.BookingResponse;
import uzumtech.j_booking.dto.DlqDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProducerConfiguration {

    KafkaProps kafkaProps;

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        // Генерация уникального Client ID для отслеживания в логах Kafka
        props.put(ProducerConfig.CLIENT_ID_CONFIG, (kafkaProps.getClientId() + "-" + UUID.randomUUID()));
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapServers());
        props.put(ProducerConfig.ACKS_CONFIG, kafkaProps.getAcksConfig());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaProps.getRetriesConfig());

        // Включаем идемпотентность (защита от дубликатов при переотправке)
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        // Настройки оптимизации пачек (Batching)
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProps.getBatchSizeConfig());
        props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProps.getLingerMsConfig());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProps.getBufferMemoryConfig());

        // Сериализаторы Kafka
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

        return props;
    }

    // Бин для отправки уведомлений о бронировании
    @Bean("bookingKafkaTemplate")
    public KafkaTemplate<String, BookingResponse> bookingTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }

    // Бин для отправки "плохих" сообщений в очередь переработки (DLQ)
    @Bean("dlqKafkaTemplate")
    public KafkaTemplate<String, DlqDto> dlqTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }
}