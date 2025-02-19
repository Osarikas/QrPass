package ru.myitschool.work.ui.qr.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.myitschool.work.data.door.open.DoorNetworkDataSource
import ru.myitschool.work.data.door.open.DoorRepoImpl
import ru.myitschool.work.domain.door.open.OpenDoorUseCase

class QrResultViewModel(
    private val useCase: OpenDoorUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    sealed class State{
        data object Success : State()
        data object Loading : State()
        data object Error : State()
    }
    fun openDoor(code: String){
        _state.value = State.Loading
        viewModelScope.launch{
            useCase.invoke(code).fold(
                onSuccess = { _ ->
                    _state.value = State.Success
                },
                onFailure = { _ ->
                    _state.value = State.Error
                }
            )
        }
    }
    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val repoImpl = DoorRepoImpl(
                    networkDataSource = DoorNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )

                val useCase = OpenDoorUseCase(repoImpl)

                return QrResultViewModel(
                    useCase, extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                ) as T
            }
        }
    }

}