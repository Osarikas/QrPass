package ru.myitschool.work.data.login

import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.utils.NetworkModule

class LoginNetworkDataSource {
    private val client = NetworkModule.httpClient
    suspend fun login(username: String, password: String):Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val result = client.get("${Constants.SERVER_ADDRESS}/api/employee/login"){
                headers{
                    basicAuth(username, password)
                }
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }

}