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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer {
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

    //@CreatedDate
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    //@LastModifiedDate
    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @JsonManagedReference
    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    private AnswerVote answerVote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "actionStatus")
    private ActionStatus actionStatus = ActionStatus.ACTION_ANSWERED;

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

    public Answer(Long answerId, User user, AnswerStatus answerStatus,  Question question,
                  String body, int vote, LocalDateTime createdAt, LocalDateTime modifiedAt,
                  List<Comment> comments, AnswerVote answerVote, ActionStatus actionStatus) {
        this.answerId = answerId;
        this.answerStatus = answerStatus;
        this.user = user;
        this.question = question;
        this.body = body;
        this.vote = vote;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
        this.answerVote = answerVote;
        this.actionStatus = actionStatus;
    }

    public enum ActionStatus {
        ACTION_ANSWERED("answered"),
        ACTION_MODIFIED( "modified");

        @Getter
        private String actionDescription;

        ActionStatus(String actionDescription) {
            this.actionDescription = actionDescription;
        }
    }
}
