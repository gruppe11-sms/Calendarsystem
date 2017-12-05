package dk.group11.calendarsystem.controllers

import dk.group11.calendarsystem.services.ActivityService
import dk.group11.calendarsystem.services.ICalenderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExportController(private val activityService: ActivityService, private val iCalenderService: ICalenderService) {

    @RequestMapping(
//            consumes = arrayOf("*/*"),
            produces = arrayOf("text/calendar"),
            value = "/api/export",
            method = arrayOf(RequestMethod.GET)
    )
    fun getExport(@RequestParam("userId") id: String): String {
        println("Called")
        return iCalenderService.convertToICalFormat(activityService.getActivities())
    }
}