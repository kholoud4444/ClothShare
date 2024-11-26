package com.ntg.backend.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class verifyResetPasswordCode {
    private String email;
    private String resetPasswordCode;
}
