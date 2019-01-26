package pl.poznan.put.cie.oculus.dbservice.app

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Component
import pl.poznan.put.cie.oculus.dbentries.Premise
import pl.poznan.put.cie.oculus.dbentries.jobs.Job
import pl.poznan.put.cie.oculus.dbentries.jobs.JobStatus
import pl.poznan.put.cie.oculus.dbentries.rules.Rule
import pl.poznan.put.cie.oculus.dbservice.rest.JobController

@Component
class DbService (
        @Autowired
        private val jobRepository: JobRepository,
        @Autowired
        private val rulesRepository: RuleRepository
) {

    private val logger = LoggerFactory.getLogger(DbService::class.java)

    // jobs
    fun getAllJobs(): List<Job> = jobRepository.findAll()
    fun getAllNewJobs(): List<Job> = jobRepository.findAllByStatus(JobStatus.NEW)
    fun getFirstNewJob(): Job? = getAllNewJobs().firstOrNull()

    fun addJobConclusions(id: String, conclusions: List<Premise>) {
        val job = jobRepository.findById(id).orElseThrow { throw ResourceNotFoundException("no job with given id") }
        job.conclusions = conclusions
        jobRepository.save(job)
        logger.trace("Added ${conclusions.size} conclusions to job ${job.id}")
    }

    fun setJobStatus(id: String, status: JobStatus) {
        val job = jobRepository.findById(id).orElseThrow { throw ResourceNotFoundException("no job with given id") }
        job.status = status
        jobRepository.save(job)
        logger.trace("Job ${job.id} changed to $status")
    }

    // rules
    fun getAllRules(): List<Rule> = rulesRepository.findAll()
}