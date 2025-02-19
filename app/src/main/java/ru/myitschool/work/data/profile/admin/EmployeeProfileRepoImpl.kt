package ru.myitschool.work.data.profile.admin

import ru.myitschool.work.entities.EmployeeEntity
import ru.myitschool.work.domain.profile.admin.EmployeeProfileRepo

class EmployeeProfileRepoImpl(
    private val networkDataSource: EmployeeNetworkDataSource
) : EmployeeProfileRepo {
    override suspend fun getInfo(login : String): Result<EmployeeEntity> {
        return networkDataSource.getProfile(login).map { dto ->
            EmployeeEntity(
                id = dto.id ?: 0,
                login = dto.login ?: "",
                name = dto.name ?: "Не указано",
                authority = dto.authority ?: "EMPLOYEE",
                photoUrl = dto.photoUrl,
                position = dto.position ?: "Отсутствует",
                qrEnabled = dto.qrEnabled ?: false
            )
        }
    }
}