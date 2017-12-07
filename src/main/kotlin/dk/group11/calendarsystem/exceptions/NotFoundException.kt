package dk.group11.calendarsystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException(message: String = "Not found") : RuntimeException(message)