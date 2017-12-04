package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.exceptions.BadRequestException
import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.repositories.ActivityRepository
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository: ActivityRepository) {

    fun getActivity(id: Long): Activity {
        return activityRepository.findOne(id)
                ?: throw BadRequestException("Activity with id: $id not found")
    }

    fun getActivities(): Iterable<Activity> {
        return activityRepository.findAll()
    }

    fun getActivities(userIds: List<Long>): Iterable<Activity> {
        return activityRepository.findAll(userIds)
    }

    fun createActivity(activity: Activity): Activity {
        return activityRepository.save(activity)
    }

    fun updateActivity(activity: Activity) {
        activityRepository.save(activity)
    }
}

