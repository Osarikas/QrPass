package ru.myitschool.work.data.profile

import android.content.Context
import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.dto.EmployeeDTO
import ru.myitschool.work.utils.NetworkModule

class ProfileNetworkDataSource(
    context: Context
) {
    private val client = NetworkModule.httpClient

    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun getInfo():Result<EmployeeDTO> = withContext(Dispatchers.IO){
        runCatching {
            val username = userDataStoreManager.usernameFlow.first()
            val password = userDataStoreManager.passwordFlow.first()
            val result = client.post("${Constants.SERVER_ADDRESS}/api/employee/profile"){
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