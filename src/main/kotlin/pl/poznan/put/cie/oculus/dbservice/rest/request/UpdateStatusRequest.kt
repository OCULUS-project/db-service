package pl.poznan.put.cie.oculus.dbservice.rest.request

import pl.poznan.put.cie.oculus.dbentries.jobs.JobStatus

data class UpdateStatusRequest (
        val id: String,
        val status: JobStatus
)