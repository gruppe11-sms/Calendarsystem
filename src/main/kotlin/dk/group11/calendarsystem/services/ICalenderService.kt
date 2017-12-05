package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.models.Activity
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.CalScale
import net.fortuna.ical4j.model.property.ProdId
import net.fortuna.ical4j.model.property.Uid
import net.fortuna.ical4j.model.property.Version
import org.springframework.stereotype.Service

@Service
class ICalenderService {
    fun convertToICalFormat(activities: Iterable<Activity>): String {

        val calender = Calendar()
        calender.properties += ProdId("-//SDU Group 11//SMS 1.0//EN")
        calender.properties += Version.VERSION_2_0
        calender.properties += CalScale.GREGORIAN

        activities.forEach {
            val event = VEvent(Date(it.startDate), Date(it.endDate), it.title)
            event.properties += Uid(it.id.toString())
            calender.components += event
        }

        return calender.toString()
    }
}