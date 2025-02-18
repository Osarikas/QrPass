package ru.myitschool.work.domain.login

interface LoginRepo {
    suspend fun login(username: String): Result<Unit>
}