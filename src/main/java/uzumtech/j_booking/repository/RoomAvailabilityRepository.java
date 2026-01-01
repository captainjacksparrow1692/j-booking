package uzumtech.j_booking.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uzumtech.j_booking.entity.RoomAvailability;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Long> {
    //поиск доступности для конкретного номера на диапазон дат
    @Query("SELECT ra FROM RoomAvailability ra where ra.room.id = :roomId AND ra.date BETWEEN :startDate AND :endDate ORDER BY ra.date ASC")
    List<RoomAvailability> findAllByRoomIdAndDateRange(
            @Param("roomId") Long roomId,
            @Param("startDate")LocalDate startDate,
            @Param("endDate")LocalDate endDate
            );

    //атомарное уменьшение количества доступных номеров(процесс HOLD)
    @Modifying
    @Query("UPDATE RoomAvailability ra SET ra.availableCount = ra.availableCount - 1 WHERE ra.room.id = :roomId AND ra.date BETWEEN :startDate AND :endDate AND ra.availableCount > 0")
    int decreaseAvailbility(
            @Param("roomId") Long roomId,
            @Param("startDate")LocalDate startDate,
            @Param("endDate")LocalDate endDate
    );

    //атомарное увеличение количества доступных номеров(CANCEL, Expired HOLD)
    @Modifying
    @Query("UPDATE RoomAvailability ra SET ra.availableCount = ra.availableCount + 1 WHERE ra.room.id = :roomId AND ra.date BETWEEN :startDate AND :endDate")
    void increaseAvailability(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    //поиск минимально цены для отеля
    @Query("SELECT MIN(ra.price) FROM RoomAvailability ra WHERE ra.room.hotel.id = :hotelId AND ra.date = :targetDate AND ra.availableCount > 0")
    Optional<java.math.BigDecimal> findMinPriceByHotelAndDate(
            @Param("hotelId") Long hotelId,
            @Param("targetDate") LocalDate targetDate
    );
}
