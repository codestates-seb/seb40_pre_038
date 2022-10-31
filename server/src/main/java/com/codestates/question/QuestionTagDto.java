package com.codestates.question;

import com.codestates.tag.Tag;
import lombok.Getter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class QuestionTagDto {

    @Getter
    public static class Find {
        @Positive
        private long tagId;

        public Tag getTag() {
            Tag tag = new Tag();
            tag.setTagId(tagId);
            return tag;
        }
    }

    @Getter
    public static class Response {
        private long tagId;
        private String tagBody;
        private List<String> tagList;
    }

}
