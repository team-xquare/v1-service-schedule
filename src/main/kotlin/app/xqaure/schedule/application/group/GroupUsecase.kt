package app.xqaure.schedule.application.group

import app.xqaure.schedule.presentation.dto.BasicResponse
import app.xqaure.schedule.presentation.dto.ResponseCreator
import app.xqaure.schedule.presentation.group.dto.CreateGroupRequest
import org.springframework.stereotype.Service

@Service
class GroupUsecase(
    private val responseCreator: ResponseCreator
) {

    companion object {
        const val CREATE_GROUP_CODE = "group.create"
    }

    suspend fun createGroup(request: CreateGroupRequest): BasicResponse {
        return responseCreator.fromMessageSource(
            code = CREATE_GROUP_CODE,
            propertyName = CREATE_GROUP_CODE,
        )
    }
}
