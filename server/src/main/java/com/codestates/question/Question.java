package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.member.entity.Member;
import com.codestates.comment.entity.Comment;
import com.codestates.vote.QuestionVote.QuestionVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private int view = 0;

    @Column(nullable = false)
    private int vote = 0;

    @OneToMany(mappedBy = "question")
    private Set<QuestionTag> questionTags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();


    public void addQuestionTags(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
    }
    public void addMember(Member member) {
        this.member = member;
    }
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToOne(cascade = {CascadeType.ALL})
    private QuestionVote questionVote;

//    private Member memberId; // 회의 후 연동할 것!
//    private List<Answer> answers; // 프론트랑 회의 후 연동!
//    private List<Comment> Comments;

}
