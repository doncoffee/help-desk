package com.innowise.helpdesk.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    /**
     * UserDto validation
     */
    public static final String BLANK_WARNING_MESSAGE = "Please fill out the required field.";
    public static final String EMAIL_VALIDATION_MESSAGE = "Please enter a valid email address.";
    public static final String EMAIL_MAX_CHARACTERS = "Email can have a maximum of 100 characters.";
    public static final String PASSWORD_CHARACTERS = "Password must be between 6 and 20 characters.";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@(yopmail.com|yopmail.net|yopmail.fr|yopmail.org)$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}])[\\p{L} \\d ~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]*$";
    public static final String PASSWORD_VALIDATION_MESSAGE = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.";
    public static final int MAX_EMAIL_CHARACTER_QUANTITY = 100;
    public static final int MAX_PASSWORD_CHARACTER_QUANTITY = 20;
    public static final int MIN_PASSWORD_CHARACTER_QUANTITY = 6;

    /**
     * table names from entities
     */
    public static final String ATTACHMENT_TABLE_NAME = "attachments";
    public static final String CATEGORY_TABLE_NAME = "categories";
    public static final String COMMENT_TABLE_NAME = "comments";
    public static final String FEEDBACK_TABLE_NAME = "feedbacks";
    public static final String HISTORY_TABLE_NAME = "history";
    public static final String TICKET_TABLE_NAME = "tickets";
    public static final String USER_TABLE_NAME = "users";

    /**
     * table column names
     */
    public static final String TICKET_ID = "ticket_id";
    public static final String USER_ID = "user_id";
    public static final String CREATED_ON = "created_on";
    public static final String DESIRED_RESOLUTION_DATE = "desired_resolution_date";
    public static final String ASSIGNEE_ID = "assignee_id";
    public static final String OWNER_ID = "owner_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String APPROVER_ID = "approver_id";

    /**
     * mapped by and join columns
     */
    public static final String CATEGORY = "category";
    public static final String TICKET = "ticket";
    public static final String USER = "user";
    public static final String MAPPED_BY_ASSIGNEE_ID = "assigneeId";
    public static final String MAPPED_BY_OWNER_ID = "ownerId";
    public static final String MAPPED_BY_APPROVER_ID = "approverId";

    /**
     * exception messages
     */
    public static final String FAILED_TO_RETRIEVE_USER = "Failed to retrieve user: ";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password.";

    /**
     * auth filter
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_HEADER = "Bearer ";

    /**
     * urls
     */
    public static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/api/v1/**",
            "/swagger-ui/index.html",
    };
    public static final String POST_MAPPING_SIGNIN = "/signin";
    public static final String POST_MAPPING_SIGNUP = "/signup";
    public static final String POST_MAPPING_LOGOUT = "/logout";
    public static final String AUTH_CONTROLLER_REQUEST_MAPPING = "/api/v1/auth";
    public static final String HTTP_METHOD_OPTIONS_URL = "/**";
    public static final String UI_URL = "http://localhost:4200";
    public static final String FIND_MY_TICKETS_URL = "/personal";
    public static final String TICKET_CONTROLLER_PATH = "/api/v1/tickets";

    /**
     * mapper messages
     */
    public static final String UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS = "Unsupported operation in current mapper class";

}
