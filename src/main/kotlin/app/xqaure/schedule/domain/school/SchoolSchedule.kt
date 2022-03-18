package app.xqaure.schedule.domain.school

import org.springframework.data.annotation.Id
import java.time.LocalDate
import java.util.UUID

class SchoolSchedule(
    date: LocalDate,
    name: String
) {
    @Id
    val id: UUID = UUID.randomUUID()

    var date: LocalDate = date
        private set

    var name: String = name
        private set
}
