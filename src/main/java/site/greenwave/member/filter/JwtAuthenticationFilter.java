package site.greenwave.member.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.greenwave.member.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
// 모든 요청에 대해 한 번씩 필터링
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    // Spring Security의 UserDetailsService를 구현한 서비스로 사용자의 인증 정보를 로드하는데 사용
    private final UserDetailsService userDetailsService;

    //모든 요청에 대해 실행되는 필터 메서드
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // jwt 토큰 확인
        // 유효한 토큰이 없으므로 인증을 진행못함, 필터 체인을 그대로 진행하여 다음 필터나 요청 핸들러로 요청을 전달
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // 사용자 이메일이 존재하고, 현재 인증된 사용자가 없는 경우 해당 사용자의 세부 정보를 로드
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // 토큰의 유효성 확인
            if(jwtService.isTokenValid(jwt, userDetails)){

                // 사용자의 세부 정보와 권한을 기반으로 UsernamePasswordAuthenticationToken을 생성하여 보안 컨텍스트에 설정

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
