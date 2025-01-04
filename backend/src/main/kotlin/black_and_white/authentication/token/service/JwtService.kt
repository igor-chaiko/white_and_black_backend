package black_and_white.authentication.token.service

import java.util.*

interface JwtService {
    fun generateToken(
        subject: String,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String

    fun getUsernameFromToken(token: String): String
}
