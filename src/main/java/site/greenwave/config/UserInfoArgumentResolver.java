package site.greenwave.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import site.greenwave.member.service.JwtService;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component
@Slf4j
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Override //UserInfo 어노테이션을 쓰기 전에 돌아감 1
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(UserInfo.class); // 어노테이션 쓴지 확인하고
        boolean hasType = UserInfoDto.class.isAssignableFrom(parameter.getParameterType()); //UserInfoDto 타입인지 확인
        return hasAnnotation && hasType;
    }

    @Override // 요청에서 쿠키에서 값 꺼내와서 UserInfoDto 생성 , 토큰에 있는거 뜯어서 세팅
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Cookie[] cookies = request.getCookies();
        String authToken = "";
        if (cookies != null) {
            log.info("작동");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    authToken = cookie.getValue();
                    break;
                }
            }
        }

        if(authToken != null){
            //토큰을 뜯어서 role name id
            UserInfoDto userInfoDto = new UserInfoDto();

            Base64.Decoder decoder = Base64.getUrlDecoder();
            Claims userInfoFromToken = getUserInfoFromToken(authToken);
            log.info("userInfoFromToken :{}", userInfoFromToken);


            userInfoDto.setRole((String) userInfoFromToken.get("role"));
            userInfoDto.setName((String) userInfoFromToken.get("name"));
            userInfoDto.setId(Integer.valueOf((String) userInfoFromToken.get("sub")));
            return userInfoDto;
        }

        return new Exception("토큰이 없다");

    }
    // 토큰의 payload 값 꺼내오기
    public Claims getUserInfoFromToken(String token) {
        if(token != null){
            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        }
        return null;
    }
}
