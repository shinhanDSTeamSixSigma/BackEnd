package site.greenwave.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.greenwave.config.UserInfo;
import site.greenwave.config.UserInfoDto;
import site.greenwave.member.dto.AuthenticationRequest;
import site.greenwave.member.dto.AuthenticationResponse;
import site.greenwave.member.dto.RegisterRequest;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.repository.MemberRepository;
import site.greenwave.member.service.LoginService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService service;
    private final MemberRepository repository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        log.info("등록 바디");
        log.info(String.valueOf(request));
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            // 등록 실패 시 처리
            return ResponseEntity.badRequest().build();
        }
    }

    //    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request,
//            HttpServletResponse response
//    ) {
//        log.info(String.valueOf(request));
//
//        AuthenticationResponse authenticationResponse = service.authenticate(request);
//
///*        log.info("token {}",authenticationResponse.getToken());
//        Cookie co1 = new Cookie("Auth",authenticationResponse.getToken());
//        co1.setPath("/");
//        co1.setHttpOnly(true);
//        co1.setMaxAge(1000*1000*1000);*/
//        log.info(service.authenticate(request).getToken());
//
//        ResponseCookie auth = ResponseCookie.from("auth", authenticationResponse.getToken()).httpOnly(true)
//                .path("/").maxAge(1000).build();
//
//        return  ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,auth.toString()).body(authenticationResponse);
//    }
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @ModelAttribute AuthenticationRequest request,
            HttpServletResponse response
    )  {
        log.info(String.valueOf(request));

        AuthenticationResponse authenticationResponse = service.authenticate(request);

/*        log.info("token {}",authenticationResponse.getToken());
        Cookie co1 = new Cookie("Auth",authenticationResponse.getToken());
        co1.setPath("/");
        co1.setHttpOnly(true);
        co1.setMaxAge(1000*1000*1000);*/
        log.info(service.authenticate(request).getToken());

        ResponseCookie auth = ResponseCookie.from("auth", authenticationResponse.getToken())
                .path("/").maxAge(100000).build();

        return  ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,auth.toString()).body(authenticationResponse);


    }


    @GetMapping("/test")
    public String cookiee(@CookieValue("auth") String val) {
        log.info("val {}", val);
        return "OK";
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoDto> getUserInfo(@UserInfo UserInfoDto userInfo) {
        // 사용자 정보를 userInfoDto에서 가져와서 반환
        log.info("userInfo {}", userInfo);

        Optional<MemberEntity> member = repository.findByMemberNo(userInfo.getId());

        log.info("member{} " , member);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/t")
    public String tes() {
        return "test";
    }
}

