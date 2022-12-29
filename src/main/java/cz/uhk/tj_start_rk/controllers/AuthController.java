package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Member;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import cz.uhk.tj_start_rk.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@CrossOrigin
@RestController
public class AuthController {
    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @Value("${environment.COOKIE_DOMAIN}")
    private String cookieDomain;

    public AuthController(TokenService tokenService, MemberRepository memberRepository) {
        this.tokenService = tokenService;
        this.memberRepository = memberRepository;
    }

    @JsonView(View.BasicMember.class)
    @PostMapping("/token")
    public String token(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        Cookie jwt = null;

        if (cookies != null) {  // if cookies exists find 'jwt' cookie
            for (int i = 0; i < cookies.length-1; i++) {
                if (cookies[i].getName().equals("jwt")) {
                    jwt = cookies[i];
                    break;
                }
            }
        }

        if (jwt != null) {  // if 'jwt' cookie exists return its token value
            return jwt.getValue();
        }
        else if (authentication.getName() != null) {    // else if authentication has name => create new token
            Optional<Member> member = memberRepository.findByUsername(authentication.getName());

            if (member.isEmpty()) {
                return null;
            }

            String token = tokenService.generateToken(authentication, member.get());

            // httpOnly cookie
            Cookie httpOnlyCookie = new Cookie("jwt", token);
            httpOnlyCookie.setMaxAge(86400);
            httpOnlyCookie.setHttpOnly(true);
            httpOnlyCookie.setDomain(cookieDomain);
            httpOnlyCookie.setPath("/");
            response.addCookie(httpOnlyCookie);

            // cookie
            Cookie cookie = new Cookie("jwt_payload", token.split("\\.")[1]);
            cookie.setMaxAge(86400);
            cookie.setHttpOnly(false);
            cookie.setDomain(cookieDomain);
            httpOnlyCookie.setPath("/");
            response.addCookie(cookie);

            return tokenService.generateToken(authentication, member.get());
        }

        return null;
    }


    @DeleteMapping("/cookies")
    public void clearCookies(HttpServletResponse response) {
        Cookie httpOnlyCookie = new Cookie("jwt", "token");
        httpOnlyCookie.setMaxAge(0);
        httpOnlyCookie.setHttpOnly(true);
        httpOnlyCookie.setDomain(cookieDomain);
        httpOnlyCookie.setPath("/");
        response.addCookie(httpOnlyCookie);

        // cookie
        Cookie cookie = new Cookie("jwt_payload", "token");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(false);
        cookie.setDomain(cookieDomain);
        httpOnlyCookie.setPath("/");
        response.addCookie(cookie);
    }
}
