package ru.myitschool.work.data.login

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.utils.NetworkModule

class LoginNetworkDataSource {
    private val client = NetworkModule.httpClient
    suspend fun login(username: String):Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val result = client.get("${Constants.SERVER_ADDRESS}/api/$username/auth")

            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            println(result.bodyAsText())
            result.body()
        }
    }

}