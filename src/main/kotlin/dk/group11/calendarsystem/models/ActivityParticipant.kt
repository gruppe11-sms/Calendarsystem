package dk.group11.calendarsystem.models

import javax.persistence.*

data class ActivityParticipantDTO(
        val userId: Long = 0,
        val activities: Set<ActivityDTO> = mutableSetOf()
)

@Entity
class ActivityParticipant(
        @Id
        var userId: Long = 0,
        @ManyToMany
        @JoinTable(name = "activities_participants_participants",
                joinColumns = arrayOf(JoinColumn(name = "user_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "activity_id"))
        )
        var activities: Set<Activity> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ActivityParticipant

        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

    fun toDTO(recursive: Boolean = true): ActivityParticipantDTO {
        val activities = if (recursive) {
            this.activities.map { it.toDTO(false) }.toSet()
        } else {
            emptySet()
        }

        return ActivityParticipantDTO(
                userId = userId,
                activities = activities
        )
    }
}