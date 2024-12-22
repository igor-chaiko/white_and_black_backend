package black_and_while.repository

import black_and_while.model.entity.UsersFavorite
import org.springframework.data.repository.CrudRepository

interface UsersFavorite : CrudRepository<UsersFavorite, Long>
