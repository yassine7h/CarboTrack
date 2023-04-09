package com.yassine7h.parcauto.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthRes> getAdmins(@RequestBody RegisterReq registerReq) {
        return new ResponseEntity<>(authService.register(registerReq), HttpStatus.OK);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthRes> getAdmin(@RequestBody AuthReq authReq){
        return new ResponseEntity<>(authService.authenticate(authReq),HttpStatus.OK);
    }
}
