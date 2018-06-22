package pl.szymonprz.restaurant.domain.model

import java.math.BigInteger

class Table(name: String, id: BigInteger, val capacity: Int) : BaseEntity<BigInteger>(name, id) {


    override fun toString(): String {
        return String.format("{id: %s, name: %s, capacity: %s", this.name, name, capacity)
    }
}
