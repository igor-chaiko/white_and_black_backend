package black_and_while.controller

import black_and_while.model.dto.favorites.response.FavoriteDto
import black_and_while.model.dto.favorites.request.LikeDto
import black_and_while.service.UsersFavoriteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/favorite")
class UsersFavoriteController(private val usersFavoriteService: UsersFavoriteService) {
    @PostMapping("/like")
    fun addToFavorite(@RequestBody likeDto: LikeDto) {
        usersFavoriteService.like(likeDto.entityId, likeDto.entityType)
    }

    @GetMapping("/getEntityList/{entityType}")
    fun getFavoriteList(@PathVariable entityType: String): FavoriteDto {
        return usersFavoriteService.getListByEntityType(entityType)
    }
}
