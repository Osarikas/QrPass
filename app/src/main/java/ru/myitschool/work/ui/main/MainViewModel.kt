package ru.myitschool.work.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myitschool.work.data.UserDataStoreManager
import ru.myitschool.work.data.profile.ProfileNetworkDataSource
import ru.myitschool.work.data.profile.ProfileRepoImpl
import ru.myitschool.work.data.entrance.employeeEntrances.EmployeeEntranceListNetworkDataSource
import ru.myitschool.work.data.entrance.employeeEntrances.EmployeeEntranceListRepoImpl
import ru.myitschool.work.data.entrance.lastEntrance.LastEntranceNetworkDataSource
import ru.myitschool.work.data.entrance.lastEntrance.LastEntranceRepoImpl
import ru.myitschool.work.domain.profile.GetProfileUseCase
import ru.myitschool.work.domain.employeeEntrance.employeeEntrances.GetEmployeeEntranceListUseCase
import ru.myitschool.work.domain.employeeEntrance.lastEntrance.GetLastEntranceUseCase
import ru.myitschool.work.entities.EmployeeEntranceEntity
import ru.myitschool.work.utils.UserState

class MainViewModel(
    private val infoUseCase: GetProfileUseCase,
    private val listUseCase: GetEmployeeEntranceListUseCase,
    private val lastEntranceUseCase: GetLastEntranceUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> get() = _userState
    private val _dateState = MutableStateFlow<DateState>(DateState.Loading)
    val dateState: StateFlow<DateState> get() = _dateState

    val listState = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 30
        )
    ) {
        EmployeeEntranceListPagingSource(listUseCase::invoke)
    }.flow.cachedIn(viewModelScope)

    sealed class DateState {
        data object Loading : DateState()
        data class Success(val data : EmployeeEntranceEntity) : DateState()
        data class Error(val message: String?) :  DateState()
    }

    private val dataStoreManager = UserDataStoreManager(application)

    fun getUserData() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            infoUseCase.invoke().fold(
                onSuccess = { data -> _userState.value = UserState.Success(data) },
                onFailure = { e -> _userState.value = UserState.Error
                println(e)}
            )
        }
    }
    fun getLastEntryDate(){
        _dateState.value = DateState.Loading
        viewModelScope.launch {
            lastEntranceUseCase.invoke().fold(
                onSuccess = { data ->
                    _dateState.value = DateState.Success(data)
                },
                onFailure = { e ->
                    _dateState.value = DateState.Error(e.message)
                }
            )
        }
    }

    fun clearUsername() {
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                dataStoreManager.clearCredentials()
            }
        }

    }
    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val profileRepoImpl = ProfileRepoImpl(
                    networkDataSource = ProfileNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )
                val listInfoImpl = EmployeeEntranceListRepoImpl(
                    networkDataSource = EmployeeEntranceListNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )
                val lastEntranceRepoImpl = LastEntranceRepoImpl(
                    networkDataSource = LastEntranceNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )

                val infoUseCase = GetProfileUseCase(profileRepoImpl)
                val listUseCase = GetEmployeeEntranceListUseCase(listInfoImpl)
                val lastEntranceUseCase = GetLastEntranceUseCase(lastEntranceRepoImpl)

                return MainViewModel(
                    infoUseCase,
                    listUseCase,
                    lastEntranceUseCase,
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                ) as T
            }
        }
    }
}
