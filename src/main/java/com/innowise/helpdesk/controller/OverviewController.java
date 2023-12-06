package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.*;
import com.innowise.helpdesk.service.jwt.ResponseErrorValidationService;
import com.innowise.helpdesk.service.tickets.AttachmentService;
import com.innowise.helpdesk.service.tickets.FeedbackService;
import com.innowise.helpdesk.service.tickets.HistoryService;
import com.innowise.helpdesk.service.tickets.OverviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping(OVERVIEW_CONTROLLER_REQUEST_MAPPING_URL)
@CrossOrigin(origins = UI_URL)
@RequiredArgsConstructor
public class OverviewController {

    private final OverviewService overviewService;
    private final HistoryService historyService;
    private final FeedbackService feedbackService;
    private final AttachmentService attachmentService;
    private final ResponseErrorValidationService responseErrorValidationService;

    @GetMapping(PATH_VARIABLE_URL)
    public TicketOverviewDto findById(@PathVariable Long id) {
        return overviewService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(FIND_TICKET_HISTORY_BY_TICKET_ID_URL)
    public ResponseEntity<List<HistoryDto>> findTicketHistory(@PathVariable Long id) {
        List<HistoryDto> history = historyService.getEntireHistoryByTicketId(id);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value = DOWNLOAD_FILE_URL)
    public ResponseEntity<AttachmentDto> downloadFile(@PathVariable(PATH_VARIABLE_URL_NAME) Long ticketId) {
        return attachmentService.findAttachedFile(ticketId)
                .map(content -> ResponseEntity.ok()
                        .body(content))
                .orElseGet(notFound()::build);
    }

    @PostMapping(LEAVE_FEEDBACK_URL)
    public ResponseEntity<Object> leaveFeedback(@PathVariable(PATH_VARIABLE_URL_NAME) Long ticketId,
                                                @RequestBody @Valid FeedbackDto feedbackDto,
                                                BindingResult bindingResult) {
        ResponseEntity<Object> errors =
                responseErrorValidationService.getValidationError(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        return ResponseEntity.ok(feedbackService
                .createFeedback(ticketId, feedbackDto));
    }
}
