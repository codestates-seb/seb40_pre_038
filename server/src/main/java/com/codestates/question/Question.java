package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.user.entity.User;
import com.codestates.comment.entity.Comment;
import com.codestates.vote.QuestionVote.QuestionVote;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(nullable = false)
    private String body;

    @Column
    private String tagBody;

    @ElementCollection
    @CollectionTable(name = "TAG_LIST", joinColumns = @JoinColumn(name = "TAG_ID"))
    @Column(name = "TAG_BODY")
    private List<String> tagList;

    @Column(nullable = false)
    private int view = 0;

    @Column(nullable = false)
    private int vote = 0;

    @OneToMany(mappedBy = "question")
    private List<QuestionTag> questionTags = new ArrayList<>(); // Question : QuestionTag = 1 : N

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();


//    public void addQuestionTags(QuestionTag questionTag) {
//        this.questionTags.add(questionTag);
//    }
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

    public void update(String title, String body, List<QuestionTag> questionTags){
        this.title = title;
        this.body = body;
        this.questionTags = questionTags;
    }

}
