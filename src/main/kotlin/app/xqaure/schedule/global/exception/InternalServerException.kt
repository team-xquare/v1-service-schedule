package app.xqaure.schedule.global.exception

import app.xqaure.schedule.global.error.ErrorCode
import app.xqaure.schedule.global.error.exception.BusinessException

class InternalServerException : BusinessException(ErrorCode.INTERNAL_SERVER_ERROR)
