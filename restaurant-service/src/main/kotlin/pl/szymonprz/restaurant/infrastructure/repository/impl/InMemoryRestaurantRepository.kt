package pl.szymonprz.restaurant.infrastructure.repository.impl

import org.springframework.stereotype.Repository
import pl.szymonprz.restaurant.domain.RestaurantRepository
import pl.szymonprz.restaurant.domain.model.Entity
import pl.szymonprz.restaurant.domain.model.Restaurant

@Repository("restaurantRepository")
class InMemoryRestaurantRepository: RestaurantRepository<Restaurant, String> {

    private val entities: MutableMap<String, Restaurant> = mutableMapOf()

    init {
        val restaurant = Restaurant("Big-O Restaurant", "1")
        val restaurant2 = Restaurant("O Restaurant", "2")
        entities["1"] = restaurant
        entities["2"] = restaurant2
    }
    override fun containsName(name: String): Boolean {
        return this.findByName(name).isNotEmpty()
    }

    override fun findByName(name: String): Collection<Restaurant> {
        var restaurants: Collection<Restaurant> = arrayListOf()
        entities.forEach { _, entity ->
            if(entity.name.toLowerCase().contains(name)){
                restaurants = restaurants.plus(entity)
            }
        }
        return restaurants
    }

    override fun add(entity: Restaurant) {
        entities[entity.id] = entity
    }

    override fun remove(id: String) {
        entities.remove(id)
    }

    override fun update(entity: Restaurant) {
        entities.computeIfPresent(entity.id ){ _, _ -> entity}
    }

    override fun contains(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: String): Entity<String>? {
        return entities[id]
    }


    override fun getAll(): Collection<Restaurant> {
        return entities.values
    }
}