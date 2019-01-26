package pl.poznan.put.cie.oculus.dbservice.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.cie.oculus.dbentries.rules.Rule
import pl.poznan.put.cie.oculus.dbservice.app.DbService

@RestController()
@RequestMapping("/db/rules")
class RulesController (
        @Autowired
        private val dbService: DbService
) {
    @RequestMapping("/all", method = [RequestMethod.GET])
    @ResponseBody
    fun getAll(): ResponseEntity<List<Rule>> {
        val rules = dbService.getAllRules()
        return if (rules.isNotEmpty()) ResponseEntity.ok(rules)
        else ResponseEntity.status(HttpStatus.NO_CONTENT).body(rules)
    }
}