package com.geesuee.springbootstudy.web;

import com.geesuee.springbootstudy.config.auth.LoginUser;
import com.geesuee.springbootstudy.config.auth.dto.SessionUser;
import com.geesuee.springbootstudy.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // @LoginUser SessionUser user
            // 기존에 httpSession.getAttribute("user") 로 가져오던 세션 정보 값 개선
            // 어느 컨트롤러든 @LoginUser 만 사용하면 세션 정보를 가져올 수 있음
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}