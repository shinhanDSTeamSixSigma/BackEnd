package site.greenwave.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 클라이언트에서 로그인 요청을 보낼 때 전송되는 데이터를 담는 클래스
// JSON 형식의 데이터를 자바 객체로 매핑하기 위해 사용
public class AuthenticationRequest {
    private String email;
    private String password;
}
