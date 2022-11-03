package com.codestates.comment;

import com.codestates.comment.entity.Comment;
import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import com.codestates.question.QuestionRepository;
import com.codestates.question.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, QuestionService questionService,
                             QuestionRepository questionRepository, CommentMapper mapper) {
        this.commentService = commentService;
        this.questionService = questionService;
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_USER")
    @PostMapping("/questions/{question_id}/comments/add")
    public ResponseEntity postQuestionComment(@Valid @RequestBody CommentDto.Post commentPost,
//                                    @RequestParam CommentType commentType,
                                    @PathVariable("question_id") @Positive long postId) {
        Comment comment = mapper.commentPostToComment(commentPost);
        Comment createComment = commentService.createQuestionComment(comment, postId, commentPost.getUserId());
        CommentDto.Response response = mapper.commentToCommentResponse(createComment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED
        );
    }

    @Secured("ROLE_USER")
    @PostMapping("/answers/{answer_id}/comments/add")
    public ResponseEntity postAnswerComment(@Valid @RequestBody CommentDto.Post commentPost,
//                                    @RequestParam CommentType commentType,
//                                      @PathVariable("question_id") @Positive long postId,
                                      @PathVariable("answer_id") @Positive long answerId) {
        Comment comment = mapper.commentPostToComment(commentPost);
        Comment createComment = commentService.createAnswerComment(comment, answerId, commentPost.getUserId());
        CommentDto.Response response = mapper.commentToCommentResponse(createComment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED
        );
    }

    @Secured("ROLE_USER")
    @PatchMapping("/comments/{comment_id}/edit")
    public ResponseEntity patchComment(@Valid @RequestBody CommentDto.Patch commentPatch,
                                     @PathVariable("comment_id") @Positive long commentId) {
        Comment comment = mapper.commentPatchToComment(commentPatch);
        Comment updateComment = commentService.updateComment(comment, commentId, commentPatch.getUserId());
        CommentDto.Response response = mapper.commentToCommentResponse(updateComment);
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @GetMapping("/comments")// 댓글 전부 조회하기
    public ResponseEntity getComments(@Positive @RequestParam int page,
                                       @Positive @RequestParam(required = false, defaultValue = "15") int size) {
        Page<Comment> pageQuestions = commentService.findComments(page - 1, size);
        List<Comment> comments = pageQuestions.getContent();
        List<CommentDto.Response> responses = mapper.commentsToCommentResponses(comments);

        return new ResponseEntity<>(
                new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK
        );
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/comments/{comment_id}/delete")
    public ResponseEntity deleteComment(@PathVariable("comment_id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
