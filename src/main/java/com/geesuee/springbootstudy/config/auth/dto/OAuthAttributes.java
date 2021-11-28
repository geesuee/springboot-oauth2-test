package com.geesuee.springbootstudy.config.auth.dto;

import com.geesuee.springbootstudy.web.domain.user.Role;
import com.geesuee.springbootstudy.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // of()
    // OAuth2User 에서 반환하는 사용자 정보는 Map 이기 때문에 값 하나나하나를 변환해야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // toEntity()
    // User 엔티티 생성
    // OAuthAttributes 에서 인테티를 생성하는 시점은 처음 가입할 때
    // 가입할 때의 기본 권한을 GUEST 로 주기 위해서 role 빌더 값에는 Role.GUEST 사용
    // OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스 생성
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
