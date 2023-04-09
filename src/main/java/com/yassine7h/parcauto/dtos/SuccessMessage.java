package com.yassine7h.parcauto.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessMessage {
    private Object resourceId;
    private String message;
    private String resourceUrl;
}
