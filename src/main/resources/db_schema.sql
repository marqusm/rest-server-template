-- Create tables
CREATE SEQUENCE id_seq;

CREATE TABLE student (
  student_id            BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('id_seq'),
  name                  VARCHAR            NOT NULL,
  meta_active           BOOLEAN            NOT NULL,
  meta_creation_date    TIMESTAMP          NOT NULL,
  meta_modified_date    TIMESTAMP          NOT NULL,
  meta_creation_user_id BIGINT             NOT NULL,
  meta_modified_user_id BIGINT             NOT NULL
);
