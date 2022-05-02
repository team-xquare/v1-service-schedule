package app.xqaure.schedule.presentation.group

import app.xqaure.schedule.application.group.GroupUsecase
import app.xqaure.schedule.presentation.group.dto.CreateGroupRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import java.util.UUID

@RestController
@RequestMapping("/group")
class GroupHandler(
    private val groupUsecase: GroupUsecase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createGroup(request: CreateGroupRequest) =
        groupUsecase.createGroup(request)

    @PostMapping("/{groupId}")
    suspend fun addMemberToGroup(@PathVariable groupId: UUID) =
        groupUsecase.addMemberToGroup()

    @DeleteMapping("/{scheduleId}/member/{userId}")
    suspend fun deleteMemberInGroup(@PathVariable scheduleId: UUID, @PathVariable userId: UUID): ServerResponse {
        TODO()
    }

    @PatchMapping("/{scheduleId}/member/{userId}")
    suspend fun modifyMemberPermission(@PathVariable scheduleId: UUID, @PathVariable userId: UUID): ServerResponse {
        TODO()
    }
}
