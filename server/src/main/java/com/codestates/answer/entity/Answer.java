package com.codestates.answer.entity;

import com.codestates.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Enumerated(EnumType.ORDINAL)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_NORMAL;

    /*@ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;*/

    @Column(nullable = false, columnDefinition = "TEXT", name = "answer_contents")
    private String contents;

    @Column
    private Long upVote;

    @Column
    private Long downVote;


    public enum AnswerStatus {
        ANSWER_BEST(1, "답변 채택"),
        ANSWER_NORMAL(2, "일반 답변");

        @Getter
        private int statusNumber;

        @Getter
        private String statusDescription;

        AnswerStatus(int statusNumber, String statusDescription) {
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }
    }
}
