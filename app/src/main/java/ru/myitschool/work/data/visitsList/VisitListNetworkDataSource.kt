package ru.myitschool.work.data.visitsList

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
import ru.myitschool.work.data.dto.VisitListPagingDTO
import ru.myitschool.work.utils.NetworkModule

class VisitListNetworkDataSource(
    context: Context
){
    private val client = NetworkModule.httpClient
    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun getList(pageNum: Int, pageSize: Int):Result<VisitListPagingDTO> = withContext(Dispatchers.IO){
        runCatching {
            val username = userDataStoreManager.usernameFlow.first()
            val result = client.get("${Constants.SERVER_ADDRESS}/api/$username/visits?page=$pageNum&size=$pageSize")

            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}