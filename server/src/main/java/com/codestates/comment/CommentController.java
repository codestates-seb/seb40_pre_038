package com.codestates.comment;

import com.codestates.dto.SingleResponseDto;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post commentPost,
                                    @RequestParam CommentType commentType,
                                    @RequestParam long postId) {
        Comment comment = mapper.commentPostToComment(commentPost);
        Comment createComment = commentService.createComment(comment, commentType, postId);
        CommentDto.Response response = mapper.commentToCommentResponse(createComment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED
        );
    }

    @PatchMapping("/{comment_id}")
    public ResponseEntity patchComment(@Valid @RequestBody CommentDto.Patch commentPatch,
                                     @PathVariable("comment_id") @Positive long commentId) {
        Comment comment = mapper.commentPatchToComment(commentPatch);
        Comment updateComment = commentService.updateComment(comment, commentId);
        CommentDto.Response response = mapper.commentToCommentResponse(updateComment);
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

//    @GetMapping
//    public ResponseEntity getReplies() {
//        return null;
//    }

    @DeleteMapping("/{Comment_id}")
    public ResponseEntity deleteComment(@PathVariable("Comment_id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
