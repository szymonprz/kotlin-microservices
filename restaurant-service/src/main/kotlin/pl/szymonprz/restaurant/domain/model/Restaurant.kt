package pl.szymonprz.restaurant.domain.model

class Restaurant(name: String, id: String, val tables: List<Table> = arrayListOf()) : BaseEntity<String>(name, id) {

}
