package com.brwnsugr.webice.web;

import com.brwnsugr.webice.config.auth.dto.SessionUser;
import com.brwnsugr.webice.service.posts.PostsService;
import com.brwnsugr.webice.web.dto.PostsResponseDto;
import com.brwnsugr.webice.config.auth.LoginUser;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("posts/save")
    public String postsSave() {
        return "posts-save"; // posts-save.mustache 를 호출 한다!
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update"; // mustache 파일로 이동
    }
}
