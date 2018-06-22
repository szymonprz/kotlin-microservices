package pl.szymonprz.restaurant.application

import pl.szymonprz.restaurant.domain.model.Entity
import pl.szymonprz.restaurant.domain.model.Restaurant

interface RestaurantService {
    fun findByName(name: String): Collection<Restaurant>
    fun add(restaurant: Restaurant)
    fun update(restaurant: Restaurant)
    fun delete(id: String)
    fun findById(restaurantId: String): Entity<String>?
    fun findByCriteria(name : Map<String, ArrayList<String>>)
}
