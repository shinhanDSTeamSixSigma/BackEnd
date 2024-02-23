package site.greenwave.member.dto;

import lombok.*;
import site.greenwave.member.entity.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberInfoDto {
    private Integer memberNo;
    private String memberId;
    private String memberName;
    private String phone;
    private String nickname;
    private String address1;
    private String address2;
    private Integer memberPoint;
    private String zipcode;
    private Role role;
}
