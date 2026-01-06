package uzumtech.j_booking.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uzumtech.j_booking.dto.response.HotelCardResponse;
import uzumtech.j_booking.dto.response.HotelDetailResponse;
import uzumtech.j_booking.service.HotelService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotels")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelController {
    HotelService hotelService;
    //список всех отелей
    @GetMapping
    public ResponseEntity<Page<HotelCardResponse>> getAllHotels(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(hotelService.getAllHotelCards(pageable));
    }

    //инфа отеля
    @GetMapping("/{id}")
    public ResponseEntity<HotelDetailResponse> getHotelDetails(@PathVariable Long id){
        return ResponseEntity.ok(hotelService.getHotelDetails(id));
    }
}
