package pl.szymonprz.user.infrastructure.repository.impl

import org.springframework.stereotype.Repository
import pl.szymonprz.user.domain.UserRepository
import pl.szymonprz.user.domain.model.Entity
import pl.szymonprz.user.domain.model.User

@Repository("userRepository")
class InMemoryUserRepository : UserRepository{

    private val entities: MutableMap<String, User> = mutableMapOf()

    init {
        val user = User("1", "1", "1", "1", "1")
        val user2 = User("2", "2", "2", "2",  "2")
        entities[user.id] = user
        entities[user2.id] = user2
    }

    override fun containsName(name: String): Boolean {
        return this.findByName(name).isNotEmpty()
    }

    override fun findByName(name: String): Collection<User> {
        var bookings: Collection<User> = arrayListOf()
        entities.forEach { _, entity ->
            if (entity.name.toLowerCase().contains(name)) {
                bookings = bookings.plus(entity)
            }
        }
        return bookings
    }

    override fun add(entity: User) {
        entities[entity.id] = entity
    }

    override fun remove(id: String) {
        entities.remove(id)
    }

    override fun update(entity: User) {
        entities.computeIfPresent(entity.id) { _, _ -> entity }
    }

    override fun contains(id: String): Boolean {
        return entities[id] != null
    }

    override fun get(id: String): Entity<String>? {
        return entities[id]
    }

    override fun getAll(): Collection<User> {
        return entities.values
    }
}