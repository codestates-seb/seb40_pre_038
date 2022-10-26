package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerResponseDto {
    private long answerId;
    private Answer.AnswerStatus answerStatus;

    /*private long memberId;
    private long questionId;*/

    private String contents;
    private int voteCounts;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    /*public void setMember(Member member) {
        this.memberId = member.getMemberId();
    }

    public void setQuestion(Question question) {
        this.questionId = question.getQuestionId();
    }*/
}
