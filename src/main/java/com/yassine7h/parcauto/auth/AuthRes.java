package com.yassine7h.parcauto.auth;

import com.yassine7h.parcauto.enums.Role;
import lombok.*;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRes {
    private String token;
    private Role role;
}
