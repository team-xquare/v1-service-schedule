package app.xqaure.schedule.domain.schedule

import org.springframework.data.annotation.Id
import java.util.*

class Schedule (
    id: UUID,
    date: Date,
    name: String,
    period: Int,
    scheduleId: UUID,
    userId: UUID
) {
    @Id
    private var id: UUID = id

    private var date: Date = date
    private var name: String = name
    private var period: Int = period
    private var scheduleId: UUID = scheduleId
    private var userId: UUID = userId
}