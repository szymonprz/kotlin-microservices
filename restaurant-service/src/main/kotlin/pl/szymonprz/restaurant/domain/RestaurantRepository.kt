package pl.szymonprz.restaurant.domain

interface RestaurantRepository<TE, T> : Repository<TE, T> {

    fun containsName(name: T): Boolean

    fun findByName(name: T): Collection<TE>
}