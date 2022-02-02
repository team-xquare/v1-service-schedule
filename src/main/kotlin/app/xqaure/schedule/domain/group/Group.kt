package app.xqaure.schedule.domain.group

import org.springframework.data.annotation.Id
import java.util.*

class Group (
    id: UUID,
    type: String,
    color: String
) {
    @Id
    private var id: UUID = id

    private var type: String = type
    private var color: String = color
}