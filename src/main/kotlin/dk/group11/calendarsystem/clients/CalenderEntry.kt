package dk.group11.calendarsystem.clients

import dk.group11.calendarsystem.models.Activity

data class CalenderEntry(
        val activity: Activity,
        val activityDetail: IActivity,
        val type: ActivityType
)