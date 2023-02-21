package app.xqaure.schedule.global.error

enum class ErrorCode(
    val status: Int,
    val code: String,
) {
    INTERNAL_SERVER_ERROR(500, "server.internal.error"),

    // Schedule domain error
    SCHOOL_SCHEDULE_NOT_FOUND(404, "school.schedule.notfound.error"),

    SCHEDULE_NOT_FOUND(404, "schedule.notfound.error")
}
