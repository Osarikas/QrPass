package ru.myitschool.work.data.login

import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
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
            println("$username $password")
            val result = client.post("${Constants.SERVER_ADDRESS}/api/employee/login"){
                headers{
                    basicAuth(username, password)
                }
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            println(result.bodyAsText())
            result.body()
        }
    }

}