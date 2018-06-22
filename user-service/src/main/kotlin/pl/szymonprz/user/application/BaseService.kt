package pl.szymonprz.user.application

import pl.szymonprz.user.domain.Repository


abstract class BaseService<TE, T>(override val repository: Repository<TE, T>) : ReadOnlyBaseService<TE, T>(repository) {

    open fun all(): Collection<TE> = repository.getAll()

    open fun add(entity: TE) {
        repository.add(entity)
    }
}