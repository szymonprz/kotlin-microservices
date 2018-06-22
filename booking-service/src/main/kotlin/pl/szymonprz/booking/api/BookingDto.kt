package pl.szymonprz.booking.api

import pl.szymonprz.NoArgConstructor

@NoArgConstructor
data class BookingDto(val name: String, val id: String, var restaurantId: String, var userId: String,  var tableId: String) {

    override fun toString(): String {
        return "{id:'$id', name:'$name', restaurantId:'$restaurantId', userId:'$userId', tableId:'$tableId'}"
    }
}