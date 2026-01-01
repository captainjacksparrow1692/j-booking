package uzumtech.j_booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.j_booking.entity.PriceHistory;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    //получение изменений цен определенного номера
    Page<PriceHistory> findByRoomIdOrderByChangeDateDesc(Long roomId, Pageable pageable);

    //найти все изменение введенные определенным сотрудником
    Page<PriceHistory> findByChangedBy(String changedBy, Pageable pageable);
}
