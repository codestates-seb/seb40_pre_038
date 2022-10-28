package com.codestates.comment;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.answer.service.AnswerService;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.member.service.MemberService;
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

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, QuestionService questionService,
                          AnswerService answerService, QuestionRepository questionRepository,
                          AnswerRepository answerRepository, MemberService memberService,
                          MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    public Comment createQuestionComment(Comment comment, long questionId, long memberId) {
        Question question = questionService.findQuestion(questionId);
        Member findMember = memberService.findVerifiedMember(memberId);
        comment.setQuestion(question);
        comment.setMember(findMember);
        comment.setCommentType(CommentType.QUESTION);
        question.getComments().add(comment);
        questionRepository.save(question);

        return commentRepository.save(comment);
    }

    public Comment createAnswerComment(Comment comment, long questionId, long answerId, long memberId) {
        Question question = questionService.findQuestion(questionId);
        Answer answer = answerService.findAnswer(answerId);
        Member findMember = memberService.findVerifiedMember(memberId);
        comment.setQuestion(question);
        comment.setAnswer(answer);
        comment.setMember(findMember);
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

    public Comment updateComment(Comment Comment, long CommentId, long memberId) {
        Comment findComment = findVerifiedComment(CommentId);
        verifyMember(memberId, findComment);

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

    public void verifyMember(long memberId, Comment comment) {
        Long thisId = comment.getMember().getMemberId();
        if (thisId != memberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }
    }

    public Comment findVerifiedComment(long CommentId) {
        Optional<Comment> optionalComment = commentRepository.findById(CommentId);
        Comment findComment = optionalComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.Comment_NOT_FOUND));

        return findComment;
    }
}
