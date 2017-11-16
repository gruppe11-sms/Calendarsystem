package dk.group11.calendarsystem.repository

import dk.group11.calendarsystem.models.Activity
import org.springframework.data.repository.CrudRepository

interface ActivityRepository : CrudRepository <Activity,Long> {
}