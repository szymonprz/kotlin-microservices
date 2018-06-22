package pl.szymonprz.booking.application

import pl.szymonprz.booking.domain.model.Booking
import pl.szymonprz.booking.domain.model.Entity


interface BookingService {

    fun add(entity: Booking)

    fun update(entity: Booking)

    fun delete(id: String)

    fun findById(id: String): Entity<String>?

    fun findByName(name: String): Collection<Booking>

    fun findByCriteria(name: Map<String, ArrayList<String>>): Collection<Booking>
}