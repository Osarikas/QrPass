package ru.myitschool.work.data.scannerState

import android.content.Context
import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.patch
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.utils.NetworkModule

class ScannerStateNetworkDataSource(
    context: Context
) {
    private val client = NetworkModule.httpClient
    private val userDataStoreManager = UserDataStoreManager.getInstance(context)
    suspend fun setScannerState(login: String):Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val username = userDataStoreManager.usernameFlow.first()
            val password = userDataStoreManager.passwordFlow.first()
            val result = client.patch("${Constants.SERVER_ADDRESS}/api/employee/$login/change_state"){
                headers{
                    basicAuth(username, password)
                }
            }
            if(result.status == HttpStatusCode.PreconditionFailed){
                error("Нельзя блокировать вход администратору")
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}