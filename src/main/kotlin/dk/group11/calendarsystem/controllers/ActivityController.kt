package dk.group11.calendarsystem.controllers

import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.models.ActivityDTO
import dk.group11.calendarsystem.services.ActivityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activities")
class ActivityController(private val activityService: ActivityService) {

    @GetMapping
    fun getActivities(): Iterable<ActivityDTO> {
        return activityService.getActivitiesForUser().map { it.toDTO(true) }
    }

    @GetMapping("/{id}")
    fun getActivity(@PathVariable id: Long): ActivityDTO {
        return activityService.getActivity(id).toDTO(true)
    }

    @GetMapping("/ids")
    fun getActivity(@RequestParam("ids") ids: String): Iterable<ActivityDTO> {
        val activityIds = ids.split(",").mapNotNull { it.toLongOrNull() }
        return activityService.getActivities(activityIds).map { it.toDTO(true) }
    }

    @PostMapping
    fun createActivity(@RequestBody activity: Activity): ActivityDTO {
        return activityService.createActivity(activity).toDTO(true)
    }

    @PutMapping
    fun updateActivity(activity: Activity): ActivityDTO {
        return activityService.updateActivity(activity).toDTO(true)
    }

}