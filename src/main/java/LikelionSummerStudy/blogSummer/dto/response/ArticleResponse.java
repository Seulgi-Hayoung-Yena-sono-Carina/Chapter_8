package LikelionSummerStudy.blogSummer.dto.response;

import LikelionSummerStudy.blogSummer.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ArticleResponse {
    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.title=article.getTitle();
        this.content=article.getContent();
    }
}
