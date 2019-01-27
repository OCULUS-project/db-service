package pl.poznan.put.cie.oculus.dbservice.rest.request

import pl.poznan.put.cie.oculus.dbentries.internal.Fact
import pl.poznan.put.cie.oculus.dbentries.internal.Premise
import pl.poznan.put.cie.oculus.dbentries.jobs.JobStatus

data class AddJobRequest (
        val status: JobStatus,
        val owner: String,
        val patient: String,
        val facts: List<Fact>,
        val conclusions: List<Premise>
)