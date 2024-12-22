package black_and_while.service

import black_and_while.model.entity.UsersFavorite
import black_and_while.model.entity.enumeration.EntityToLike
import black_and_while.repository.UserRepository
import black_and_while.repository.UsersFavoriteRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsersFavoriteServiceImpl(
    private val userFavoriteRepository: UsersFavoriteRepository,
    private val userRepository: UserRepository,
) : UsersFavoriteService {
    override fun saveFavorite(entityId: Long, entityType: String) {
        val entityToLike: EntityToLike = when (entityType) {
            "COFFEE_SHOP" -> EntityToLike.COFFEE_SHOP
            "DRINK" -> EntityToLike.DRINK
            else -> throw IllegalArgumentException("Unsupported entity type")
        }

        val user = userRepository.findByLogin(
            (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        ) ?: throw UsernameNotFoundException("User not found")

        userFavoriteRepository.save(UsersFavorite(user.id!!, entityId, entityToLike))
    }
}
