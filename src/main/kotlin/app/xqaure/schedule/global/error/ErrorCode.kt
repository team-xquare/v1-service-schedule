package app.xqaure.schedule.global.error

enum class ErrorCode(
    val status: Int,
    val code: String,
) {
    INTERNAL_SERVER_ERROR(500, "server.internal.error"),

    BAD_REQUEST(400, "server.bad.request.error"),

    REQUEST_HANDLER_NOT_FOUND(404, "server.request.handler.not.found"),


    // Schedule domain error
    SCHOOL_SCHEDULE_NOT_FOUND(404, "school.schedule.notfound.error"),

    SCHEDULE_NOT_FOUND(404, "schedule.notfound.error"),

    INVALID_USER(401, "user.invalid.error"),

    UNAUTHORIZED(401, "user.unauthorized.error"),
}
