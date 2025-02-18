package ru.myitschool.work.data.door

import android.content.Context
import io.ktor.client.call.body
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.data.dto.OpenRequestDTO
import ru.myitschool.work.utils.NetworkModule

class DoorNetworkDataSource(
    context: Context
) {
    private val client = NetworkModule.httpClient
    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun openDoor(openRequestDTO: OpenRequestDTO): Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val username = userDataStoreManager.usernameFlow.first()
            val result = client.patch("${Constants.SERVER_ADDRESS}/api/$username/open"){
                contentType(ContentType.Application.Json)
                setBody(openRequestDTO)
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            println(result.bodyAsText())
            result.body()
        }
    }

}