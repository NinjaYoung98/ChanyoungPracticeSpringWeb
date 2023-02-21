package com.example.javajobjobproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mvc;
    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;

    }

    @Test
    void givenNothingWhenRequestingViewArticlesThenReturn() throws Exception {

        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));


    }

    @Test
    void givenNothingWhenRequestingViewArticleThenReturn() throws Exception {

        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("articles","articleComment"));


    }

    @Disabled
    @Test
    void givenNothingWhenRequestingViewArticleSearchThenReturn() throws Exception {

        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }

    @Disabled
    @Test
    void givenNothingWhenRequestingViewArticleHashtagSearchThenReturn() throws Exception {

        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));

    }

}