package app.xqaure.schedule.domain.school

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import java.util.UUID

class SchoolSchedule(
    id: UUID,
    date: LocalDateTime,
    name: String
) {
    @Id
    private var id: UUID = id

    private var date: LocalDateTime = date
    private var name: String = name
}
