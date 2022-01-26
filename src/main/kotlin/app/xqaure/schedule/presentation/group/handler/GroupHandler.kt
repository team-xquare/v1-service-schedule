package app.xqaure.schedule.presentation.group.handler

import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Controller
class GroupHandler {
    suspend fun createGroup(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun addMemberToGroup(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun deleteMemberInGroup(request: ServerRequest): ServerResponse {
        TODO()
    }

    suspend fun modifyMemberPermission(request: ServerRequest): ServerResponse {
        TODO()
    }
}
