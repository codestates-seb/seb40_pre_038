package com.codestates.member.entity;

import com.codestates.audit.Auditable;
import com.codestates.comment.entity.Comment;
import com.codestates.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 16, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be empty.")
    private String email;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Password cannot be empty.")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();

    public Member(String nickName, String email, String password) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
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
