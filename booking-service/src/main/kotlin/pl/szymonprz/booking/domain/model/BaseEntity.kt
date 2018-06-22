package pl.szymonprz.booking.domain.model

abstract class BaseEntity<T>(name: String, id: T) : Entity<T>(name, id) {

    private var isModified: Boolean = false

}