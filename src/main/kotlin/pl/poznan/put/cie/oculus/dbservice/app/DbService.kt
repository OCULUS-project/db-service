package pl.poznan.put.cie.oculus.dbservice.app

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Component
import pl.poznan.put.cie.oculus.dbentries.internal.Premise
import pl.poznan.put.cie.oculus.dbentries.jobs.Job
import pl.poznan.put.cie.oculus.dbentries.jobs.JobStatus
import pl.poznan.put.cie.oculus.dbentries.rules.Rule
import java.util.*

@Component
class DbService (
        @Autowired
        private val jobRepository: JobRepository,
        @Autowired
        private val rulesRepository: RuleRepository
) {

    private val logger = LoggerFactory.getLogger(DbService::class.java)

    // jobs
    fun getJobById(id: String): Optional<Job> = jobRepository.findById(id)
    fun getAllJobs(): List<Job> = jobRepository.findAll()
    fun getAllNewJobs(): List<Job> = jobRepository.findAllByStatus(JobStatus.NEW)
    fun getFirstNewJob(): Job? = getAllNewJobs().firstOrNull()

    fun addJob(job: Job): Job {
        val added = jobRepository.insert(job)
        logger.info("Added new Job with id ${added.id}")
        return added
    }

    fun addJobConclusions(id: String, conclusions: List<Premise>) {
        val job = jobRepository.findById(id).orElseThrow { throw ResourceNotFoundException("no job with given id") }
        job.conclusions = conclusions
        jobRepository.save(job)
        logger.info("Added ${conclusions.size} conclusions to job ${job.id}")
    }

    fun setJobStatus(id: String, status: JobStatus) {
        val job = jobRepository.findById(id).orElseThrow { throw ResourceNotFoundException("no job with given id") }
        job.status = status
        jobRepository.save(job)
        logger.info("Job ${job.id} changed to $status")
    }

    // rules
    fun getAllRules(): List<Rule> = rulesRepository.findAll()
}