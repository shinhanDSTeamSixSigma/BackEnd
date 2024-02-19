package site.greenwave.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private JwtTokenDecoder jwtTokenDecoder;

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
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    authToken = cookie.getValue();
                }
            }
        }

        //토큰을 뜯어서 email, id, nick
        UserInfoDto userInfoDto = new UserInfoDto();

        return userInfoDto;
    }
}
