package ru.myitschool.work.domain.login

interface LoginRepo {
    suspend fun login(username: String, password: String): Result<Unit>
}