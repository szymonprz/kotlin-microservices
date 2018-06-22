package pl.szymonprz.user.application

import pl.szymonprz.user.domain.model.Entity
import pl.szymonprz.user.domain.model.User


interface UserService {

    fun add(entity: User)

    fun update(entity: User)

    fun delete(id: String)

    fun findById(id: String): Entity<String>?

    fun findByName(name: String): Collection<User>

    fun findByCriteria(name: Map<String, ArrayList<String>>): Collection<User>
}