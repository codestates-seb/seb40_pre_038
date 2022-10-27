package com.codestates.member.entity;

import com.codestates.question.Question;
import com.codestates.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 16, nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();

//    @Column(length = 100, nullable = false)
//    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public Member(String nickName, String email) {
        this.nickName = nickName;
        this.email = email;
    }

    public Member(Long memberId, String nickName, String email) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
    }

    public enum MemberStatus {

        MEMBER_ACTIVE("Active account"),
        MEMBER_INACTIVE("Inactive account"),
        MEMBER_QUIT("Deleted account");

        @Getter
        private final String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
