package black_and_white.user.common.controller

import black_and_white.user.common.model.dto.request.PasswordChangeDto
import black_and_white.user.common.model.entity.User
import black_and_white.user.common.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserHandler(
    private val userService: UserService,
) {
    @GetMapping("/users")
    fun setTestUser(
        @RequestParam login: String,
        @RequestParam password: String,
        @RequestParam email: String
    ): ResponseEntity<User> {
        if (password.length < 7) {
            return ResponseEntity.badRequest().build()
        }

        val res = userService.saveUser(User(login = login, password = password, email = email))

        if (res == 0) {
            // уникальность логина
            return ResponseEntity.badRequest().build()
        }

        return ResponseEntity.ok().build()
    }

    @PostMapping("/changePassword")
    fun changePassword(@Valid @RequestBody passwordChangeDto: PasswordChangeDto) =
        userService.changePassword(
            passwordChangeDto.oldPassword,
            passwordChangeDto.newPassword,
            passwordChangeDto.newPasswordRepeat,
        )
}
