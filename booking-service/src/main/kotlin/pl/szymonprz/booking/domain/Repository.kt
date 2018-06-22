package pl.szymonprz.booking.domain

interface Repository<TE, T> : ReadOnlyRepository<TE, T> {

    fun add(entity: TE)

    fun remove(id: T)

    fun update(entity: TE)
}