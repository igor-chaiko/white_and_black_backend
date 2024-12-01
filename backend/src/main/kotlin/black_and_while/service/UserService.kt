package black_and_while.service

import black_and_while.model.entity.User

/**
 * Класс для работы с пользователями
 */
interface UserService {
    /**
     * Сохранение пользователя.
     *
     * @param user пользователь
     */
    fun saveUser(user: User): Int
}
