package pl.poznan.put.cie.oculus.dbservice.rest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.cie.oculus.dbentries.jobs.Job
import pl.poznan.put.cie.oculus.dbservice.app.DbService
import pl.poznan.put.cie.oculus.dbservice.rest.request.UpdateConclusionsRequest
import pl.poznan.put.cie.oculus.dbservice.rest.request.UpdateStatusRequest

@RestController()
@RequestMapping("/db/jobs")
class JobController (
        @Autowired
        private val dbService: DbService
) {
    @GetMapping("/all")
    @ResponseBody
    fun getAll(): ResponseEntity<List<Job>> {
        val jobs = dbService.getAllJobs()
        return if (jobs.isNotEmpty()) ResponseEntity.ok(jobs)
        else ResponseEntity.status(HttpStatus.NO_CONTENT).body(jobs)
    }

    @GetMapping("/new/first")
    @ResponseBody
    fun getFirstNew(): ResponseEntity<Job> {
        val job = dbService.getFirstNewJob()
        return if (job != null) ResponseEntity.ok(job)
        else ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

    @GetMapping("/new/all")
    @ResponseBody
    fun getAllNew(): ResponseEntity<List<Job>> {
        val newJobs = dbService.getAllNewJobs()
        return if (newJobs.isNotEmpty()) ResponseEntity.ok(newJobs)
        else ResponseEntity.status(HttpStatus.NO_CONTENT).body(newJobs)
    }

    @PutMapping("/update/job")
    @ResponseBody
    fun update(@RequestBody updatedJob: Job): Job {
        return updatedJob
    }

    @PutMapping("/update/conclusions")
    @ResponseBody
    fun updateConclusions(@RequestBody request: UpdateConclusionsRequest) {
        dbService.addJobConclusions(request.id, request.conclusions)
    }

    @PutMapping("/update/status")
    @ResponseBody
    fun updateStatus(@RequestBody request: UpdateStatusRequest) {
        dbService.setJobStatus(request.id, request.status)
    }
}