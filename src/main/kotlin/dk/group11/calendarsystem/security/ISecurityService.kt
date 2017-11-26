package dk.group11.calendarsystem.security


interface ISecurityService {

    fun getId(): Long

    fun getToken(): String
}