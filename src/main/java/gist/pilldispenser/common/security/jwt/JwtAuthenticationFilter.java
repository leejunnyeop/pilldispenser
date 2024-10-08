package gist.pilldispenser.common.security.jwt;


import gist.pilldispenser.common.security.UsersDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UsersDetailsService usersDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isExcludedByUrl(request) || isExcludedByMethod(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = getAuthorizationFromHeader(request);

        if (jwtProvider.isValidToken(accessToken)){
            UserDetails usersDetails = getMemberFromToken(accessToken);
            storeAuthenticationInContext(request, usersDetails);
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthorizationFromHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private UserDetails getMemberFromToken(String token) {
        Long id = (long)((int)jwtProvider.getClaimValue(token, "user-id"));
        return usersDetailsService.loadUserById(id);
    }

    private void storeAuthenticationInContext(HttpServletRequest request,
                                              UserDetails usersDetails) {
        AbstractAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        usersDetails, null, usersDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public boolean isExcludedByUrl(HttpServletRequest request)  {
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("kakao")) {
            log.info("Request sent to: "+request.getRequestURI()+" is excluded from JwtAuthenticationFilter");
            return true;
        } else {
            return false;
        }
    }

    public boolean isExcludedByMethod(HttpServletRequest request) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.info("Request send via OPTIONS method is excluded from JwtAuthenticationFilter.");
            return true;
        } else {
            return false;
        }
    }
}
