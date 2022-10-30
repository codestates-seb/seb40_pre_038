package com.codestates.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class POST {
        @Positive
        private long tagId;
        @NotBlank
        private String body;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PATCH{}

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        private long tagId;
        private String body;
    }
}
