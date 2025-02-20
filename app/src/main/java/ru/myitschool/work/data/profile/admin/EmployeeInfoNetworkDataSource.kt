package ru.myitschool.work.data.profile.admin

import android.content.Context
import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.myitschool.work.R
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.dto.EmployeeDTO
import ru.myitschool.work.utils.NetworkModule

class EmployeeInfoNetworkDataSource(
   private val context: Context
) {
    private val client = NetworkModule.httpClient
    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun getProfile(login : String):Result<EmployeeDTO> = withContext(Dispatchers.IO){
        runCatching {
            println(login)
            val username = userDataStoreManager.usernameFlow.first()
            println(username)
            val password = userDataStoreManager.passwordFlow.first()
            val result = client.get("${Constants.SERVER_ADDRESS}/api/employee/$login"){
                headers{
                    basicAuth(username, password)
                }
            }
            when(result.status){
                HttpStatusCode.Unauthorized -> {context.getString(R.string.admin_unauthorized)}
                HttpStatusCode.Forbidden -> error(context.getString(R.string.admin_forbidden))
                HttpStatusCode.NotFound -> error(context.getString(R.string.not_found))
                HttpStatusCode.OK -> result.body()
            }
            println(result.bodyAsText())
            result.body()
        }
    }
}