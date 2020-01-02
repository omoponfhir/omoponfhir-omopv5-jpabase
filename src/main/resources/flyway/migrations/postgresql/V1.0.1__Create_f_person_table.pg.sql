CREATE TABLE f_person
(
    person_id          INTEGER NOT NULL,
    family_name        VARCHAR(255),
    given1_name        VARCHAR(255),
    given2_name        VARCHAR(255),
    prefix_name        VARCHAR(255),
    suffix_name        VARCHAR(255),
    preferred_language VARCHAR(255),
    ssn                VARCHAR(12),
    active             SMALLINT DEFAULT 1,
    contact_point1     VARCHAR(255),
    contact_point2     VARCHAR(255),
    contact_point3     VARCHAR(255),
    maritalstatus      VARCHAR(255)
);