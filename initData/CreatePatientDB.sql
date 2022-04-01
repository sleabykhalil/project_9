CREATE SEQUENCE patient_id_seq;

CREATE TABLE patient (
                id BIGINT NOT NULL DEFAULT nextval('patient_id_seq'),
                first_name VARCHAR NOT NULL,
                last_name VARCHAR NOT NULL,
                birth_date DATE NOT NULL,
                gender VARCHAR ,
                address VARCHAR ,
                phone_number VARCHAR ,
                CONSTRAINT patient_pk PRIMARY KEY (id)
);


ALTER SEQUENCE patient_id_seq OWNED BY patient.id;
