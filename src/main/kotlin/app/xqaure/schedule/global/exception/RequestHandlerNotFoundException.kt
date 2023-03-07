package app.xqaure.schedule.global.exception

import app.xqaure.schedule.global.error.ErrorCode
import app.xqaure.schedule.global.error.exception.BusinessException

class RequestHandlerNotFoundException : BusinessException(ErrorCode.REQUEST_HANDLER_NOT_FOUND)