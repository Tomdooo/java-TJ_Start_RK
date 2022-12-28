package cz.uhk.tj_start_rk.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieAuthFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;

    public CookieAuthFilter(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
    }


}
