package com.codestates.comment;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.answer.service.AnswerService;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;

import com.codestates.user.entity.User;
import com.codestates.user.repository.UserRepository;
import com.codestates.user.service.UserService;
import com.codestates.question.Question;
import com.codestates.question.QuestionRepository;
import com.codestates.question.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    private final UserService userService;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, QuestionService questionService,
                          AnswerService answerService, QuestionRepository questionRepository,
                          AnswerRepository answerRepository, UserService userService,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Comment createQuestionComment(Comment comment, long questionId) {
        Question question = questionService.findQuestion(questionId);
        //User findUser = userService.findVerifiedUser(userId);
        comment.setQuestion(question);
        comment.setUser(userService.getLoginUser()); // 로그인 유저로 작성
        comment.setCommentType(CommentType.QUESTION);
        question.getComments().add(comment);
        questionRepository.save(question);

        return commentRepository.save(comment);
    }

    public Comment createAnswerComment(Comment comment, long answerId) {
//        Question question = questionService.findQuestion(questionId);
        Answer answer = answerService.findAnswer(answerId);
        //User findUser = userService.findVerifiedUser(userId);

        comment.setQuestion(answer.getQuestion());
        comment.setAnswer(answer);
        comment.setUser(userService.getLoginUser()); // 로그인 유저로 작성
        comment.setCommentType(CommentType.ANSWER);
        answer.getComments().add(comment);
        answerRepository.save(answer);

        return commentRepository.save(comment);
    }
//    public Comment createComment(Comment comment, CommentType commentType, long postId) {
//        if (commentType == CommentType.QUESTION) {
//            long commentId = comment.getPostId();
//            Question question = questionService.findQuestion(postId);
//            comment.setCommentType(CommentType.QUESTION);
//            question.getComments().add(comment);
//            questionRepository.save(question);
//        }
//        if (commentType == CommentType.ANSWER) {
//            Answer answer = answerService.findAnswer(postId);
//            comment.setCommentType(CommentType.ANSWER);
//            answer.getComments().add(comment);
//            answerRepository.save(answer);
//        }
//            return commentRepository.save(comment);
//    }

    public Comment updateComment(Comment Comment, long CommentId) {
        Comment findComment = findVerifiedComment(CommentId);
        //verifyUser(userId, findComment);
        
        User postUser = userService.findVerifiedUser(findComment.getUser().getUserId()); // 작성자
        if(userService.getLoginUser().getUserId() != postUser.getUserId()) // 로그인 유저 != 작성자
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED); // 수정 권한 없음

        Optional.ofNullable(Comment.getBody())
                .ifPresent(findComment::setBody); // 댓글 수정

        findComment.setModifiedAt(LocalDateTime.now()); // 수정 시간 업데이트

        return commentRepository.save(findComment);
    }

    public Comment findComment(long CommentId) {
        return findVerifiedComment(CommentId);
    }

    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size, Sort.by("CommentId").descending()));
    }

    public void deleteComment(long CommentId) {
        Comment findComment = findVerifiedComment(CommentId);
        
        User postUser = userService.findVerifiedUser(findComment.getUser().getUserId()); // 작성자
        if(userService.getLoginUser().getUserId() != postUser.getUserId()) // 로그인 유저 != 작성자
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED); // 삭제 권한 없음
        
        commentRepository.delete(findComment);
    }

    public void verifyUser(long userId, Comment comment) {
        Long thisId = comment.getUser().getUserId();
        if (thisId != userId) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_ALLOWED);
        }
    }

    public Comment findVerifiedComment(long CommentId) {
        Optional<Comment> optionalComment = commentRepository.findById(CommentId);
        Comment findComment = optionalComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.Comment_NOT_FOUND));

        return findComment;
    }
}
