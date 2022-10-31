//package com.codestates.question;
//
//import com.codestates.question.Question;
//import com.codestates.tag.Tag;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "Question_Tag")
//public class QuestionTag {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long questionTagId;
//
//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question; // QuestionTag : Question = N : 1
//
//    @ManyToOne
//    @JoinColumn(name = "tag_id")
//    private Tag tag; // Question : Tag = N : 1
//
//    public void addQuestion(Question question) {
//        this.question = question;
//    }
//
//    public void addTag(Tag tag) {
//        this.tag = tag;
//    }
//
//    public QuestionTag(Question question, Tag tag) {
//        this.question = question;
//        this.tag = tag;
//    }
//}
