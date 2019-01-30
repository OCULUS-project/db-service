package pl.poznan.put.cie.oculus.dbservice.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.cie.oculus.dbentries.jobs.Job
import pl.poznan.put.cie.oculus.dbentries.rules.Rule
import pl.poznan.put.cie.oculus.dbservice.app.DbService
import java.net.URI

@RestController()
@RequestMapping("/db/rules")
class RulesController (
        @Autowired
        private val dbService: DbService
) {

    @GetMapping("/get")
    @ResponseBody
    fun getById(@RequestParam("id") id: String): ResponseEntity<Rule> {
        val rule = dbService.getRuleById(id)
        return if (rule.isPresent) ResponseEntity.ok(rule.get())
        else ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

    @GetMapping("/all")
    @ResponseBody
    fun getAll(): ResponseEntity<List<Rule>> {
        val rules = dbService.getAllRules()
        return if (rules.isNotEmpty()) ResponseEntity.ok(rules)
        else ResponseEntity.status(HttpStatus.NO_CONTENT).body(rules)
    }

    @PostMapping("/add")
    @ResponseBody
    fun addRule (@RequestBody rule: Rule): ResponseEntity<Void> {
        val added = dbService.addRule(rule)
        return ResponseEntity.created(URI(added.id)).body(null)
    }
}