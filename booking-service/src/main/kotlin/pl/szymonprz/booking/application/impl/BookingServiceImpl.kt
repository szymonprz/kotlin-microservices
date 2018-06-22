package pl.szymonprz.booking.application.impl

import org.springframework.stereotype.Service
import pl.szymonprz.booking.application.BaseService
import pl.szymonprz.booking.application.BookingService
import pl.szymonprz.booking.domain.BookingRepository
import pl.szymonprz.booking.domain.model.Booking
import pl.szymonprz.booking.domain.model.Entity

@Service("bookingService")
class BookingServiceImpl(private val bookingRepository: BookingRepository) : BaseService<Booking, String>(bookingRepository),  BookingService {

    override fun add(entity: Booking) {
        if(bookingRepository.containsName(entity.name)){
            throw RuntimeException(String.format("There is already entity with the name - %s", entity.name))
        }
        if(entity.name.isBlank()){
            throw RuntimeException("Booking name cannot be null or empty string.")
        }
        super.add(entity)
    }

    override fun update(entity: Booking) {
        bookingRepository.update(entity)
    }

    override fun delete(id: String) {
        bookingRepository.remove(id)
    }

    override fun findById(id: String): Entity<String>? {
        return bookingRepository.get(id)
    }

    override fun findByName(name: String): Collection<Booking> {
        return bookingRepository.findByName(name)
    }

    override fun findByCriteria(name: Map<String, ArrayList<String>>): Collection<Booking> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}