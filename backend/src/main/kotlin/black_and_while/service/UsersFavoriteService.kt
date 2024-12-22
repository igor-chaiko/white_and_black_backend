package black_and_while.service

interface UsersFavoriteService {
    fun saveFavorite(entityId: Long, entityType: String)
}
