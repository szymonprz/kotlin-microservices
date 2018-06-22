package pl.szymonprz.restaurant.api

import com.fasterxml.jackson.annotation.JsonProperty
import pl.szymonprz.NoArgConstructor

@NoArgConstructor
data class RestaurantDto(val id: String, val name: String, @JsonProperty(required = false) var tables: ArrayList<TableDto> = arrayListOf()) {

}
