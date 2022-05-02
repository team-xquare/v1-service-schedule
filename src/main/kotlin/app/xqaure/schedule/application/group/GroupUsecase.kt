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
        const val ADD_MEMBER_GROUP_CODE = "group.member.add"
    }

    suspend fun createGroup(request: CreateGroupRequest): BasicResponse {
        return responseCreator.onSuccess(
            code = CREATE_GROUP_CODE,
            propertyName = CREATE_GROUP_CODE,
        )
    }

    suspend fun addMemberToGroup(): BasicResponse {
        return responseCreator.onSuccess(
            code = ADD_MEMBER_GROUP_CODE,
            propertyName = ADD_MEMBER_GROUP_CODE
        )
    }
}
