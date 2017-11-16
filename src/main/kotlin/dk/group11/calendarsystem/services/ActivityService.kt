package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.repository.ActivityRepository

class ActivityService(private val activityRepository: ActivityRepository){
    fun getActivity(id: Long): Activity {
       return activityRepository.findOne(id)
    }

    fun getActivities(): List<Activity> {
        return activityRepository.findAll().toList()
    }

    fun createActivity(activity: Activity): Activity {
        return activityRepository.save(activity)
    }

    fun getUserActivities(id: Long): List<Activity>{
        TODO()
    }
}