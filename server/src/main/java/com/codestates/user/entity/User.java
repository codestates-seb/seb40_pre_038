package com.codestates.user.entity;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.comment.entity.Comment;
import com.codestates.question.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "USERS")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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
    private UserStatus userStatus = UserStatus.USER_ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false, name = "reputation")
    private int reputation = 0;

    public User(String nickName, String email, String password, int reputation) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.reputation = reputation;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(Long userId, String nickName, String email) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
    }

    public User(Long userId, String nickName, String email, int reputation) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.reputation = reputation;
    }

    public enum UserStatus {

        USER_ACTIVE("Active account"),
        USER_INACTIVE("Inactive account"),
        USER_QUIT("Deleted account");

        @Getter
        private final String status;

        UserStatus(String status) {
            this.status = status;
        }
    }
}
