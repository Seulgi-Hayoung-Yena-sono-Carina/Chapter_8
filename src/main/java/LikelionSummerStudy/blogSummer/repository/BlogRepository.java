package LikelionSummerStudy.blogSummer.repository;
import LikelionSummerStudy.blogSummer.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
