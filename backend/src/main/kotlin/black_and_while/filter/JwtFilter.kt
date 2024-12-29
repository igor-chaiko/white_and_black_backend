package black_and_while.filter

import black_and_while.service.JwtService
import black_and_while.service.JwtUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtFilter(
    private val jwtService: JwtService,
    private val userDetailsService: JwtUserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                val token = authHeader.substring(7)
                val username = jwtService.getUsernameFromToken(token)

                if (SecurityContextHolder.getContext().authentication == null) {
                    val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

                    if (username == userDetails.username) {
                        val authToken = UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.authorities
                        )
                        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                }
            } catch (_: Exception) {
            }
        }
        filterChain.doFilter(request, response)
    }

}