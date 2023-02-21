package app.xqaure.schedule.domain.school

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*

@Table(value = "tbl_school_schedule")
class SchoolSchedule(
    date: LocalDate,
    name: String,
) : Persistable<UUID> {
    @Id
    private var id: UUID = UUID.randomUUID()

    var date: LocalDate = date

    var name: String = name

    override fun getId(): UUID = id
    override fun isNew(): Boolean = true
}
