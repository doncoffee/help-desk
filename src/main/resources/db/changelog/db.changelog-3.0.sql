--liquibase formatted sql

--changeset doncoffee:insert-values-into-users-table
INSERT INTO users (email, password, role, firstname, lastname)
VALUES ('user1_mogilev@yopmail.com',
        '{bcrypt}$2a$12$aC89r4l/10ds9nq6URLa8udhM5oRCn7P3OGD/hheE1U.PpJ6zdWOe',
        'EMPLOYEE', 'John', 'Doe'),
       ('user2_mogilev@yopmail.com',
        '{bcrypt}$2a$12$aC89r4l/10ds9nq6URLa8udhM5oRCn7P3OGD/hheE1U.PpJ6zdWOe',
        'EMPLOYEE', 'John', 'Doe'),
       ('manager1_mogilev@yopmail.com',
        '{bcrypt}$2a$12$aC89r4l/10ds9nq6URLa8udhM5oRCn7P3OGD/hheE1U.PpJ6zdWOe',
        'MANAGER', 'John', 'Doe'),
       ('manager2_mogilev@yopmail.com',
        '{bcrypt}$2a$12$aC89r4l/10ds9nq6URLa8udhM5oRCn7P3OGD/hheE1U.PpJ6zdWOe',
        'MANAGER', 'John', 'Doe'),
       ('engineer1_mogilev@yopmail.com',
        '{bcrypt}$2a$12$aC89r4l/10ds9nq6URLa8udhM5oRCn7P3OGD/hheE1U.PpJ6zdWOe',
        'ENGINEER', 'John', 'Doe'),
       ('engineer2_mogilev@yopmail.com',
        '{bcrypt}$2a$12$aC89r4l/10ds9nq6URLa8udhM5oRCn7P3OGD/hheE1U.PpJ6zdWOe',
        'ENGINEER', 'John', 'Doe');