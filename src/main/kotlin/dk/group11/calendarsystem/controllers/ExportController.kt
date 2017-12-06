package dk.group11.calendarsystem.controllers

import dk.group11.calendarsystem.models.CalenderExport
import dk.group11.calendarsystem.services.ActivityService
import dk.group11.calendarsystem.services.ExportService
import dk.group11.calendarsystem.services.ICalenderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExportController(
        private val activityService: ActivityService,
        private val iCalenderService: ICalenderService,
        private val exportService: ExportService
) {

    @RequestMapping(
//            consumes = arrayOf("*/*"),
            produces = arrayOf("text/calendar"),
            value = "/api/export",
            method = arrayOf(RequestMethod.GET)
    )
    fun getExport(
            @RequestParam("userId") id: Long,
            @RequestParam("tokenId") tokenId: Long,
            @RequestParam("token") token: String
    ): String {

        exportService.checkToken(id, tokenId, token)

        return iCalenderService.convertToICalFormat(activityService.getActivitiesForUser(id))
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun createExportUrl(): CalenderExport {
        return exportService.generateCalenderExport()
    }
}