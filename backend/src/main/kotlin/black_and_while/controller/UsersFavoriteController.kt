package black_and_while.controller

import black_and_while.model.dto.FavoriteDto
import black_and_while.service.UsersFavoriteService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/favorite")
class UsersFavoriteController(private val usersFavoriteService: UsersFavoriteService) {
    @PostMapping("/add")
    fun saveFavorite(favoriteDto: FavoriteDto) {
        usersFavoriteService.saveFavorite(favoriteDto.entityId, favoriteDto.entityType)
    }
}
