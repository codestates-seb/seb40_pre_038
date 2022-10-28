package com.codestates.tag;

import com.codestates.question.QuestionTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    @Column(nullable = false)
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private TagStatus tagStatus = TagStatus.TAG_NOT_EXIST;

    @OneToMany(mappedBy = "tag")
    private Set<QuestionTag> questionTags = new HashSet<>();

    public enum TagStatus {
        TAG_NOT_EXIST(0, "태그 없음"),
        TAG_EXISTS(1, "태그 존재");

        @Getter
        private int statusNumber;

        @Getter
        private String statusDescription;

        TagStatus(int statusNumber, String statusDescription) {
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }
    }

    public void addQuestionTags(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
    }
}
