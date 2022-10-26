package com.codestates.member;

import com.codestates.answer.entity.Answer;
import com.codestates.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    private long memberId;
    private String nickName;
    private String email;
    private List<Question> questions = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();
}
