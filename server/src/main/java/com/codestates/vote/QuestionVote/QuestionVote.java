package com.codestates.vote.QuestionVote;

import com.codestates.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteAnswerId;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "member_id")
    private long memberId;
}
