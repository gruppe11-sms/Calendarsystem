package dk.group11.calendarsystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServiceError(message: String) : RuntimeException(message)