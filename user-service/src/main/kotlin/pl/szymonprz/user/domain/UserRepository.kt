package pl.szymonprz.user.domain

import pl.szymonprz.user.domain.model.User


interface UserRepository : Repository<User, String> {

    fun containsName(name: String): Boolean

    fun findByName(name: String): Collection<User>
}