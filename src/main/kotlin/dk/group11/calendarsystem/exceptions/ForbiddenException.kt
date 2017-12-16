package dk.group11.calendarsystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.FORBIDDEN)
class ForbiddenException(message: String) : RuntimeException(message)