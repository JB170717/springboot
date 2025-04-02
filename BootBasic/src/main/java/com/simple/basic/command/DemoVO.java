package com.simple.basic.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoVO {

    @NotBlank
    @Pattern(regexp  ="[a-zA-Z0-9]{8,}",message = "아이디는 영문자숫자 8자리이상입니다.")
    private String id;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$",message = "비밀번호는 영문 숫자 특수문자 포함입니다")
    private String pw;

}
