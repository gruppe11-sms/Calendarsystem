package dk.group11.calendarsystem.clients

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import dk.group11.coursesystem.security.HEADER_STRING
import dk.group11.coursesystem.security.SecurityService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.util.*

data class Participant(
        var userId: Long = 0,
        var assignments: List<Assignment> = emptyList(),
        var id: Long = 0

)

data class Assignment(
        val startdate: Date = Date(),
        val enddate: Date = Date(),
        val description: String = ""
)


@Service
class CourseSystemClient(private val courseConfig: CourseConfigurationProperties, private val securityService: SecurityService) {

    fun getParticipantsByUserId(userId: Long): List<Participant> {
        val (_, _, result) = Fuel.get(courseConfig.url + "/api/courses/participants/user/" + userId)
                .header(Pair(HEADER_STRING, securityService.getToken()))
                .responseString()
        // TODO fix error handling
        return result.fold({ data -> ObjectMapper().readValue(data, Array<Participant>::class.java) },
                { error -> throw TODO(error.toString()) }).toList()
    }
}

@Configuration
@ConfigurationProperties(prefix = "course")
data class CourseConfigurationProperties(var url: String = "")