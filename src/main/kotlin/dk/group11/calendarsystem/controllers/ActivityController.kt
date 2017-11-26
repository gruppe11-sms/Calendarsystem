package dk.group11.calendarsystem.controllers

import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.services.ActivityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activities")
class ActivityController(private val activityService: ActivityService) {

    @GetMapping
    fun getActivities(): List<Activity>{
        return activityService.getActivities()
    }

    @GetMapping("/{id]")
    fun getActivity(@PathVariable id: Long): Activity{
        return activityService.getActivity(id)
    }

    @PostMapping
    fun createActivity(@RequestBody activity: Activity): Activity {
        return activityService.createActivity(activity)
    }

}