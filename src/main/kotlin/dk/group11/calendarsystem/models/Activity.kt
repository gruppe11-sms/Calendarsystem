package dk.group11.calendarsystem.models

import java.util.*
import javax.persistence.*

data class ActivityDTO(
        val id: Long = 0,
        val title: String = "",
        val startDate: Date = Date(),
        val endDate: Date = Date(),
        val participants: Set<ActivityParticipantDTO> = mutableSetOf()
)

@Entity
data class Activity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var title: String = "",
        var startDate: Date = Date(),
        var endDate: Date = Date(),

        @ManyToMany(cascade = arrayOf(CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH))
        @JoinTable(name = "activities_participants_participants",
                joinColumns = arrayOf(JoinColumn(name = "activity_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "user_id"))
        )
        var participants: Set<ActivityParticipant> = mutableSetOf()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Activity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun toDTO(recursive: Boolean = true): ActivityDTO {
        val participants = if (recursive) {
            this.participants.map { it.toDTO(false) }.toSet()
        } else {
            emptySet()
        }

        return ActivityDTO(
                id = id,
                title = title,
                startDate = startDate,
                endDate = endDate,
                participants = participants
        )
    }
}
