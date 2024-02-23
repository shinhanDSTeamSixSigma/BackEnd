package site.greenwave.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.greenwave.member.dto.AuthenticationRequest;
import site.greenwave.member.dto.AuthenticationResponse;
import site.greenwave.member.dto.MemberInfoDto;
import site.greenwave.member.dto.RegisterRequest;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.entity.Role;
import site.greenwave.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {

        if (repository.existsByMemberId(request.getMemberId())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        Role role = request.isFarmer() ? Role.FARMER : Role.USER;

        var user = MemberEntity.builder()
                .memberId(request.getMemberId())
                .memberPwd(passwordEncoder.encode(request.getMemberPwd()))
                .memberName(request.getMemberName())
                .phone(request.getPhone())
                .nickname(request.getNickname())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .memberPoint(0)
                .zipcode(request.getZipcode())
                .role(role)
                .build();


        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        log.info(request.getEmail());

        // 위 이메일과 비번이 인증되면, 토큰 생성하고 보내기
        var user = repository.findByMemberId(request.getEmail()) // 아이디 찾기
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        // 비밀번호 일치하는 경우
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            // 비밀번호 불일치
            throw new RuntimeException("비밀번호가 잘못되었습니다.");
        }
    }

    public MemberInfoDto findMemberByMemberNo(Integer memberNo) {
        Optional<MemberEntity> memberEntity = repository.findByMemberNo(memberNo);

        MemberInfoDto memberInfoDto = new MemberInfoDto();

        return memberInfoDto;
    }

}
