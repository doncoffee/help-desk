package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.jwt.JwtAuthenticationResponse;
import com.innowise.helpdesk.service.AuthenticationService;
import com.innowise.helpdesk.service.ResponseErrorValidationService;
import com.innowise.helpdesk.service.TokenBlackList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.innowise.helpdesk.util.Constants.*;

@RestController
@RequestMapping(AUTH_CONTROLLER_REQUEST_MAPPING)
@CrossOrigin(origins = UI_URL)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ResponseErrorValidationService responseErrorValidationService;
    private final TokenBlackList tokenBlackList;

    @PostMapping(POST_MAPPING_SIGNUP)
    public ResponseEntity<Object> signUp(@RequestBody @Valid UserDto userDto,
                                                            BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidationService.getValidationError(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        return ResponseEntity.ok(authenticationService.signUp(userDto));
    }

    @PostMapping(POST_MAPPING_SIGNIN)
    public ResponseEntity<Object> signIn(@RequestBody UserDto userDto) {
        try {
            JwtAuthenticationResponse response = authenticationService.signIn(userDto);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(INVALID_EMAIL_OR_PASSWORD);
        }
    }

    @PostMapping(POST_MAPPING_LOGOUT)
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = tokenBlackList.extractTokenFromRequest(request);
        tokenBlackList.addToBlacklist(token);

        return ResponseEntity.ok().build();
    }
}
