package pl.poznan.put.cie.oculus.dbservice.app

import org.springframework.data.mongodb.repository.MongoRepository
import pl.poznan.put.cie.oculus.dbentries.jobs.Job
import pl.poznan.put.cie.oculus.dbentries.jobs.JobStatus

interface JobRepository : MongoRepository<Job, String> {
    fun findAllByStatus(status: JobStatus): List<Job>
}