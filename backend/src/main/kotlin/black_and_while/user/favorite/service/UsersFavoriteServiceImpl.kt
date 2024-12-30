package black_and_while.user.favorite.service

import black_and_while.model.dto.favorites.response.FavoriteDto
import black_and_while.model.entity.UsersFavorite
import black_and_while.user.common.repository.UserRepository
import black_and_while.user.favorite.convertToFavoriteEntity
import black_and_while.user.favorite.repository.UsersFavoriteRepository
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
                entityType = entityType.convertToFavoriteEntity(),
            ),
        )
    }

    override fun getListByEntityType(entityType: String): FavoriteDto {
        val user = userRepository.findByLogin(
            (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        ) ?: throw UsernameNotFoundException("User not found")

        return FavoriteDto(
            userFavoriteRepository.findAllByUserIdAndEntityType(user.id!!, entityType.convertToFavoriteEntity()).map {
                it.entityId
            }
        )
    }
}
