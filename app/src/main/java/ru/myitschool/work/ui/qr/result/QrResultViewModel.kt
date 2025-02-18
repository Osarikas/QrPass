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
import ru.myitschool.work.data.door.DoorNetworkDataSource
import ru.myitschool.work.data.door.DoorRepoImpl
import ru.myitschool.work.domain.door.OpenDoorUseCase
import ru.myitschool.work.domain.entities.OpenEntity

class QrResultViewModel(
    private val useCase: OpenDoorUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    sealed class State{
        object Success : State()
        object Loading : State()
        object Error : State()
    }
    fun openDoor(openEntity: OpenEntity){
        _state.value = State.Loading
        viewModelScope.launch{
            useCase.invoke(openEntity).fold(
                onSuccess = { data->
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