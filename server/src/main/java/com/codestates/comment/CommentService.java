package com.codestates.comment;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.answer.service.AnswerService;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;

import com.codestates.member.repository.MemberRepository;
import com.codestates.question.Question;
import com.codestates.question.QuestionRepository;
import com.codestates.question.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, QuestionService questionService,
                          AnswerService answerService, QuestionRepository questionRepository,
                          AnswerRepository answerRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
    }

    public Comment createComment(Comment comment, CommentType commentType, long postId) {
        if (commentType == CommentType.QUESTION) {
            Question question = questionService.findQuestion(postId);
            comment.setCommentType(CommentType.QUESTION);
            question.getComments().add(comment);
            questionRepository.save(question);
        }
        if (commentType == CommentType.ANSWER) {
            Answer answer = answerService.findAnswer(postId);
            comment.setCommentType(CommentType.ANSWER);
            answer.getComments().add(comment);
            answerRepository.save(answer);
        }
            return commentRepository.save(comment);
    }

    public Comment updateComment(Comment Comment, long CommentId) {
        Comment findComment = findVerifiedComment(CommentId);

        Optional.ofNullable(Comment.getBody())
                .ifPresent(findComment::setBody); // 댓글 수정

        return commentRepository.save(findComment);
    }

    public Comment findComment(long CommentId) {
        return findVerifiedComment(CommentId);
    }

    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size, Sort.by("CommentId").descending()));
    }

    public void deleteComment(long CommentId) {
        Comment Comment = findVerifiedComment(CommentId);
        commentRepository.delete(Comment);
    }

    public Comment findVerifiedComment(long CommentId) {
        Optional<Comment> optionalComment = commentRepository.findById(CommentId);
        Comment findComment = optionalComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.Comment_NOT_FOUND));

        return findComment;
    }
}