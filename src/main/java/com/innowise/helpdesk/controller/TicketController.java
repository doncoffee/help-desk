package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.TicketCreateEditDto;
import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.service.jwt.ResponseErrorValidationService;
import com.innowise.helpdesk.service.tickets.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;

@RestController
@RequestMapping(TICKET_CONTROLLER_PATH)
@CrossOrigin(origins = UI_URL)
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final ResponseErrorValidationService responseErrorValidationService;

    @GetMapping
    public ResponseEntity<List<TicketReadDto>> findAllTickets() {
        List<TicketReadDto> tickets = ticketService.getTicketsByUser();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping(FIND_MY_TICKETS_URL)
    public ResponseEntity<List<TicketReadDto>> findMyTickets() {
        List<TicketReadDto> tickets = ticketService.getMyTickets();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping(value = CREATE_TICKET_URL)
    public ResponseEntity<Object> createTicket(
            @RequestPart(REQUEST_PART_TICKET_DTO_NAME) @Valid
            TicketCreateEditDto ticketCreateEditDto,
            @RequestPart(value = REQUEST_PART_MULTIPART_NAME, required = false)
            MultipartFile multipartFile,
            BindingResult bindingResult) {
        ResponseEntity<Object> errors =
                responseErrorValidationService.getValidationError(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        return ResponseEntity.ok(ticketService
                .createTicket(ticketCreateEditDto, multipartFile));
    }

    @PutMapping(PATH_VARIABLE_URL)
    public ResponseEntity<Object> editTicket(@PathVariable Long id,
                                    @RequestPart(REQUEST_PART_TICKET_DTO_NAME) @Valid
                                    TicketCreateEditDto ticketCreateEditDto,
                                    @RequestPart(value = REQUEST_PART_MULTIPART_NAME, required = false)
                                                 MultipartFile multipartFile,
                                    BindingResult bindingResult) {
        ResponseEntity<Object> errors =
                responseErrorValidationService.getValidationError(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        TicketReadDto ticketReadDto =
                ticketService.editTicket(id, ticketCreateEditDto, multipartFile)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(ticketReadDto);
    }

    @PutMapping(SET_STATE_URL)
    public TicketReadDto setState(@PathVariable Long id,
                                  @RequestBody String stateString) {
        State state = State.valueOf(stateString);
        return ticketService.setTicketsState(id, state)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
