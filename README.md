#db-service
Facade of the database storing jobs and rules. Allows basic CRUD operations. Should be used always, when accessing jobs database.

Data types used here are stored in _db-entries_ repository.

## build and run
`./gradlew build run` or `/.gradlew build` and  `java -jar build\libs\jobs-db-service-0.0.1-SNAPSHOT.jar`

## usage 
This sections demonstrates endpoints and requests.

> Data types used here are stored in _db-entries_ repository.

### jobs
#### get all jobs
__url:__ `../db/jobs/all`, __method:__ `GET`

| case | response | code |
|---|---|---|
| found rules | json array of `Job` | 200 OK |
| no rules in db | `[]` | 204 No Content |

#### get first new job
__url:__ `../db/jobs/new/first`, __method:__ `GET`

| case | response | code |
|---|---|---|
| found rules | json `Job` | 200 OK |
| no rules in db | _empty_ | 204 No Content |

#### get first all new jobs
__url:__ `../db/jobs/new/all`, __method:__ `GET`

| case | response | code |
|---|---|---|
| found rules | json array of `Job` | 200 OK |
| no rules in db | `[]` | 204 No Content |

#### update job _in dev_
__url:__ `../db/jobs/update/job`, __method:__ `PUT`, __body:__ json of `Job`

| case | response | code |
|---|---|---|
| success | _empty_ | 200 OK |
| fail | _error json_ | 500 Internal Server Error |

#### update conclusions of job
__url:__ `../db/jobs/update/conclusions`, __method:__ `PUT`, __body:__
```json
{
  "id": "idOfJobToUpdate",
  "conclusions": [/*array of conclusions*/]
}
```
| case | response | code |
|---|---|---|
| success | _empty_ | 200 OK |
| fail | _error json_ | 500 Internal Server Error |

#### update job status
__url:__ `../db/jobs/update/status`, __method:__ `PUT`, __body:__
```json
{
  "id": "idOfJobToUpdate",
  "status": "new JobStatus"
}
```
| case | response | code |
|---|---|---|
| success | _empty_ | 200 OK |
| fail | _error json_ | 500 Internal Server Error |



### rules

#### get all rules
__url:__ `../db/rules/all`, __method:__ `GET`

| case | response | code |
|---|---|---|
| found rules | json array of `Rule` | 200 OK |
| no rules in db | `[]` | 204 No Content |
