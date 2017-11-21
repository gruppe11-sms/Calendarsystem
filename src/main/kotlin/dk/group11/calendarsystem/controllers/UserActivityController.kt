package dk.group11.calendarsystem.controllers

import dk.group11.calendarsystem.clients.CalenderEntry
import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.services.ActivityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users/{userId}/activities")
class UserActivityController(private val activityService : ActivityService) {

    @GetMapping
    fun getUserActivities(@PathVariable userId: Long): List<CalenderEntry> {
        return activityService.getUserActivities(userId)
    }

}