package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.member.Member;
import com.codestates.reply.entity.Reply;
import com.codestates.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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

//    private long memberId; // 멤버 쪽과 연동할 것

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private int view;

    @Column(nullable = false)
    private int vote;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tags; // 태그 구현 후 풀기

    @Column(nullable = false)
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

//    private Member memberId; // 회의 후 연동할 것!
//    private List<Answer> answers; // 프론트랑 회의 후 연동!
//    private List<Comment> comments;
}
