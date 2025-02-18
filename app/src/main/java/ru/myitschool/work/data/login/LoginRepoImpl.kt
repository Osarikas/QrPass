package ru.myitschool.work.data.login

import ru.myitschool.work.domain.login.LoginRepo

class LoginRepoImpl(
    private val networkDataSource: LoginNetworkDataSource
) : LoginRepo {
    override suspend fun login(username: String): Result<Unit> {
       return networkDataSource.login(username)
    }
}