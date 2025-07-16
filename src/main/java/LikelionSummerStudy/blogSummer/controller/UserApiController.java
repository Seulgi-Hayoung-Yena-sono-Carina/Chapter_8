package LikelionSummerStudy.blogSummer.controller;

import LikelionSummerStudy.blogSummer.dto.request.AddUserRequest;
import LikelionSummerStudy.blogSummer.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;
    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request); //UserService 계층의 메서드 호출
        return "redirect:/login"; //로그인 페이지로 redirect
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //new SecurityContextLogOutHandler(): 사용자의 인증 정보를 초기화하고, 세션을 무효화하여 로그아웃을 처리
        new SecurityContextLogoutHandler()
                .logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        //SecurityContextHolder.getContext().getAuthentication()
        return "redirect:/login";
    }

}
