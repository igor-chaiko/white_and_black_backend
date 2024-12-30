package black_and_while.service

import black_and_while.model.dto.favorites.response.FavoriteDto
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
    override fun like(entityId: Long, entityType: String) {
        val user = userRepository.findByLogin(
            (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        ) ?: throw UsernameNotFoundException("User not found")

        userFavoriteRepository.findByUserIdAndEntityId(user.id!!, entityId)?.let {
            userFavoriteRepository.delete(it)
            return
        }

        userFavoriteRepository.save(
            UsersFavorite(
                userId = user.id!!,
                entityId = entityId,
                entityType = convertToEntity(entityType),
            ),
        )
    }

    override fun getListByEntityType(entityType: String): FavoriteDto {
        val user = userRepository.findByLogin(
            (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        ) ?: throw UsernameNotFoundException("User not found")

        return FavoriteDto(
            userFavoriteRepository.findAllByUserIdAndEntityType(user.id!!, convertToEntity(entityType)).map {
                it.entityId
            }
        )
    }

    private fun convertToEntity(entityType: String): EntityToLike = when (entityType) {
        "COFFEE_SHOP" -> EntityToLike.COFFEE_SHOP
        "DRINK" -> EntityToLike.DRINK
        else -> throw IllegalArgumentException("Unsupported entity type")
    }
}
