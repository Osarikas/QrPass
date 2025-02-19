package ru.myitschool.work.utils

import ru.myitschool.work.entities.EmployeeEntity

sealed class UserState {
    object Loading : UserState()
    data class Success(val employeeEntity: EmployeeEntity) : UserState()
    object Error : UserState()
}