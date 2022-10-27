package com.codestates.answer.entity;

import com.codestates.audit.Auditable;
import com.codestates.question.Question;
import com.codestates.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Member member;*/

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT", name = "answer_contents")
    private String contents;

    @Column(nullable = false, name = "votes")
    private int voteCounts = 0;

    /*public void addMember(Member member) {
        this.member = member;
    }*/

    /*public void addQuestion(Question question) {
        this.question = question;
    }*/

    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL})
    private List<Comment> comments = new ArrayList<>();

    public enum AnswerStatus {
        ANSWER_DELETE(0, "삭제된 답변"),
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
