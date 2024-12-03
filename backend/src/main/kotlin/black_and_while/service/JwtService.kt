package black_and_while.service

import org.springframework.beans.factory.annotation.Value
import java.util.*
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import javax.crypto.spec.SecretKeySpec

@Service
class JwtService (
    @Value("\${jwt.secret}")
    private var secret: String,
){
    private val secretKey: SecretKeySpec
        get() {
            val keyBytes: ByteArray = Base64.getDecoder().decode(secret)
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    fun generateToken(
        subject: String,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String {
        val issuedDate = Date()

        return Jwts.builder()
            .claims(additionalClaims)
            .subject(subject)
            .issuedAt(issuedDate)
            .expiration(expirationDate)
            .signWith(secretKey)
            .compact()
    }

    fun getUsernameFromToken(token: String): String =
        getAllClaimsFromToken(token).subject

    private fun getAllClaimsFromToken(token: String): Claims =
         Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

}