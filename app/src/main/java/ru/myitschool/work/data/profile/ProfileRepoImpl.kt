package ru.myitschool.work.data.profile

import ru.myitschool.work.entities.EmployeeEntity
import ru.myitschool.work.domain.profile.ProfileRepo

class ProfileRepoImpl(
    private val networkDataSource: ProfileNetworkDataSource
): ProfileRepo {
    override suspend fun getInfo(): Result<EmployeeEntity> {
        return networkDataSource.getInfo().map { dto->
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