package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.clients.*
import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.repositories.ActivityRepository

class ActivityService(private val activityRepository: ActivityRepository, private val courseSystemClient: CourseSystemClient) {
    fun getActivity(id: Long): Activity {
        return activityRepository.findOne(id)
    }

    fun getActivities(): List<Activity> {
        return activityRepository.findAll().toList()
    }

    fun createActivity(activity: Activity): Activity {
        return activityRepository.save(activity)
    }

    fun getUserActivities(userId: Long): List<CalenderEntry> {
        val calenderEntries = ArrayList<CalenderEntry>()

        courseSystemClient.getLessonsByUserId(userId).forEach { lesson: Lesson ->
            calenderEntries.add(CalenderEntry(
                    activity = activityRepository.findOne(lesson.activityId),
                    activityDetail = lesson,
                    type = ActivityType.Lesson
            ))
        }

        courseSystemClient.getAssignmentsByUserId(userId).forEach { assignment: Assignment->
            calenderEntries.add(CalenderEntry(
                    activity = activityRepository.findOne(assignment.activityId),
                    activityDetail = assignment,
                    type = ActivityType.Assignment
            ))
        }

        courseSystemClient.getEvents().forEach { event : Event ->
            calenderEntries.add(CalenderEntry(
                    activity = activityRepository.findOne(event.activityId),
                    activityDetail = event,
                    type = ActivityType.Event
            ))
        }

        return calenderEntries


    }
}