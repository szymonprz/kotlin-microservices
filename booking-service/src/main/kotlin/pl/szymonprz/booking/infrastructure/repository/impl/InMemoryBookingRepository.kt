package pl.szymonprz.booking.infrastructure.repository.impl

import org.springframework.stereotype.Repository
import pl.szymonprz.booking.domain.BookingRepository
import pl.szymonprz.booking.domain.model.Booking
import pl.szymonprz.booking.domain.model.Entity
import java.time.LocalDate
import java.time.LocalTime

@Repository("bookingRepository")
class InMemoryBookingRepository : BookingRepository {

    private val entities: MutableMap<String, Booking> = mutableMapOf()

    init {
        val booking = Booking("1", "1", "1", "1", LocalDate.now(), LocalTime.now(), "1")
        val booking2 = Booking("2", "2", "2", "2", LocalDate.now(), LocalTime.now(), "2")
        entities[booking.id] = booking
        entities[booking2.id] = booking2
    }

    override fun containsName(name: String): Boolean {
        return this.findByName(name).isNotEmpty()
    }

    override fun findByName(name: String): Collection<Booking> {
        var bookings: Collection<Booking> = arrayListOf()
        entities.forEach { _, entity ->
            if (entity.name.toLowerCase().contains(name)) {
                bookings = bookings.plus(entity)
            }
        }
        return bookings
    }

    override fun add(entity: Booking) {
        entities[entity.id] = entity
    }

    override fun remove(id: String) {
        entities.remove(id)
    }

    override fun update(entity: Booking) {
        entities.computeIfPresent(entity.id) { _, _ -> entity }
    }

    override fun contains(id: String): Boolean {
        return entities[id] != null
    }

    override fun get(id: String): Entity<String>? {
        return entities[id]
    }

    override fun getAll(): Collection<Booking> {
        return entities.values
    }
}