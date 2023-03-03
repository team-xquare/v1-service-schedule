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
) : Persistable<String> {
    @Id
    private var id: String = UUID.randomUUID().toString()

    var date: LocalDate = date

    var name: String = name

    override fun getId(): String = id
    override fun isNew(): Boolean = true
}
