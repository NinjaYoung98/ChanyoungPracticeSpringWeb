package com.example.javajobjobproject.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
public class ArticleDto implements Serializable {
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createdBy;




}
