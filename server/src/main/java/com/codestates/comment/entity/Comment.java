package com.codestates.comment.entity;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.user.entity.User;
import com.codestates.question.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType commentType;

    @Column(nullable = false)
    private String body;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @JsonBackReference
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void addQuestion(Question question) {
        this.question = question;
    }
    public void addAnswer(Answer answer){
        this.answer = answer;
    }
    public void addUser(User user) {
        this.user = user;
    }

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

//    public Long getPostId() {
//        if(commentType == CommentType.QUESTION) {
//            return question.getQuestionId();
//        }
//        if(commentType == CommentType.ANSWER) return answer.getAnswerId();
//
//        throw new UnsupportedOperationException("Unsupported CommentType. CommentType = " + commentType);
//    }


    public Comment(CommentType commentType, User user, Question question, String body) {
        this.user = user;
        this.commentType = commentType;
        this.question = question;
        this.body = body;
    }

    public Comment(CommentType commentType, User user, Answer answer, String body) {
        this.user = user;
        this.commentType = commentType;
        this.answer = answer;
        this.body = body;
    }

    public Comment(CommentType commentType, String body) {
        this.commentType = commentType;
        this.body = body;
    }
}
