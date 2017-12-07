package dk.group11.calendarsystem.clients

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.core.response
import com.github.kittinunf.result.Result
import dk.group11.calendarsystem.exceptions.ForbiddenException
import dk.group11.calendarsystem.exceptions.InternalServiceError
import dk.group11.calendarsystem.models.CalenderExport
import dk.group11.calendarsystem.security.HEADER_STRING
import dk.group11.calendarsystem.security.SecurityService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Service
class RoleClient(
        private val securityService: SecurityService,
        private val roleConfigProperties: RoleConfigProperties
) {

    private object TokenType : TypeReference<CalenderExport>()
    private object TokenDeserializer : ResponseDeserializable<CalenderExport> {
        override fun deserialize(content: String) = ObjectMapper().readValue<CalenderExport>(content, TokenType)
    }


    fun generateToken(): CalenderExport {
        val (_, _, result) = Fuel.post("${roleConfigProperties.url}/api/tokens")
                .header(Pair(HEADER_STRING, securityService.getToken()))
                .response(TokenDeserializer)

        return when (result) {
            is Result.Success -> {
                result.value
            }
            else -> {
                throw InternalServiceError("Could not contact role system")
            }
        }
    }

    fun checkToken(token: String, userId: Long, tokenId: Long) {
        val (request, response, result) = Fuel.get("${roleConfigProperties.url}/api/tokens",
                listOf(
                        Pair("token", token),
                        Pair("userId", userId),
                        Pair("tokenId", tokenId)
                ))
                .responseString()

        when (result) {
            is Result.Success -> {

            }
            else -> throw ForbiddenException("Invalid token")
        }
    }

}


@Configuration
@ConfigurationProperties(prefix = "role")
class RoleConfigProperties(
        var url: String = "http://localhost:8084",
        var username: String = "system"
)