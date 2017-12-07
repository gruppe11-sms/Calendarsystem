package dk.group11.calendarsystem.repositories

import dk.group11.calendarsystem.models.ActivityParticipant
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantRepository : JpaRepository<ActivityParticipant, Long>