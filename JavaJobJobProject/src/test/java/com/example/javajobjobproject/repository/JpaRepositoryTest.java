package com.example.javajobjobproject.repository;

import com.example.javajobjobproject.config.JpaConfig;
import com.example.javajobjobproject.domain.Article;
import com.example.javajobjobproject.domain.ArticleComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public JpaRepositoryTest(ArticleRepository articleRepository,
                             ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        List<Article> articles = articleRepository.findAll();


        assertThat(articles).isNotNull().hasSize(123);

    }


    @DisplayName("insert test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        Article article = articleRepository.save(Article.of("스프링 공부법",
                "스프링을 잘하기 위해서는 매일 공부하세요",
                "#spring"));
        assertThat(articleRepository.findById(article.getId()).get()).isEqualTo(article);
    }

    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        Article article = articleRepository.findById(1L).orElseThrow();
        article.setHashtag("#spring");
        Article saveArticle = articleRepository.saveAndFlush(article);
        assertThat(saveArticle).hasFieldOrPropertyWithValue("hashtag", "#spring");
    }


    @DisplayName("delete test / casecade")
    @Test
    void given_테스트데이터_when_영속객체자동삭제_then_정상작동하는지() {
        //given
        final String articleCommentTestMsg = "덕분에 스프링이 정상적으로 수행되었네요";
        Article article = articleRepository.findById(1L).orElseThrow();
        ArticleComment articleComment = articleCommentRepository.saveAndFlush(ArticleComment.of(articleCommentTestMsg, article));
        long ArticleCount = articleCommentRepository.count();

        //when
        articleRepository.delete(article);
        long deleteArticleCount = articleCommentRepository.count();


        //then
        assertThat(deleteArticleCount).isEqualTo(ArticleCount - 1);




    }
}



