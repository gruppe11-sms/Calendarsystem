package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.exceptions.NotFoundException
import dk.group11.calendarsystem.models.Activity
import dk.group11.calendarsystem.models.ActivityParticipant
import dk.group11.calendarsystem.repositories.ActivityRepository
import dk.group11.calendarsystem.repositories.ParticipantRepository
import dk.group11.calendarsystem.security.SecurityService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ActivityService(
        private val activityRepository: ActivityRepository,
        private val securityService: SecurityService,
        private val participantRepository: ParticipantRepository
) {

    fun getActivity(id: Long): Activity {
        return activityRepository.findOne(id)
                ?: throw NotFoundException("Activity with id '$id' was not found")
    }

    fun getActivitiesForUser(userId: Long = -1): Iterable<Activity> {
        val id = if (userId == -1L) securityService.getId() else userId // Yes, you should be able to use default parameters instead of this crap, but NOPE
        val participant = getActivityParticipant(id)
        return participant.activities
    }

    fun getActivities(activityIds: List<Long>): Iterable<Activity> {
        return activityRepository.findAll(activityIds)
    }

    @Transactional
    fun createActivity(activity: Activity): Activity {
        activity.participants = activity.participants.map { getActivityParticipant(it.userId) }.toMutableSet()
        return activityRepository.save(activity)
    }

    private fun getActivityParticipant(userId: Long): ActivityParticipant {
        return participantRepository.findOne(userId) ?: participantRepository.save(
                ActivityParticipant(
                        userId = userId
                )
        )
    }

    @Transactional
    fun updateActivity(activity: Activity): Activity {

        val currentActivity = activityRepository.findOne(activity.id) ?: throw NotFoundException("Activity with id '${activity.id}' was not found ")

        currentActivity.startDate = activity.startDate
        currentActivity.endDate = activity.endDate
        currentActivity.title = activity.title

        currentActivity.participants = activity.participants.map { getActivityParticipant(it.userId) }.toMutableSet()

        return currentActivity
    }
}

