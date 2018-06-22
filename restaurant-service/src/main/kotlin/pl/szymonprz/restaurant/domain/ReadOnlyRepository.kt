package pl.szymonprz.restaurant.domain

import pl.szymonprz.restaurant.domain.model.Entity

interface ReadOnlyRepository<TE, T> {

    fun contains(id: T): Boolean

    fun get(id: T): Entity<T>?

    fun getAll(): Collection<TE>
}
