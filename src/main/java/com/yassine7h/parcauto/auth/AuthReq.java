package com.yassine7h.parcauto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthReq {
    private String email;
    private String password;
}
