package black_and_white.user.favorite.controller

import black_and_white.user.favorite.model.dto.request.LikeDto
import black_and_white.user.favorite.service.UsersFavoriteService
import org.springframework.web.bind.annotation.GetMapping
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
