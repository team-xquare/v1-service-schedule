package app.xqaure.schedule.domain.group

import org.springframework.data.annotation.Id
import java.util.UUID

class GroupMember(
    id: UUID,
    userId: UUID,
    scheduleGroupId: UUID,
    is_updatable: Boolean = false
) {
    @Id
    private var id: UUID = id

    private var userId: UUID = userId
    private var scheduleGroupId: UUID = scheduleGroupId
    private var is_updatable: Boolean = is_updatable
}
