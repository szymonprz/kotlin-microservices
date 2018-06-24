package pl.szymonprz.booking.api

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.szymonprz.booking.application.BookingService
import pl.szymonprz.booking.domain.model.Booking
import pl.szymonprz.booking.domain.model.Entity
import java.time.LocalDate
import java.time.LocalTime


@RestController
@RequestMapping("/v1/booking")
class BookingController(protected var bookingService: BookingService) {

    /**
     * Fetch bookings with the specified name. A partial case-insensitive match
     * is supported. So `http://.../booking/rest` will find any
     * bookings with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of bookings.
     */
    @GetMapping
    fun findByName(@RequestParam("name") name: String): ResponseEntity<Collection<Booking>> {
        LOG.info("booking-service findByName() invoked:{} for {} ", bookingService.javaClass.name, name)
        return try {
            val bookings = bookingService.findByName(name.toLowerCase())
            if (bookings.isNotEmpty())
                ResponseEntity(bookings, HttpStatus.OK)
            else
                ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (ex: Exception) {
            LOG.warn("Exception raised findByName REST Call", ex)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    /**
     * Fetch bookings with the given id.
     * `http://.../v1/bookings/{id}` will return booking with given
     * id.
     *
     * @param id
     * @return A non-null, non-empty collection of bookings.
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Entity<String>> {
        LOG.info("booking-service findById() invoked:{} for {} ", bookingService.javaClass.name, id)
        return try {
            val booking = bookingService.findById(id)
            if (booking != null)
                ResponseEntity(booking, HttpStatus.OK)
            else
                ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (ex: Exception) {
            LOG.warn("Exception raised findById REST Call {0}", ex)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }


    }

    /**
     * Add booking with the specified information.
     *
     * @param bookingDto
     * @return A non-null booking.
     */
    @PostMapping
    fun add(@RequestBody bookingDto: BookingDto): ResponseEntity<Booking> {
        LOG.info("booking-service add() invoked: %s for %s", bookingService.javaClass.name, bookingDto.name)
        val booking = Booking("", "", "", "", LocalDate.now(), LocalTime.now(), "")
        BeanUtils.copyProperties(bookingDto, booking)
        try {
            bookingService.add(booking)
        } catch (ex: Exception) {
            LOG.warn("Exception raised add Booking REST Call {0}", ex)
            return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        }

        return ResponseEntity(HttpStatus.CREATED)
    }

    companion object {
        protected val LOG: Logger = LogManager.getLogger(BookingController::class.java.name)
    }
}