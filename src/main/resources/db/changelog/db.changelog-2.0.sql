--liquibase formatted sql

--changeset doncoffee:insert-values-into-categories-table
INSERT INTO categories (name)
VALUES ('Application & Services'),
       ('Benefits & Paper Work'),
       ('Hardware & Software'),
       ('People Management'),
       ('Security & Access'),
       ('Workplaces & Facilities');

