package com.brwnsugr.webice.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
//  Posts 클래스로 db를 접근하게 해줄 JpaRepository 임
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
