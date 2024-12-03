package black_and_while.repository

import black_and_while.model.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long>{
    fun findByToken(token: String): RefreshToken?
}