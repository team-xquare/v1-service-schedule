package app.xqaure.schedule.domain.schedule

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.annotation.Transient
import java.time.LocalDateTime
import java.util.UUID

@Table(value = "schedule")
class Schedule(
    id: UUID,
    date: LocalDateTime,
    name: String,
    period: Int,
    scheduleId: UUID,
    userId: UUID,

    @Transient
    private val isNew: Boolean = false
) : Persistable<UUID> {
    @Id
    private val id: UUID = id

    private var date: LocalDateTime = date
    private var name: String = name
    private var period: Int = period
    private var scheduleId: UUID = scheduleId
    private var userId: UUID = userId

    override fun getId(): UUID = id
    override fun isNew(): Boolean = isNew
}
