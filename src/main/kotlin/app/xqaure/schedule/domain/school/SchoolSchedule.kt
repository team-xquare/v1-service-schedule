package app.xqaure.schedule.domain.school

import org.springframework.data.annotation.Id
import java.util.*

class SchoolSchedule (
    id: UUID,
    date: Date,
    name: String
) {
    @Id
    private var id: UUID = id

    private var date: Date = date
    private var name: String = name
}