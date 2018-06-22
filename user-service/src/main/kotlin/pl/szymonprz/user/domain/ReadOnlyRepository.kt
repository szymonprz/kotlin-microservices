package pl.szymonprz.user.domain

import pl.szymonprz.user.domain.model.Entity

interface ReadOnlyRepository<TE, T> {

    fun contains(id: T): Boolean

    fun get(id: T): Entity<T>?

    fun getAll(): Collection<TE>
}
