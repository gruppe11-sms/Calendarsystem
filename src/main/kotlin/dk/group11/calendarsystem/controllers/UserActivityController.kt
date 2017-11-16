package dk.group11.calendarsystem.controllers

import dk.group11.calendarsystem.models.Activity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users/{id}/activities")
class UserActivityController() {

    @GetMapping
    fun getUserActivities(@PathVariable id: Long): List<Activity> {
        TODO()
    }
}