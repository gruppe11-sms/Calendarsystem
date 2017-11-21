package dk.group11.calendarsystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
class BadGateWayException(message: String  = "Bad Gateway") : RuntimeException(message)

