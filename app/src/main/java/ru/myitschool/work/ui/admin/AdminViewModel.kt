package ru.myitschool.work.ui.admin

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
import ru.myitschool.work.data.profile.admin.EmployeeInfoNetworkDataSource
import ru.myitschool.work.data.profile.admin.EmployeeInfoRepoImpl
import ru.myitschool.work.data.scannerState.ScannerStateNetworkDataSource
import ru.myitschool.work.data.scannerState.ScannerStateRepoImpl
import ru.myitschool.work.domain.profile.admin.EmployeeInfoRepo
import ru.myitschool.work.domain.profile.admin.GetEmployeeInfoUseCase
import ru.myitschool.work.domain.scannerBlockState.SetScannerStateUseCase
import ru.myitschool.work.entities.EmployeeEntity

class AdminViewModel(
    private val getInfoUseCase: GetEmployeeInfoUseCase,
    private val setScannerStateUseCase : SetScannerStateUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _infoState = MutableStateFlow<SearchState>(SearchState.Loading)
    val infoState: StateFlow<SearchState> = _infoState.asStateFlow()

    private val _blockState = MutableStateFlow<BlockState>(BlockState.Loading)
    val blockState: StateFlow<BlockState> = _blockState.asStateFlow()

    sealed class SearchState {
        data object Loading : SearchState()
        data class Success(val data: EmployeeEntity) : SearchState()
        data class Error(val message: String?) :  SearchState()
    }
    sealed class BlockState {
        data object Loading : BlockState()
        data object Success : BlockState()
        data class Error(val message: String?) :  BlockState()
    }
    fun searchUser(login : String){
        _infoState.value = SearchState.Loading
        viewModelScope.launch {
            getInfoUseCase.invoke(login).fold(
                onSuccess = { data ->
                    _infoState.value = SearchState.Success(data)
                },
                onFailure = { e ->
                    _infoState.value = SearchState.Error(e.message)
                }
            )
        }
    }
    fun changeState(login: String){
        viewModelScope.launch {
            _blockState.value = BlockState.Loading
            setScannerStateUseCase.invoke(login).fold(
                onSuccess = { _ ->
                    _blockState.value = BlockState.Success
                },
                onFailure = { e ->
                    _blockState.value = BlockState.Error(e.message)
                }
            )
        }
    }
    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val infoRepoImpl = EmployeeInfoRepoImpl(
                    networkDataSource = EmployeeInfoNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )
                val setScannerStateRepoImpl = ScannerStateRepoImpl(
                    networkDataSource = ScannerStateNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )
                val getInfoUseCase = GetEmployeeInfoUseCase(infoRepoImpl)
                val setScannerStateUseCase = SetScannerStateUseCase(setScannerStateRepoImpl)

                return AdminViewModel(
                    getInfoUseCase, setScannerStateUseCase, extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                ) as T
            }
        }
    }
}