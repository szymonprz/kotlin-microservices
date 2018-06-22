package pl.szymonprz.booking.domain

import pl.szymonprz.booking.domain.model.Booking

interface BookingRepository: Repository<Booking, String> {

    fun containsName(name: String): Boolean

    fun findByName(name: String): Collection<Booking>
}