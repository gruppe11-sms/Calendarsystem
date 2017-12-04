package dk.group11.calendarsystem.repositories

import dk.group11.calendarsystem.models.Activity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository : CrudRepository<Activity, Long>