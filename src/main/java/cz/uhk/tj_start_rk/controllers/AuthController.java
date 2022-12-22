package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Member;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import cz.uhk.tj_start_rk.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@CrossOrigin
@RestController
public class AuthController {
    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    public AuthController(TokenService tokenService, MemberRepository memberRepository) {
        this.tokenService = tokenService;
        this.memberRepository = memberRepository;
    }

    @JsonView(View.BasicMember.class)
    @PostMapping("/token")
    public String token(Authentication authentication, HttpServletResponse response) {
        Optional<Member> member = memberRepository.findByUsername(authentication.getName());

        if (member.isEmpty()) {
            return null;
        }

        String token = tokenService.generateToken(authentication, member.get());

        // httpOnly cookie
        Cookie httpOnlyCookie = new Cookie("jwt", token);
        httpOnlyCookie.setMaxAge(86400);
        httpOnlyCookie.setHttpOnly(true);
//        cookie.setDomain(".localhost:3000");
        response.addCookie(httpOnlyCookie);

        // cookie
        Cookie cookie = new Cookie("jwt_payload", token.split("\\.")[1]);
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(false);
//        cookie.setDomain(".localhost:3000");
        response.addCookie(cookie);

        return tokenService.generateToken(authentication, member.get());
    }
}
