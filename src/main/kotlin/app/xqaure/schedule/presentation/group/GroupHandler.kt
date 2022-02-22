package app.xqaure.schedule.presentation.group

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import java.util.UUID

@RestController
@RequestMapping("/group")
class GroupHandler {

    @PostMapping
    suspend fun createGroup(request: ServerRequest): ServerResponse {
        TODO()
    }

    @PostMapping("/{groupId}")
    suspend fun addMemberToGroup(@PathVariable groupId: UUID): ServerResponse {
        TODO()
    }

    @DeleteMapping("/{scheduleId}/member/{userId}")
    suspend fun deleteMemberInGroup(@PathVariable scheduleId: UUID, @PathVariable userId: UUID): ServerResponse {
        TODO()
    }

    @PatchMapping("/{scheduleId}/member/{userId}")
    suspend fun modifyMemberPermission(@PathVariable scheduleId: UUID, @PathVariable userId: UUID): ServerResponse {
        TODO()
    }
}
