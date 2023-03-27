package com.yassine7h.parcauto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessMessage {
    private Object resourceId;
    private String message;
    private String resourceUrl;
}
