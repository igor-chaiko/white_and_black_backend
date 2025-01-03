package black_and_while.user.favorite.controller

import black_and_while.model.dto.favorites.response.FavoriteDto
import black_and_while.user.favorite.model.dto.request.LikeDto
import black_and_while.user.favorite.service.UsersFavoriteService
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

    @GetMapping("/getFavoriteDrinks")
    fun getFavoriteDrinks() {
        usersFavoriteService.getFavoriteDrinks()
    }

    @GetMapping("/getFavoriteCoffeeShops")
    fun getFavoriteCoffeeShops() {
        usersFavoriteService.getFavoriteCoffeeShops()
    }
}
