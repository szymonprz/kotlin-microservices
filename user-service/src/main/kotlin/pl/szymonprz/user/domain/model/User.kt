package pl.szymonprz.user.domain.model

class User(name: String, id: String, var address: String, var city: String, var phoneNo: String) : BaseEntity<String>(name, id) {

    override fun toString(): String {
        return String.format("{id: %s, name: %s, address: %s, city: %s, phoneNo: %s}",
                id, name, address, city, phoneNo)
    }
}