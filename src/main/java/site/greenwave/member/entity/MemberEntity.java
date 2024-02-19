package site.greenwave.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_member")
@EqualsAndHashCode(of = "memberNo")
public class MemberEntity implements UserDetails { //로그인 기능을 구현하고, 사용자 인증과 권한 관리를 효과적으로 처리
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String phone;
    private String nickname;
    private String address1;
    private String address2;
    private Integer memberPoint;

    private String zipcode;

    @Enumerated(EnumType.STRING) //"USER" || "FARMER" 등의 문자열 값을 쓰기 위함
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberNo.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}