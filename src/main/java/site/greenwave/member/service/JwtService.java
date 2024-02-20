package site.greenwave.member.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.repository.MemberRepository;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

// 클라이언트가 로그인하거나 요청을 보낼 때 JWT를 생성하여 해당 사용자의 인증 정보를 포함하고, 이를 검증하여 사용자의 요청을 인증하는 데 사용
@Service
public class JwtService {

    private final MemberRepository repository;
    private static final String SECFRET_KEY = "232fClKDHBL22dDCSLDNHF40SLCDKHSDLKGH123SLDKVHC80DSLJKCSL367KGDH";

    public JwtService(MemberRepository repository) {
        this.repository = repository;
    }

    // JWT 토큰에서 사용자 이름을 추출
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // JWT 토큰에서 특정 클레임(claim)을 추출하는 유틸리티 메서드
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    // 사용자의 인증 정보를 포함하는 JWT 토큰을 생성
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ) {

        // 사용자의 인증 정보와 함께 추가 클레임을 포함하는 JWT 토큰을 생성
        Map<String, Object> claims = new HashMap<>();

        // 유저 아이디를 통해 유저의 정보 찾기
        Optional<MemberEntity> user = repository.findById(Integer.valueOf(userDetails.getUsername()));
        String name = user.orElseThrow().getMemberName(); // 멤버 이름 저장해두기 ( 토큰에서 빼서 쓰기 위함 )

        claims.put("role", userDetails.getAuthorities().toString()); // 역할 ( 화면 막기 ) 토큰에 추가
        claims.put("name", name); // 이름 토큰에 추가

        return Jwts
                .builder() //JWT 토큰을 생성하기 위한 빌더 객체를 생성
                .setClaims(claims) // 추가 클레임을 지정 ( 현재는 역할, 이름 )
                .setSubject(userDetails.getUsername()) // sub로 id 값 넣기 _ userdetails 에서 username에 id값을 넣어둠
                .setIssuedAt(new Date(System.currentTimeMillis())) // 생성 날짜
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 토큰 만료 시간 : 현재 시간으로부터 24시간 후로 설정
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact(); //모든 설정이 완료된 JWT 토큰을 생성하여 문자열 형태로 반환
    }


    // 주어진 JWT 토큰이 유효한지 확인
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        // token에서 추출한 이름과 사용자의 이름이 같은지, 기간이 만료되지 않았는지 확인
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // JWT 토큰이 만료되었는지 확인
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // JWT 토큰에서 만료 시간을 추출
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //JWT 토큰의 본문(body)에 들어 있는 클레임을 반환
    //토큰의 유효성을 검사하고 클레임을 추출하기 위해 Jwts.parserBuilder()를 사용하여 JWT 파서를 생성하고, 이를 이용해 JWT를 해석
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //SECFRET_KEY를 Base64 디코딩하여 비밀 키 가져오기
    //Keys.hmacShaKeyFor(keyBytes)를 사용하여 HMAC-SHA 알고리즘을 사용하는 시크릿 키를 생성

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECFRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
