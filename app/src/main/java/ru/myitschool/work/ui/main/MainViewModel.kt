package ru.myitschool.work.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.data.info.InfoNetworkDataSource
import ru.myitschool.work.data.info.InfoRepoImpl
import ru.myitschool.work.domain.info.GetInfoUseCase
import ru.myitschool.work.utils.UserState
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel(
    private val useCase: GetInfoUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> get() = _userState

    private val dataStoreManager = UserDataStoreManager(application)

    fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return try {
            val formattedDate = inputFormat.parse(date)
            if (formattedDate != null) {
                outputFormat.format(formattedDate)
            } else{}
        } catch (_: Exception) {
            "Invalid Date"
        }.toString()
    }

    fun getUserData() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            useCase.invoke().fold(
                onSuccess = { data -> _userState.value = UserState.Success(data) },
                onFailure = { _userState.value = UserState.Error }
            )
        }
    }

    fun clearUsername() {
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                dataStoreManager.clearUsername()
            }
        }

    }
    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val repoImpl = InfoRepoImpl(
                    networkDataSource = InfoNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )

                val useCase = GetInfoUseCase(repoImpl)

                return MainViewModel(
                    useCase, extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                ) as T
            }
        }
    }
}
