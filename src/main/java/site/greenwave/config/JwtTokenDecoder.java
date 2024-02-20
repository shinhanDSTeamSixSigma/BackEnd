package site.greenwave.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Optional;

public class JwtTokenDecoder {

    private final String SECRET_KEY = "232fClKDHBL22dDCSLDNHF40SLCDKHSDLKGH123SLDKVHC80DSLJKCSL367KGDH";

    // JWT 토큰을 복호화하고 클레임을 추출하는 메서드
    public Optional<Claims> decodeToken(String token) {
        try {
            // 토큰을 복호화하여 JWS 객체 생성
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            // 복호화된 클레임 반환
            return Optional.of(claimsJws.getBody());
        } catch (Exception e) {
            // 복호화 실패 시 Optional.empty() 반환
            return Optional.empty();
        }
    }

    // 복호화된 클레임에서 role 값을 추출하는 메서드
    public String getRoleFromToken(String token) {
        Optional<Claims> decodedToken = decodeToken(token);
        return decodedToken.map(claims -> claims.get("role", String.class)).orElse(null);
    }

    // 복호화된 클레임에서 name 값을 추출하는 메서드
    public String getNameFromToken(String token) {
        Optional<Claims> decodedToken = decodeToken(token);
        return decodedToken.map(claims -> claims.get("name", String.class)).orElse(null);
    }

    // 복호화된 클레임에서 subject(sub) 값을 추출하는 메서드
    public String getSubjectFromToken(String token) {
        Optional<Claims> decodedToken = decodeToken(token);
        return decodedToken.map(Claims::getSubject).orElse(null);
    }
}