package pl.szymonprz.user.application.impl

import org.springframework.stereotype.Service
import pl.szymonprz.user.application.BaseService
import pl.szymonprz.user.application.UserService
import pl.szymonprz.user.domain.model.Entity
import pl.szymonprz.user.domain.UserRepository
import pl.szymonprz.user.domain.model.User

@Service("UserService")
class UserServiceImpl(private val userRepository: UserRepository) : BaseService<User, String>(userRepository), UserService {

    override fun add(entity: User) {
        if(userRepository.containsName(entity.name)){
            throw RuntimeException(String.format("There is already entity with the name - %s", entity.name))
        }
        if(entity.name.isBlank()){
            throw RuntimeException("User name cannot be null or empty string.")
        }
        super.add(entity)
    }

    override fun update(entity: User) {
        userRepository.update(entity)
    }

    override fun delete(id: String) {
        userRepository.remove(id)
    }

    override fun findById(id: String): Entity<String>? {
        return userRepository.get(id)
    }

    override fun findByName(name: String): Collection<User> {
        return userRepository.findByName(name)
    }

    override fun findByCriteria(name: Map<String, ArrayList<String>>): Collection<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}