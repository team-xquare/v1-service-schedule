package app.xqaure.schedule.domain.group

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("tbl_group_member")
class GroupMember(
    id: UUID,
    userId: UUID,
    scheduleGroupId: UUID,
    is_updatable: Boolean = false,

    @Transient
    private val isNew: Boolean = true
) : Persistable<UUID> {
    @Id
    private var id: UUID = id

    private var userId: UUID = userId
    private var scheduleGroupId: UUID = scheduleGroupId
    private var is_updatable: Boolean = is_updatable

    override fun getId(): UUID = id
    override fun isNew(): Boolean = isNew
}
