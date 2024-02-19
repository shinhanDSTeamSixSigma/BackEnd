package site.greenwave.member.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String memberId; // 이메일 값
    private String memberPwd;
    private String memberName;
    private String phone;
    private String nickname;
    private String address1;
    private String address2;
    private Integer memberPoint;
    private String zipcode;

    @Getter
    private boolean farmer; // 농부 여부를 나타내는 boolean 필드
}
