package pl.szymonprz.booking.application

import pl.szymonprz.booking.domain.ReadOnlyRepository

abstract class ReadOnlyBaseService<TE, T>(protected open val repository: ReadOnlyRepository<TE, T>)