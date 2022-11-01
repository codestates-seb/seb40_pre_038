package com.codestates.answer.entity;

import com.codestates.audit.Auditable;
import com.codestates.user.entity.User;
import com.codestates.question.Question;
import com.codestates.comment.entity.Comment;
import com.codestates.vote.AnswerVote.AnswerVote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(nullable = false, name = "status")
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_NORMAL;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT", name = "body", length = 1000000000)
    private String body;

    @Column(nullable = false, name = "vote")
    private int vote = 0;

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @JsonManagedReference
    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    private AnswerVote answerVote;

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

    public Answer(AnswerStatus answerStatus, String body, List<Comment> comments) {
//        this.question = question;
        this.answerStatus = answerStatus;
        this.body = body;
        this.comments = comments;
    }

    public Answer(AnswerStatus answerStatus, User user, Question question, String body, List<Comment> comments) {
        this.user = user;
        this.answerStatus = answerStatus;
        this.question = question;
        this.body = body;
        this.comments = comments;
    }
}
