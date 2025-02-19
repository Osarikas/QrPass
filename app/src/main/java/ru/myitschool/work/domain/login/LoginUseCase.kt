package ru.myitschool.work.domain.login

class LoginUseCase(
    private val repo: LoginRepo
) {
    suspend operator fun invoke(username: String, password: String) = repo.login(username, password)
}