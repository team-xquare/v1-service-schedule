package app.xqaure.schedule.domain.group

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.annotation.Transient
import java.util.UUID

@Table(value = "tbl_schedule_group")
class Group(
    id: UUID,
    type: String,
    color: String,

    @Transient
    private val isNew: Boolean = false
) : Persistable<UUID> {
    @Id
    private var id: UUID = id

    private var type: String = type
    private var color: String = color

    override fun getId(): UUID = id
    override fun isNew(): Boolean = isNew
}
