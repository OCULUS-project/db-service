package pl.poznan.put.cie.oculus.dbservice.rest.request

import pl.poznan.put.cie.oculus.dbentries.internal.Premise


data class UpdateConclusionsRequest (
        val id: String,
        val conclusions: List<Premise>
)