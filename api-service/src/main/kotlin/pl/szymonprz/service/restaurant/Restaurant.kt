package pl.szymonprz.service.restaurant

import pl.szymonprz.NoArgConstructor

@NoArgConstructor
data class Restaurant(var id: String, var name: String, var isModified: Boolean, var tables : List<Table> = listOf())