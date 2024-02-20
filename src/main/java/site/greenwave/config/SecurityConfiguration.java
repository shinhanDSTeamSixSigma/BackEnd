package site.greenwave.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import site.greenwave.member.filter.JwtAuthenticationFilter;

import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
// Spring Security를 사용하여 웹 애플리케이션의 보안을 설정하고, JWT 인증 필터를 통해 사용자의 인증을 처리
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF 보호를 비활성화
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/**").permitAll()

                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) //세션을 사용하지 않음
                .authenticationProvider(authenticationProvider)
                //JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 앞에 추가하여 JWT 기반의 인증을 처리
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        //리액트 연동을 위한 cors설정
        http.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                return config;
            }
        }));
        return http.build();
    }
}
