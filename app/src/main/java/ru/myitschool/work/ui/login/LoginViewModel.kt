package ru.myitschool.work.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.data.login.LoginNetworkDataSource
import ru.myitschool.work.data.login.LoginRepoImpl
import ru.myitschool.work.domain.login.LoginUseCase

class LoginViewModel(
    private val useCase: LoginUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val dataStoreManager = UserDataStoreManager(application)
    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        viewModelScope.launch{
            val username = dataStoreManager.usernameFlow.first()
            if(username != "")
                login(username)
        }

    }

    sealed class State {
        object Idle : State()
        object Loading : State()
        object Success : State()
        data class Error(val message: String?) :  State()
    }


    fun login(username: String) {
        _state.value = State.Loading
        viewModelScope.launch{
            useCase.invoke(username).fold(
                onSuccess = { data ->
                    dataStoreManager.saveUsername(username)
                    _state.value = State.Success
                },
                onFailure = {e->
                    println(e)
                    _state.value = State.Error(e.message)

                }
            )
        }
    }
    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val repoImpl = LoginRepoImpl(
                    networkDataSource = LoginNetworkDataSource()
                )

                val useCase = LoginUseCase(repoImpl)

                return LoginViewModel(
                    useCase, extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                ) as T
            }
        }
    }
}
