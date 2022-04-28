package app.xqaure.schedule.global.error.exception

import app.xqaure.schedule.global.error.ErrorCode

open class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException()
