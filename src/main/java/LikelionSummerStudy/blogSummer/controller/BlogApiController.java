package LikelionSummerStudy.blogSummer.controller;

import LikelionSummerStudy.blogSummer.domain.Article;
import LikelionSummerStudy.blogSummer.dto.request.AddArticleRequest;
import LikelionSummerStudy.blogSummer.dto.request.UpdateArticleRequest;
import LikelionSummerStudy.blogSummer.dto.response.ArticleResponse;
import LikelionSummerStudy.blogSummer.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BlogApiController {

    private final BlogService blogService;

    /**
     * @RequestBody: 클라이언트가 보낸 JSON 데이터를 우리가 지정한 DTO 클래스에 자동으로 매핑
     */
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ArticleResponse(savedArticle));
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        /**
         * .stream(): 리스트를 스트림(Stream) 으로 변환 (순차 처리 가능하게 함)
         * .map(ArticleResponse::new): 각 Article 객체를 ArticleResponse 객체로 변환
         * stream()으로 흘러온 각 Article 객체에 대해 new ArticleResponse(article)을 수행
         * .toList(): 변환된 요소들을 다시 리스트로 수집
         * List<Article> → List<ArticleResponse>로 변환하는 것이 목적!
         * */
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable("id") long id,
                                                         @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(new ArticleResponse(updatedArticle));
    }
}
