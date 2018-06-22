package pl.szymonprz.booking.domain.model

import java.time.LocalDate
import java.time.LocalTime

class Booking(name: String, id: String, var restaurantId: String, var userId: String, var date: LocalDate, var time: LocalTime, var tableId: String) : BaseEntity<String>(name, id) {

    override fun toString(): String {
        return "{id:'$id', name:'$name', restaurantId:'$restaurantId', userId:'$userId', date:$date, time:$time, tableId:'$tableId'}"
    }
}