# omoponfhir-omopv5-jpabase

<!-- TODO: Change this URL when merged -->
[![Build Status](https://travis-ci.org/PheMA/omoponfhir-omopv5-jpabase.svg?branch=flyway)](https://travis-ci.org/PheMA/omoponfhir-omopv5-jpabase)

Java library containing Hibernate JPA entities used by OMOP on FHIR.


## Schema Preparation

At least one table and one view need to be created to support mapping the OMOP CDM to FHIR. First, the `f_person` table
must be created to contain additional demographic information used by FHIR, but not typically stored in the OMOP CDM.
Second, since both the CDM `measurement` and `observation` tables map to the FHIR <kbd>Observation</kbd> resource, we
create a view called `f_observation_view`, which is a union of these two tables.

The PostgreSQL scripts for creating these two tables, as well as an index on `f_person` can be found in
[`src/main/resources/flyway/migrations/postgresql`](src/main/resources/flyway/migrations/postgresql).

### Automatic Schema Creation

To automatically create the necessary tables, or apply any updates implemented since you last ran the scripts, do the
following.

First, clone this repository:

```
git clone https://github.com/omoponfhir/omoponfhir-omopv5-jpabase.git
```

Then, use Maven and Flyway to apply the scripts for you:

```sh
mvn flyway:migrate -Ddatabase.url='jdbc:postgresql://localhost:5432/postgres?currentSchema=cdm' -Ddatabase.user=postgres -Ddatabase.password=postgres
```

Adjust the parameters as required. Importantly, ensure the correct schema is selected (`cdm` in the above example).