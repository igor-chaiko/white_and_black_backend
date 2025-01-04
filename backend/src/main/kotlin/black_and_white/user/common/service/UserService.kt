package black_and_white.user.common.service

import black_and_white.user.common.model.entity.User

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
