package com.innowise.helpdesk.dto;

import com.innowise.helpdesk.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import static com.innowise.helpdesk.util.Constants.*;

@Value
@Builder
public class UserDto {

    Long id;
    @NotBlank(message = BLANK_WARNING_MESSAGE)
    String firstname;
    @NotBlank(message = BLANK_WARNING_MESSAGE)
    String lastname;
    Role role;
    @Email(message = EMAIL_VALIDATION_MESSAGE)
    @Size(max = MAX_EMAIL_CHARACTER_QUANTITY,
            message = EMAIL_MAX_CHARACTERS)
    @Pattern(regexp = EMAIL_REGEX,
            message = EMAIL_VALIDATION_MESSAGE)
    String email;
    @Pattern(regexp = PASSWORD_REGEX,
            message = PASSWORD_VALIDATION_MESSAGE)
    @Size(min = MIN_PASSWORD_CHARACTER_QUANTITY,
            max = MAX_PASSWORD_CHARACTER_QUANTITY,
            message = PASSWORD_CHARACTERS)
    String password;
}
