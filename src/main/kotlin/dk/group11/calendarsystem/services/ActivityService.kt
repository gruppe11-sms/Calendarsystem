package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.repositories.ActivityRepository
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository: ActivityRepository) {

    fun getActivity(id: Long): Activity {
        return activityRepository.findOne(id)
    }

    fun getActivities(): Iterable<Activity> {
        return activityRepository.findAll().toList()
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