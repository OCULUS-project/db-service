package pl.poznan.put.cie.oculus.dbservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping

@SpringBootApplication
@RequestMapping("/db")
class DbServiceApplication

fun main(args: Array<String>) {
	runApplication<DbServiceApplication>(*args)
}
