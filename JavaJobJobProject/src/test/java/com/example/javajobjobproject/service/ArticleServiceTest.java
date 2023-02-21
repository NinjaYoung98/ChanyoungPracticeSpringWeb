package com.example.javajobjobproject.service;

import com.example.javajobjobproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;

    @Mock private ArticleRepository articleRepository;


    @DisplayName("게시글을 검색하면 ,게시글 리스트를 반환한다")
    @Test
    void givenSearchParameter_WhenSearchingArticles_thenReturnsArticleList() {

    }
}