package uzumtech.j_booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uzumtech.j_booking.constant.enums.AccommodationType;
import uzumtech.j_booking.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    //поиск по городу
    Page<Hotel> findByHotelId(Long hotelId, Pageable pageable);

    //поиск по типу жилья
    Page<Hotel> findByType(AccommodationType type, Pageable pageable);

    @Query("SELECT h from Hotel h where h.city = :city AND h.type = :type")
    Page<Hotel> findAdvanced(
            @Param("city") String city,
            @Param("type") AccommodationType type,
            Pageable pageable
    );
}
