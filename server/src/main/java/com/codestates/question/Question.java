package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.entity.Comment;
import com.codestates.user.entity.User;
import com.codestates.vote.QuestionVote.QuestionVote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000000000)
    private String problem;

    @Column(nullable = false, length = 1000000000)
    private String expect;

    @Column
    private String tagBody;

    @ElementCollection
    @CollectionTable(name = "QUESTION_TAG_LIST", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    @Column(name = "TAG_BODY")
    private List<String> tagList;

    @Column(nullable = false)
    private int view;

    @Column(nullable = false)
    private int vote;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();

    public void addUser(User user) {
        this.user = user;
    }

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        answers.add(answer);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    //@CreatedDate
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    //@LastModifiedDate
    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToOne(cascade = {CascadeType.ALL})
    private QuestionVote questionVote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "actionStatus")
    private ActionStatus actionStatus = ActionStatus.ACTION_ASKED;

    public void update(String title, String problem, String expect) {
        this.title = title;
        this.problem = problem;
        this.expect = expect;
    }

    public Question(String title, String problem, String expect, String tagBody, List<String> tagList, int view, int vote, User user, List<Answer> answers, List<Comment> comments, LocalDateTime createdAt, ActionStatus actionStatus) {
        this.title = title;
        this.problem = problem;
        this.expect = expect;
        this.tagBody = tagBody;
        this.tagList = tagList;
        this.view = view;
        this.vote = vote;
        this.user = user;
        this.answers = answers;
        this.comments = comments;
        this.createdAt = createdAt;
        this.actionStatus = actionStatus;
    }

    public Question(long questionId, User user, String title, String problem, String expect, List<String> tagList, int view, int vote, LocalDateTime createdAt, LocalDateTime modifiedAt, List<Comment> comments, ActionStatus actionStatus) {
        this.questionId = questionId;
        this.user = user;
        this.title = title;
        this.problem = problem;
        this.expect = expect;
        this.tagList = tagList;
        this.view = view;
        this.vote = vote;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
        this.actionStatus = actionStatus;
    }

    public enum ActionStatus {
        ACTION_ASKED("asked"),
        ACTION_MODIFIED("modified");

        @Getter
        private String actionDescription;

        ActionStatus(String actionDescription) {
            this.actionDescription = actionDescription;
        }
    }
}
