package black_and_white.authentication.token.service

import org.springframework.beans.factory.annotation.Value
import java.util.*
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import javax.crypto.spec.SecretKeySpec

@Service
class JwtServiceImpl (
    @Value("\${jwt.secret}")
    private var secret: String,
) : JwtService {
    private val secretKey: SecretKeySpec
        get() {
            val keyBytes: ByteArray = Base64.getDecoder().decode(secret)
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    override fun generateToken(
        subject: String,
        expirationDate: Date,
        additionalClaims: Map<String, Any>
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

    override fun getUsernameFromToken(token: String): String =
        getAllClaimsFromToken(token).subject

    private fun getAllClaimsFromToken(token: String): Claims =
         Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

}