--liquibase formatted sql

--changeset doncoffee:insert-values-into-users-table
INSERT INTO tickets (name, description, created_on, desired_resolution_date, urgency, state, assignee_id, owner_id, approver_id)
VALUES ('Task_1', 'for fun', CURRENT_DATE, CURRENT_DATE, 'CRITICAL', 'DRAFT', 1, null, null),
       ('Task_2', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'NEW', null, 2, null),
       ('Task_3', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'APPROVED', 1, null, 3),
       ('Task_4', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'DECLINED', null, 4, null),
       ('Task_5', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'IN_PROGRESS', null, 5, null),
       ('Task_6', 'for fun', CURRENT_DATE, CURRENT_DATE, 'CRITICAL', 'DONE', 6, null, null),
       ('Task_7', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'CANCELLED', 3, null, 4),
       ('Task_8', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'IN_PROGRESS', 4, 5, null),
       ('Task_9', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'APPROVED', null, 2, null),
       ('Task_11', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'IN_PROGRESS', 2, 3, 6),
       ('Task_12', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'NEW', 1, 4, 3),
       ('Task_13', 'for fun', CURRENT_DATE, CURRENT_DATE, 'CRITICAL', 'IN_PROGRESS', 5, 2, 2),
       ('Task_14', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'APPROVED', 6, 1, 2),
       ('Task_15', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'IN_PROGRESS', 4, 1, 5),
       ('Task_16', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'APPROVED', 2, 3, 4),
       ('Task_17', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'IN_PROGRESS', 4, 6, 1),
       ('Task_18', 'for fun', CURRENT_DATE, CURRENT_DATE, 'CRITICAL', 'IN_PROGRESS', 3, 5, 4),
       ('Task_19', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'NEW', 1, 6, 5),
       ('Task_20', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'IN_PROGRESS', 1, 2, 5),
       ('Task_21', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'APPROVED', 6, 3, 2),
       ('Task_22', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'NEW', 1, 3, 4),
       ('Task_23', 'for fun', CURRENT_DATE, CURRENT_DATE, 'CRITICAL', 'IN_PROGRESS', 1, 1, 3),
       ('Task_24', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'NEW', 2, 4, 5),
       ('Task_25', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'IN_PROGRESS', 2, 4, 6),
       ('Task_26', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'NEW', 5, 6, 2),
       ('Task_27', 'for fun', CURRENT_DATE, CURRENT_DATE, 'LOW', 'APPROVED', 5, 2, 6),
       ('Task_28', 'for fun', CURRENT_DATE, CURRENT_DATE, 'HIGH', 'IN_PROGRESS', 4, 1, 6),
       ('Task_29', 'for fun', CURRENT_DATE, CURRENT_DATE, 'CRITICAL', 'DONE', 4, 2, 1),
       ('Task_30', 'for fun', CURRENT_DATE, CURRENT_DATE, 'AVERAGE', 'DONE', 5, 6, 2);
