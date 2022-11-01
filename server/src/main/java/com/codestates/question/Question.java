package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.user.entity.User;
import com.codestates.comment.entity.Comment;
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
public class Question extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false)
    private String title;

//    @Column(nullable = false)
//    private String body;

    @Column(nullable = false)
    private String problem;

    @Column(nullable = false)
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

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToOne(cascade = {CascadeType.ALL})
    private QuestionVote questionVote;

    public void update(String title, String problem, String expect) {
        this.title = title;
        this.problem = problem;
        this.expect = expect;
    }

}
