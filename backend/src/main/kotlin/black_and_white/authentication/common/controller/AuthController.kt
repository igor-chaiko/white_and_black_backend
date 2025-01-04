package black_and_white.authentication.common.controller

import black_and_white.authentication.common.model.dto.request.AuthenticationRequestDto
import black_and_white.authentication.common.model.dto.response.AuthenticationResponseDto
import black_and_white.authentication.common.model.dto.request.RefreshRequestDto
import black_and_white.authentication.common.model.dto.request.UserDto
import black_and_white.authentication.common.model.dto.response.RefreshResponseDto
import black_and_white.authentication.common.service.AuthenticationService
import black_and_white.user.common.model.entity.User
import black_and_white.user.common.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
){
    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody refreshRequestDto: RefreshRequestDto) =
        RefreshResponseDto(
            authenticationService.
            updateAccessToken(refreshRequestDto.refreshToken)
        )

    @PostMapping("/login")
    fun authenticate(@Valid @RequestBody authenticationRequestDto: AuthenticationRequestDto) =
            authenticationService.authentication(authenticationRequestDto)

    @PostMapping("/register")
    fun register(@Valid @RequestBody userDto: UserDto) : AuthenticationResponseDto {
        val user = User(
            login = userDto.login,
            password = userDto.password,
            email = userDto.email
        )
        userService.saveUser(user)
        val authenticationRequestDto =
            AuthenticationRequestDto(userDto.login, userDto.password)
        return authenticationService.authentication(authenticationRequestDto)
    }
}
