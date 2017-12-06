package dk.group11.calendarsystem.models

data class CalenderExport(
        var userId: Long = 0,
        var token: String = "",
        var tokenId: Long = 0
)