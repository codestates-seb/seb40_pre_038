package com.codestates.dto.question;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.member.entity.Member;
import com.codestates.comment.entity.Comment;
import com.codestates.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

//    private Member memberId; // 회의 후 연동할 것!
//    private List<Answer> answers; // 프론트랑 회의 후 연동!
//    private List<Comment> Comments;
}
