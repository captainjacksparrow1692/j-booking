package uzumtech.j_booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.j_booking.constant.enums.BoardBasis;
import uzumtech.j_booking.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    //получить каталог
    List<Room> findByHotelId(Long hotelId);

    //поиск по вместимости
    Page<Room> findByHotelIdAndCapacityGreaterThanEqual(Long hotelId, Integer capacity, Pageable pageable);

    //поиск по типу питания
    List<Room> findByHotelIdAndBoardBasis(String hotelId, BoardBasis boardBasis);

    //поиск по удобствам
    List<Room> findByAmenitiesIn(List<String> amenities);
}
