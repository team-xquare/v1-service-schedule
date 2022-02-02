package app.xqaure.schedule.domain.schedule

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import java.util.UUID

class Schedule(
    id: UUID,
    date: LocalDateTime,
    name: String,
    period: Int,
    scheduleId: UUID,
    userId: UUID
) {
    @Id
    private var id: UUID = id

    private var date: LocalDateTime = date
    private var name: String = name
    private var period: Int = period
    private var scheduleId: UUID = scheduleId
    private var userId: UUID = userId
}
