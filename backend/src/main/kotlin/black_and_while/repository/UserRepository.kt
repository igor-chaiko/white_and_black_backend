package black_and_while.repository

import black_and_while.model.entity.User
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User, Long> {

    @Modifying
    @Query(
        """INSERT INTO users (login, password, email) VALUES (:login, :password, :email)
           ON CONFLICT (login) DO NOTHING
           """
    )
    fun saveUserWithUniqueLogin(
        @Param("login") login: String,
        @Param("password") password: String,
        @Param("email") email: String,
    ): Int

    fun findByLogin(login: String): User?
}
