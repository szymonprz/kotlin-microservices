package pl.szymonprz.service.restaurant

import com.netflix.hystrix.HystrixCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
class RestaurantServiceApi(val restTemplate: RestTemplate, val client: DiscoveryClient) {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(RestaurantServiceApi::class.java)
    }

    @RequestMapping("/service")
    fun home(): List<String> {
        return client.services
    }

    @GetMapping("/restaurants/{restaurantId}")
    fun getRestaurant(@PathVariable("restaurantId") restaurantId: Int): ResponseEntity<Restaurant> {
        MDC.put("restaurandId", restaurantId.toString())
        val url = "http://restaurant-service/v1/restaurants/$restaurantId"
        LOG.debug("GetRestaurant from URL: {}", url)
        val result = restTemplate.getForEntity(url, Restaurant::class.java)
        LOG.info("GetRestaurant http-status: {}", result.statusCode)
        LOG.debug("GetRestaurant body: {}", result.body)
        return ResponseEntity(result.body, HttpStatus.OK)
    }

    @RequestMapping("/restaurants")
    fun findByName(@RequestParam("name") name: String): ResponseEntity<Collection<Restaurant>> {
        LOG.info(String.format("api-service findByName() invoked:{} for {} ", "v1/restaurants?name=", name))
        MDC.put("restaurantId", name)
        val hystrixCommand = RestaurantsHystrixCommand(restTemplate, name)
        val restaurants = hystrixCommand
                .execute()
        return if (restaurants != null) {
            ResponseEntity(restaurants, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    fun defaultRestaurant(@PathVariable restaurantId: Int): ResponseEntity<Restaurant> {
        return ResponseEntity(HttpStatus.BAD_GATEWAY)
    }

    fun defaultRestaurants(input: String): ResponseEntity<Collection<Restaurant>> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    class RestaurantsHystrixCommand(private val restTemplate: RestTemplate, private val restaurantName: String) : HystrixCommand<Collection<Restaurant>?>({ "restaurant-service" }) {
        override fun run(): Collection<Restaurant>? {
            val url = "http://restaurant-service/v1/restaurants?name=$restaurantName"
            LOG.debug("GetRestaurant from URL: {}", url)

            val typeRef = object : ParameterizedTypeReference<Collection<Restaurant>>() {

            }
            val result = restTemplate.exchange(url, HttpMethod.GET, null, typeRef)
            LOG.info("GetRestaurant http-status: {}", result.statusCode)
            LOG.debug("GetRestaurant body: {}", result.body)
            return result.body
        }

        override fun getFallback(): Collection<Restaurant>? {
            return emptyList()
        }
    }

}