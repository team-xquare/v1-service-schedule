package app.xqaure.schedule.domain.schedule

import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotNull

@Table(value = "tbl_schedule")
class Schedule(
    date: LocalDate,
    name: String,

    @field: NotNull
    @field: Length(min = 36, max = 36)
    var userId: String,

) : Persistable<String> {

    @Id
    private var id: String = UUID.randomUUID().toString()

    var date: LocalDate = date

    var name: String = name

    override fun getId(): String = id
    override fun isNew(): Boolean = true

    fun setSchedule(id: String, date: LocalDate, name: String, userId: String): Schedule {
        this.id = id
        this.date = date
        this.name = name
        this.userId = userId
        return this
    }
}
