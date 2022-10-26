package com.codestates.reply;

import com.codestates.dto.SingleResponseDto;
import com.codestates.reply.entity.Reply;
import com.codestates.reply.entity.ReplyType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyMapper mapper;

    public ReplyController(ReplyService replyService, ReplyMapper mapper) {
        this.replyService = replyService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postReply(@Valid @RequestBody ReplyDto.Post replyPost,
                                    @RequestParam ReplyType replyType,
                                    @RequestParam long postId) {
        Reply reply = mapper.replyPostToReply(replyPost);
        Reply createReply = replyService.createReply(reply, replyType, postId);
        ReplyDto.Response response = mapper.replyToReplyResponse(createReply);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED
        );
    }

    @PatchMapping("/{reply_id")
    public ResponseEntity patchReply(@Valid @RequestBody ReplyDto.Patch replyPatch,
                                     @PathVariable("reply_id") @Positive long replyId) {
        Reply reply = mapper.replyPatchToReply(replyPatch);
        Reply updateReply = replyService.updateReply(reply, replyId);
        ReplyDto.Response response = mapper.replyToReplyResponse(updateReply);
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

//    @GetMapping
//    public ResponseEntity getReplies() {
//        return null;
//    }

    @DeleteMapping("/{reply_id}")
    public ResponseEntity deleteReply(@PathVariable("reply_id") @Positive long replyId) {
        replyService.deleteReply(replyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
