package pl.szymonprz.user.api

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.szymonprz.user.api.UserDto
import pl.szymonprz.user.application.UserService
import pl.szymonprz.user.domain.model.Entity
import pl.szymonprz.user.domain.model.User


@RestController
@RequestMapping("/v1/user")
class UserController(protected var userService: UserService) {

    /**
     * Fetch bookings with the specified name. A partial case-insensitive match
     * is supported. So `http://.../booking/rest` will find any
     * bookings with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of bookings.
     */
    @GetMapping
    fun findByName(@RequestParam("name") name: String): ResponseEntity<Collection<User>> {
        logger.info(String.format("user-service findByName() invoked:{} for {} ", userService.javaClass.name, name))
        return try {
            val bookings = userService.findByName(name.toLowerCase())
            if (bookings.isNotEmpty())
                ResponseEntity(bookings, HttpStatus.OK)
            else
                ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (ex: Exception) {
            logger.warn("Exception raised findByName User REST Call", ex)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    /**
     * Fetch bookings with the given id.
     * `http://.../v1/bookings/{id}` will return booking with given
     * id.
     *
     * @param id
     * @return A non-null, non-empty collection of bookings.
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Entity<String>> {
        logger.info(String.format("user-service findById() invoked:{} for {} ", userService.javaClass.name, id))
        return try {
            val booking = userService.findById(id)
            if (booking != null)
                ResponseEntity(booking, HttpStatus.OK)
            else
                ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (ex: Exception) {
            logger.warn("Exception raised findById User REST Call {0}", ex)
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }


    }

    /**
     * Add booking with the specified information.
     *
     * @param userDto
     * @return A non-null booking.
     */
    @PostMapping
    fun add(@RequestBody userDto: UserDto): ResponseEntity<User> {
        logger.info(String.format("user-service add() invoked: %s for %s", userService.javaClass.name, userDto.name))
        val user = User("", "", "", "", "")
        BeanUtils.copyProperties(userDto, user)
        try {
            userService.add(user)
        } catch (ex: Exception) {
            logger.warn("Exception raised add User REST Call {0}", ex)
            return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        }
        return ResponseEntity(HttpStatus.CREATED)
    }

    companion object {
        protected val logger: Logger = LogManager.getLogger(UserController::class.java.name)
    }
}