package pl.szymonprz.restaurant.api

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.szymonprz.restaurant.application.RestaurantService
import pl.szymonprz.restaurant.domain.model.Entity
import pl.szymonprz.restaurant.domain.model.Restaurant

@RestController
@RequestMapping("/v1/restaurants")
class RestaurantController(protected val restaurantService: RestaurantService) {

    companion object {
        val LOG: Logger = LogManager.getLogger(RestaurantController::class.java)
    }

    @GetMapping()
    fun findByName(@RequestParam("name") name: String): ResponseEntity<Collection<Restaurant>> {
        LOG.info("Restaurant-service findByName invoked for {}", name)
        val nameLowercase = name.trim().toLowerCase()
        try {
            val restaurants: Collection<Restaurant> = restaurantService.findByName(nameLowercase)
            if (restaurants.isNotEmpty()) {
                return ResponseEntity(restaurants, HttpStatus.OK)
            }
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            LOG.warn("Exception raised findByName REST Call", e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{restaurant_id}")
    fun findById(@PathVariable("restaurant_id") restaurantId: String): ResponseEntity<Entity<String>> {
        LOG.info("Restaurant-service findById invoked for {}", restaurantId)
        try {
            val restaurant = restaurantService.findById(restaurantId)
            if (restaurant != null) {
                return ResponseEntity(restaurant, HttpStatus.OK)
            }
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            LOG.warn("Exception raised findById REST Call", e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun add(@RequestBody restaurantDto: RestaurantDto): ResponseEntity<Restaurant> {
        LOG.info("Restaurant-service add invoked for {}", restaurantDto.name)
        val restaurant = Restaurant("", "")
        try {
            BeanUtils.copyProperties(restaurantDto, restaurant)
        } catch (e: Exception) {
            LOG.warn("Exception raised add REST Call", e)
            return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        }

        return try {
            restaurantService.add(restaurant)
            ResponseEntity(HttpStatus.CREATED)
        } catch (e: Exception) {
            LOG.warn("Exception raised add REST Call", e)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
}