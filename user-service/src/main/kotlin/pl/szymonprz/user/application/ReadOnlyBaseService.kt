package pl.szymonprz.user.application

import pl.szymonprz.user.domain.ReadOnlyRepository

abstract class ReadOnlyBaseService<TE, T>(protected open val repository: ReadOnlyRepository<TE, T>)