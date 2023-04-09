package com.yassine7h.parcauto.auth;


import com.yassine7h.parcauto.models.Account;
import com.yassine7h.parcauto.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthRes register(RegisterReq registerReq){
        Account account=Account.builder()
                .email(registerReq.getEmail())
                .password(passwordEncoder.encode( registerReq.getPassword()))
                .role(registerReq.getRole())
                .build();
        accountService.add(account);
        String token=jwtService.generateToken(account);
        return AuthRes.builder()
                .token(token)
                .build();
    }
    public AuthRes authenticate(AuthReq registerReq){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerReq.getEmail(),
                        registerReq.getPassword()
                )
        );
        var account=accountService.getByEmail(registerReq.getEmail());
        String token=jwtService.generateToken(account);
        return AuthRes.builder()
                .token(token)
                .build();
    }
}
