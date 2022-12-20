package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Member;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MemberRepository;
import cz.uhk.tj_start_rk.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public String token(Authentication authentication) {
        Optional<Member> member = memberRepository.findByUsername(authentication.getName());

        if (member.isEmpty()) {
            return null;
        }

        return tokenService.generateToken(authentication, member.get());
    }
}
