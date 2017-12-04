package dk.group11.calendarsystem

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableRetry
class CalendarsystemApplication

fun main(args: Array<String>) {
    SpringApplication.run(CalendarsystemApplication::class.java, *args)
}
