package uzumtech.j_booking.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uzumtech.j_booking.constant.enums.BookingStatus;
import uzumtech.j_booking.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    //история брони пользователя
    Page<Booking> findByUserIdOrderByCreatedAtDesc (Long userId, Pageable pageable);

    //найти всех по айди
    Page<Booking> findAllByUserId(Long userId, Pageable pageable);

    //поиск брони по статусу
    Page<Booking> findByStatus(BookingStatus status, Pageable pageable);

    //поиск всех броней пользователя с одним статусом
    List<Booking> findByUserIdAndStatus(Long userId, BookingStatus status);

    //поиск просрочек
    @Query("SELECT b from Booking b where b.status = 'HELD' And b.holdUntil < :now")
    List<Booking> findExpiredHolds(@Param("now") LocalDateTime now);

    //защита от дубликата
    boolean existsByUserIdAndRoomIdAndStatusNot (Long userId, Long roomId, BookingStatus status);
}
