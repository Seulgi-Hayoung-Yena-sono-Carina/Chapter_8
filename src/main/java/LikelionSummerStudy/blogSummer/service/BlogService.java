package LikelionSummerStudy.blogSummer.service;
import LikelionSummerStudy.blogSummer.domain.Article;
import LikelionSummerStudy.blogSummer.dto.request.AddArticleRequest;
import LikelionSummerStudy.blogSummer.dto.request.UpdateArticleRequest;
import LikelionSummerStudy.blogSummer.dto.response.ArticleResponse;
import LikelionSummerStudy.blogSummer.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id){
        //값이 없을 때 던질 예외를 람다식으로 정의
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    @Transactional
    public Article patch(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        //Optional.ofNullable은 값이 null일 경우 빈 Optional 객체를 반환하고, 값이 존재하면 해당 값을 감싼 Optional 객체를 반환
        article.update(Optional.ofNullable(request.getTitle()), Optional.ofNullable(request.getContent()));
        return article;
    }
}
