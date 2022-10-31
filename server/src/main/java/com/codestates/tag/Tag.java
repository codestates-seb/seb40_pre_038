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

//    @Column(nullable = false)
    private String body;

//    @ElementCollection
//    @CollectionTable(name = "TAG_SET", joinColumns = @JoinColumn(name = "TAG_ID"))
//    @Column(name = "TAGS")
//    private Set<String> tagSet;

    @ElementCollection
    @CollectionTable(name = "TAG_SET", joinColumns = @JoinColumn(name = "TAG_ID"))
    @Column(name = "TAGS")
    private List<String> tagList;

    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionTags = new ArrayList<>(); // Tag : QuestionTag = 1 : N

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, name = "status")
//    private TagStatus tagStatus = TagStatus.TAG_NOT_EXIST;
//
//    public enum TagStatus {
//        TAG_NOT_EXIST("태그 없음"),
//        TAG_EXISTS("태그 존재");

//        TAG_NOT_EXIST(0, "태그 없음"),
//        TAG_EXISTS(1, "태그 존재");

//        @Getter
//        private int statusNumber;
//
//        @Getter
//        private String statusDescription;

//        TagStatus(String statusDescription) {
//            this.statusDescription = statusDescription;
//        }
//        TagStatus(int statusNumber, String statusDescription) {
//            this.statusNumber = statusNumber;
//            this.statusDescription = statusDescription;
//        }
//    }

    public void addQuestionTags(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
    }
}