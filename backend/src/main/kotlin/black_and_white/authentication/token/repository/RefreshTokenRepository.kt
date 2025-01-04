package black_and_white.authentication.token.repository

import black_and_white.authentication.token.model.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long>{
    fun findByToken(token: String): RefreshToken?
}