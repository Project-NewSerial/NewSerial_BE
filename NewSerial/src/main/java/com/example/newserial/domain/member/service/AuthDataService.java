package com.example.newserial.domain.member.service;

import com.example.newserial.domain.error.BadRequestException;
import com.example.newserial.domain.error.ErrorCode;
import com.example.newserial.domain.member.config.jwt.JwtUtils;
import com.example.newserial.domain.member.repository.Member;
import com.example.newserial.domain.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDataService {

    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;


    //AT 검사함.
    public Member checkAccessToken(HttpServletRequest request) {
        String accesstoken = jwtUtils.getAccessTokenFromAuthorization(request);
        if (!jwtUtils.validateJwtToken(accesstoken)) { //AT 만료
            String refreshToken = jwtUtils.getJwtFromCookies(request); //쿠키에서 RT 꺼냄
            try {
                accesstoken = checkRefreshToken(refreshToken); //리프레시 토큰 검사후 액세스 토큰 재발급
            } catch (ExpiredJwtException e) {  //리프레시 토큰 만료한 경우
                throw new BadRequestException("재로그인 필요", ErrorCode.BAD_REQUEST);
            }
        }
        String email = jwtUtils.getEmailFromJwtToken(accesstoken);
        return memberRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("해당 계정이 존재하지 않습니다", ErrorCode.BAD_REQUEST));
    }


    //RT 검사함. 예외 던져지면 로그인 다시하라고 리다이렉트
    public String checkRefreshToken(String refreshToken) {
        if (!jwtUtils.validateJwtToken(refreshToken)) {
            Jwt jwt = jwtUtils.getTokenClaims(refreshToken);
            throw new ExpiredJwtException(jwt.getHeader(), (Claims) jwt.getBody(), "토큰 만료됨");
        }
        String email = jwtUtils.getEmailFromJwtToken(refreshToken);
        return jwtUtils.generateAccessTokenFromEmail(email);  //액세스 토큰과 함께 필요한 정보 전달해야함
    }

    // 로그인으로 리다이렉션
    public ResponseEntity<?> redirectToLogin() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .header("Location", "/login")
                .build();
    }

}
