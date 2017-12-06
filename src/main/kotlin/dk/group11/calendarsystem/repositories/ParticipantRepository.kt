package dk.group11.calendarsystem.repositories

import dk.group11.calendarsystem.models.ActivityParticipant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipantRepository : CrudRepository<ActivityParticipant, Long>