package com.codestates.reply.entity;

import com.codestates.answer.entity.Answer;
import com.codestates.audit.Auditable;
import com.codestates.member.Member;
import com.codestates.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.lang.reflect.Type;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReplyType replyType;

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

    public long getPostId() {
        if(replyType == ReplyType.QUESTION) return question.getQuestionId();
        if(replyType == ReplyType.ANSWER) return answer.getAnswerId();

        throw new UnsupportedOperationException("Unsupported ReplyType. replyType = " + replyType);
    }
}
