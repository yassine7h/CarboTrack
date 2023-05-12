package com.yassine7h.parcauto.auth;


import com.yassine7h.parcauto.enums.TokenType;
import com.yassine7h.parcauto.models.Account;
import com.yassine7h.parcauto.models.Token;
import com.yassine7h.parcauto.repositories.TokenRepository;
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
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    public AuthRes register(RegisterReq registerReq){
        Account account=Account.builder()
                .email(registerReq.getEmail())
                .password(passwordEncoder.encode( registerReq.getPassword()))
                .role(registerReq.getRole())
                .build();

        var savedAccountId =accountService.add(account);
        String jwtToken=jwtService.generateToken(account);
        account.setId(savedAccountId);

        saveAccountToken(account,jwtToken);

        return AuthRes.builder()
                .token(jwtToken)
                .role(account.getRole())
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
        String jwtToken=jwtService.generateToken(account);

        revokeAllAccountTokens(account);
        saveAccountToken(account,jwtToken);

        return AuthRes.builder()
                .token(jwtToken)
                .role(account.getRole())
                .build();
    }
    private void saveAccountToken(Account account,String jwtToken){
        var token = Token.builder()
                .account(account)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private  void revokeAllAccountTokens(Account account){
        var validAccountTokens=tokenRepository.findAllValidTokensByUser(account.getId());
        if(validAccountTokens.isEmpty()) return;
        validAccountTokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validAccountTokens);
    }
}
