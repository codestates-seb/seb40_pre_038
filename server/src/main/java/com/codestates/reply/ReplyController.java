package com.codestates.reply;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/questions")
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyMapper mapper;

    public ReplyController(ReplyService replyService, ReplyMapper mapper) {
        this.replyService = replyService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postReply() {
        return null;
    }

    @PatchMapping
    public ResponseEntity patchReply() {
        return null;
    }

    @GetMapping
    public ResponseEntity getReplies() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity deleteReply() {
        return null;
    }
}
