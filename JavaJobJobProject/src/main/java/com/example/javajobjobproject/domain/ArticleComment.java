package com.example.javajobjobproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Objects;

@Getter
@ToString


@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})

@Entity
public class ArticleComment extends AuditorField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @Setter
    @Column(nullable = false, length = 500)
    private String content;     //댓글 내용

    @Setter
    private @ManyToOne(optional = false) Article article;    //게시글(ID)



    protected ArticleComment() {
    }

    private ArticleComment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

    public static ArticleComment of(String content, Article article) {
        return new ArticleComment(content, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
