package pl.szymonprz.restaurant.application.impl

import org.springframework.stereotype.Service
import pl.szymonprz.restaurant.application.RestaurantService
import pl.szymonprz.restaurant.domain.RestaurantRepository
import pl.szymonprz.restaurant.domain.model.Entity
import pl.szymonprz.restaurant.domain.model.Restaurant

@Service
class RestaurantServiceImpl(val restaurantRepository: RestaurantRepository<Restaurant, String>) : RestaurantService {

    override fun add(restaurant: Restaurant) {
        if(restaurant.name.isBlank()){
            throw RuntimeException("Restaurant name cannot be null or empty string.")
        }
        if(restaurantRepository.containsName(restaurant.name)){
            throw RuntimeException(String.format("There is already a product with the name - %s", restaurant.name))
        }
        restaurantRepository.add(restaurant)
    }

    override fun update(restaurant: Restaurant) {
        restaurantRepository.update(restaurant)
    }

    override fun delete(id: String) {
        restaurantRepository.remove(id)
    }

    override fun findById(restaurantId: String): Entity<String>? {
        return restaurantRepository.get(restaurantId)
    }

    override fun findByCriteria(name: Map<String, ArrayList<String>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByName(name: String): Collection<Restaurant> {
        return restaurantRepository.findByName(name)
    }
}