package ru.myitschool.work.data.entrance.employeeEntrances

import android.content.Context
import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.data.dto.EmployeeEntranceListPagingDTO
import ru.myitschool.work.utils.NetworkModule

class EmployeeEntranceListNetworkDataSource(
    context: Context
){
    private val client = NetworkModule.httpClient
    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun getList(pageNum: Int, pageSize: Int):Result<EmployeeEntranceListPagingDTO> = withContext(Dispatchers.IO){
        runCatching {
            val username = userDataStoreManager.usernameFlow.first()
            val password = userDataStoreManager.passwordFlow.first()
            val result = client.get("${Constants.SERVER_ADDRESS}/api/entrance?page=$pageNum&size=$pageSize"){
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