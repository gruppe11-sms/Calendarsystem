package dk.group11.calendarsystem.clients

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import dk.group11.calendarsystem.exceptions.BadGateWayException
import dk.group11.calendarsystem.security.HEADER_STRING
import dk.group11.calendarsystem.security.SecurityService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.util.*

data class Participant(
        var userId: Long = 0,
        var assignments: List<Assignment> = emptyList(),
        var id: Long = 0

)

data class Room(
        val name: String = "",
        val roomnr: Long = 0
)

data class Course(
        val id: Long = 0,
        val title: String = ""
)

data class Lesson(
        val id: Long = 0,
        override val activityId: Long = 0,
        val rooms: List<Room> = emptyList(),
        val Course: Course = Course()
) : IActivity

interface IActivity {
    val activityId: Long
}


enum class ActivityType {
    Assignment,
    Lesson,
    Event
}

data class Assignment(
        val startdate: Date = Date(),
        val enddate: Date = Date(),
        val description: String = "",
        override val activityId: Long = 0
) : IActivity

data class Event(
        val startdate: Date = Date(),
        val enddate: Date = Date(),
        val description: String = "",
        val eventId: Long = 0,
        override val activityId: Long = 0
) : IActivity

@Service
class CourseSystemClient(private val courseConfig: CourseConfigurationProperties,
                         private val securityService: SecurityService) {

    fun getParticipantsByUserId(userId: Long): List<Participant> {
        val (_, _, result) = Fuel.get(courseConfig.url + "/api/courses/participants/user/" + userId)
                .header(Pair(HEADER_STRING, securityService.getToken()))
                .responseString()

        return result.fold({ data -> ObjectMapper().readValue(data, Array<Participant>::class.java) },
                { error -> throw BadGateWayException(error.toString()) }
        ).toList()
    }

    fun getLessonsByUserId(userId: Long): List<Lesson> {
        val (_, _, result) = Fuel.get(courseConfig.url + "/api/users/" + userId + "/lessons")
                .header(Pair(HEADER_STRING, securityService.getToken()))
                .responseString()

        return result.fold({ data -> ObjectMapper().readValue(data, Array<Lesson>::class.java) },
                { error -> throw BadGateWayException(error.toString()) }
        ).toList()
    }

    fun getAssignmentsByUserId(userId: Long): List<Assignment> {
        val (_, _, result) = Fuel.get(courseConfig.url + "/api/users/" + userId + "/lessons")
                .header(Pair(HEADER_STRING, securityService.getToken()))
                .responseString()

        return result.fold({ data -> ObjectMapper().readValue(data, Array<Assignment>::class.java) },
                { error -> throw BadGateWayException(error.toString()) }
        ).toList()
    }

    fun getEvents(): List<Event> {
        val (_, _, result) = Fuel.get(courseConfig.url + "/api/events")
                .header(Pair(HEADER_STRING, securityService.getToken()))
                .responseString()

        return result.fold({ data -> ObjectMapper().readValue(data, Array<Event>::class.java) },
                { error -> throw BadGateWayException(error.toString()) }
        ).toList()
    }
}

@Configuration
@ConfigurationProperties(prefix = "course")
data class CourseConfigurationProperties(var url: String = "")