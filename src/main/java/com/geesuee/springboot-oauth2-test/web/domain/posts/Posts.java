package com.geesuee.springbootstudy.web.domain.posts;

import com.geesuee.springbootstudy.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor      // 기본 생성자 자동 추가 public Posts() {} 와 같은 효과
@Entity                 // 테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭
public class Posts extends BaseTimeEntity {

    @Id                                                     // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)                 // 문자열의 경우 VARCHAR(255)가 기본, 이를 500으로 늘림
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
