package com.codestates.member.entity;

import com.codestates.question.Question;
import com.codestates.reply.entity.Reply;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String nickName;

    @Column
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();
//    private List<Answer> answers = new ArrayList<>();


    public Member(Long memberId, String nickName, String email) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
    }
}
