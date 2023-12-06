package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.CommentDto;
import com.innowise.helpdesk.service.tickets.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;

@RestController
@RequestMapping(COMMENT_CONTROLLER_REQUEST_MAPPING_URL)
@CrossOrigin(origins = UI_URL)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(FIND_TICKET_BY_ID_URL)
    public ResponseEntity<List<CommentDto>> findTicketComments(@PathVariable Long id) {
        List<CommentDto> comments = commentService.getAllCommentsByTicketId(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping(CREATE_COMMENT_URL)
    public ResponseEntity<CommentDto> createComment(@PathVariable(PATH_VARIABLE_URL_NAME) Long ticketId,
                                               @RequestBody String text) {
        return ResponseEntity.ok(commentService
                .createComment(ticketId, text));
    }
}
