package cz.uhk.tj_start_rk.config;

import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

public class JwtAuthInterceptor implements HandlerInterceptor {
    private final JwtDecoder jwtDecoder;

    public JwtAuthInterceptor(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            // Find jwt cookie
            Cookie jwt = null;

            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("jwt")) {
                    jwt = cookies[i];
                    break;
                }
            }

            if (jwt != null) {
                System.out.println("-----");
                System.out.println(SecurityContextHolder.getContext().getAuthentication());
                SecurityContextHolder.getContext().setAuthentication(
                        new JwtAuthenticationToken(jwtDecoder.decode(jwt.getValue()))
                );
                System.out.println(SecurityContextHolder.getContext().getAuthentication());
                System.out.println("-----");
            }

        }
        return true;
    }
}
