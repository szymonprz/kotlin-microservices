package pl.szymonprz.user.api

import pl.szymonprz.NoArgConstructor

@NoArgConstructor
data class UserDto(val name: String, val id: String, var address: String, var city: String, var phoneNo: String)
