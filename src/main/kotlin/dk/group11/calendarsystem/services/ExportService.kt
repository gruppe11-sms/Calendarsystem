package dk.group11.calendarsystem.services

import dk.group11.calendarsystem.clients.RoleClient
import dk.group11.calendarsystem.models.CalenderExport
import org.springframework.stereotype.Service

@Service
class ExportService(private val roleClient: RoleClient) {

    fun generateCalenderExport(): CalenderExport {
        return roleClient.generateToken()
    }

    fun checkToken(userId: Long, tokenId: Long, token: String) {
        roleClient.checkToken(token, userId, tokenId)
    }

}