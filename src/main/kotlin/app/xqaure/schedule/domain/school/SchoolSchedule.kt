package app.xqaure.schedule.domain.school

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.UUID

@Table(value = "tbl_school_schedule")
class SchoolSchedule(
    date: LocalDate,
    name: String,
    @Transient private val isNew: Boolean = false
) : Persistable<UUID> {
    @Id
    private val id: UUID = UUID.randomUUID()

    var date: LocalDate = date
        private set

    var name: String = name
        private set

    override fun getId(): UUID = id
    override fun isNew(): Boolean = isNew
}
