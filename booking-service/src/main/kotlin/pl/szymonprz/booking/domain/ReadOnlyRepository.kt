package pl.szymonprz.booking.domain

import pl.szymonprz.booking.domain.model.Entity

interface ReadOnlyRepository<TE, T> {

    fun contains(id: T): Boolean

    fun get(id: T): Entity<T>?

    fun getAll(): Collection<TE>
}
