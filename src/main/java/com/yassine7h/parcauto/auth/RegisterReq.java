package com.yassine7h.parcauto.auth;

import com.yassine7h.parcauto.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String email;
    private String password;
    private Role role;
}
