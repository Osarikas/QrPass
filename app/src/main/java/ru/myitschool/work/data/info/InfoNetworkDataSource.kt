package ru.myitschool.work.data.info

import android.content.Context
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.data.dto.UserDTO
import ru.myitschool.work.utils.NetworkModule

class InfoNetworkDataSource(
    context: Context
) {
    private val client = NetworkModule.httpClient

    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun getInfo():Result<UserDTO> = withContext(Dispatchers.IO){
        runCatching {
            val username = userDataStoreManager.usernameFlow.first()
            val result = client.get("${Constants.SERVER_ADDRESS}/api/$username/info")

            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            println(result.bodyAsText())
            result.body()
        }
    }
}