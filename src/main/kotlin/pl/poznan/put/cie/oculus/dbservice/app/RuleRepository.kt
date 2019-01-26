package pl.poznan.put.cie.oculus.dbservice.app

import org.springframework.data.mongodb.repository.MongoRepository
import pl.poznan.put.cie.oculus.dbentries.rules.Rule

interface RuleRepository : MongoRepository<Rule, String>