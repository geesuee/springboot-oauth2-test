package com.geesuee.springbootstudy.web.posts;

import com.geesuee.springbootstudy.web.domain.posts.Posts;
import com.geesuee.springbootstudy.web.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    // JUnit 에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    // 보통은 배포 전 전체 테스트를 수행할 때, 테스트 간 데이터 침범을 막기 위해 사용
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스에 데이터가 그대로 남아 다음 테스트 실행시 영향
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePost() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()                    // 테이블 posts 에 insert/update 퀴리 실행, 있으면 update 없으면 insert
                                .title(title)
                                .content(content)
                                .author("geesuee")
                                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();      // 테이블 posts 에 있는 모든 데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void saveBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createDate = " + posts.getCreatedDate());
        System.out.println(">>>>>> modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate().isAfter(now));
        assertThat(posts.getModifiedDate().isAfter(now));
    }
}
