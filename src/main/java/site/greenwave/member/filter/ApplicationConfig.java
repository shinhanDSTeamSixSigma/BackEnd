package site.greenwave.member.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import site.greenwave.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final MemberRepository repository;

    @Bean
    //Spring Security가 사용자의 인증 및 권한 부여를 위해 사용
    // 사용자의 이메일을 기반으로 데이터베이스에서 사용자를 검색하고, 해당 사용자의 세부 정보를 제공하여 인증을 수행
    public UserDetailsService userDetailsService() {
        return username -> repository.findById(Integer.valueOf(username))
                //주어진 이메일에 해당하는 사용자가 데이터베이스에 없는 경우 예외가 발생
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // DaoAuthenticationProvider 객체를 생성하고, userDetailsService()를 설정하여 사용자의 인증을 처리
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호를 암호화
    }
}
