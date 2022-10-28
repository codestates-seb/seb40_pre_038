package com.codestates.comment.entity;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.member.entity.Member;
import com.codestates.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addQuestion(Question question) {
        this.question = question;
    }
    public void addAnswer(Answer answer){
        this.answer = answer;
    }
    public void addMember(Member member) {
        this.member = member;
    }



    public Long getPostId() {
        if(commentType == CommentType.QUESTION) {
//            System.out.println(question.getCreatedAt());
            return commentId;
        }
        if(commentType == CommentType.ANSWER) return answer.getAnswerId();

        throw new UnsupportedOperationException("Unsupported CommentType. CommentType = " + commentType);
    }
}
