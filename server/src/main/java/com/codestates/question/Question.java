package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.member.entity.Member;
import com.codestates.comment.entity.Comment;
import com.codestates.vote.QuestionVote.QuestionVote;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();


//    public void addQuestionTags(QuestionTag questionTag) {
//        this.questionTags.add(questionTag);
//    }
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

    public void update(String title, String body, List<QuestionTag> questionTags){
        this.title = title;
        this.body = body;
        this.questionTags = questionTags;
    }

}
