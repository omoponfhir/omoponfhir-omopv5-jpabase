CREATE TABLE id_mapping
(
    id               INTEGER      NOT NULL,
    fhir_id          INTEGER      NULL,
    omop_id          INTEGER      NULL,
    related_resource varchar(255) NULL,
    CONSTRAINT id_mapping_pkey PRIMARY KEY (id)
);