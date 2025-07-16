package LikelionSummerStudy.blogSummer;
import LikelionSummerStudy.blogSummer.domain.Article;
import LikelionSummerStudy.blogSummer.repository.BlogRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final BlogRepository blogRepository;

    @PostConstruct //Spring Bean이 생성되고 의존성 주입이 완료된 후 자동으로 실행
    public void init() {
        // 현재 시간 설정
        LocalDateTime now = LocalDateTime.now();

        // 3개의 Article 객체 생성
        Article article1 = new Article("제목 1", "내용 1");
        article1.setCreatedAt(now);
        article1.setUpdatedAt(now);

        Article article2 = new Article("제목 2", "내용 2");
        article2.setCreatedAt(now);
        article2.setUpdatedAt(now);

        Article article3 = new Article("제목 3", "내용 3");
        article3.setCreatedAt(now);
        article3.setUpdatedAt(now);

        // 3개의 Article 객체를 한 번에 저장
        blogRepository.saveAll(List.of(article1, article2, article3));
    }
}
