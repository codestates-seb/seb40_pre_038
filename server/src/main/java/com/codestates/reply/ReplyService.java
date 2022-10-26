package com.codestates.reply;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.answer.service.AnswerService;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;

import com.codestates.member.repository.MemberRepository;
import com.codestates.question.Question;
import com.codestates.question.QuestionService;
import com.codestates.reply.entity.Reply;
import com.codestates.reply.entity.ReplyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public ReplyService(ReplyRepository replyRepository, QuestionService questionService,
                        AnswerService answerService, AnswerRepository answerRepository,
                        MemberRepository memberRepository) {
        this.replyRepository = replyRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
    }

    public Reply createReply(Reply reply, ReplyType replyType, long postId) {
        if (replyType == ReplyType.QUESTION) {
            Question question = questionService.findQuestion(postId);
            reply.setReplyType(ReplyType.QUESTION);
            question.getReplies().add(reply);
        }
        if (replyType == ReplyType.ANSWER) {
            Answer answer = answerService.findAnswer(postId);
            reply.setReplyType(ReplyType.ANSWER);
            answer.getReplies().add(reply);
        }
            return replyRepository.save(reply);
    }

    public Reply updateReply(Reply reply, long replyId) {
        Reply findReply = findVerifiedReply(replyId);

        Optional.ofNullable(reply.getBody())
                .ifPresent(findReply::setBody); // 댓글 수정

        return replyRepository.save(findReply);
    }

    public Reply findReply(long replyId) {
        return findVerifiedReply(replyId);
    }

//    public Page<Reply> findReplies(int page, int size) {
//        return replyRepository.findAll(PageRequest.of(page, size, Sort.by("replyId").descending()));
//    }

    public void deleteReply(long replyId) {
        Reply reply = findVerifiedReply(replyId);
        replyRepository.delete(reply);
    }

    public Reply findVerifiedReply(long replyId) {
        Optional<Reply> optionalReply = replyRepository.findById(replyId);
        Reply findReply = optionalReply.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.REPLY_NOT_FOUND));

        return findReply;
    }
}
