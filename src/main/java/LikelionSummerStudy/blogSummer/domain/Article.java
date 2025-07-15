package LikelionSummerStudy.blogSummer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED) //protected type 기본 생성자
@AllArgsConstructor
//@Builder //모든 필드(id,createdAt까지 다) 포함하는 객체 생성
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Builder //일부 필드만 포함한 빌더 생성자를 명시적으로 정의
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

    public void update(Optional<String> title, Optional<String> content) {
        title.ifPresent(t -> this.title = t);  // title이 제공되면 업데이트
        content.ifPresent(c -> this.content = c);  // content가 제공되면 업데이트
    }
}
