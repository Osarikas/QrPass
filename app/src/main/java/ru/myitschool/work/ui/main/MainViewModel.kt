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
import ru.myitschool.work.data.info.InfoNetworkDataSource
import ru.myitschool.work.data.info.InfoRepoImpl
import ru.myitschool.work.data.entrance.employeeEntrances.EmployeeEntranceListNetworkDataSource
import ru.myitschool.work.data.entrance.employeeEntrances.EmployeeEntranceListRepoImpl
import ru.myitschool.work.domain.info.GetInfoUseCase
import ru.myitschool.work.domain.employeeEntrance.employeeEntrances.GetEmployeeEntranceListUseCase
import ru.myitschool.work.utils.UserState

class MainViewModel(
    private val infoUseCase: GetInfoUseCase,
    private val listUseCase: GetEmployeeEntranceListUseCase,
    application: Application
) : AndroidViewModel(application) {

    val listState = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 30
        )
    ) {
        println("Creating PagingSource")
        EmployeeEntranceListPagingSource(listUseCase::invoke)
    }.flow.cachedIn(viewModelScope)
    init {
        viewModelScope.launch {
            listState.collect { pagingData ->
                if (pagingData.toString().isEmpty()) {
                    println("No data in paging data.")
                } else {
                    println("Data received: $pagingData")
                }
            }
        }
    }

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> get() = _userState

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
                val infoRepoImpl = InfoRepoImpl(
                    networkDataSource = InfoNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )
                val listInfoImpl = EmployeeEntranceListRepoImpl(
                    networkDataSource = EmployeeEntranceListNetworkDataSource(
                        context = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    )
                )

                val infoUseCase = GetInfoUseCase(infoRepoImpl)
                val listUseCase = GetEmployeeEntranceListUseCase(listInfoImpl)

                return MainViewModel(
                    infoUseCase, listUseCase,  extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                ) as T
            }
        }
    }
}
