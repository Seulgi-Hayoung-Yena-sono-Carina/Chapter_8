package LikelionSummerStudy.blogSummer.dto.response;

import LikelionSummerStudy.blogSummer.domain.Article;
import lombok.Data;

@Data
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article){
        this.id=article.getId();
        this.title= article.getTitle();
        this.content= article.getContent();
    }
}
