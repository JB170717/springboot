package com.simple.basic.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidVO {

    /*
    @NotNull - null값 제외 - Integer, Long, String 적용함
    @NotEmpty - null제외, 공백허용하지 않는다 - Array, Map, String에 적용가능
    @NotBlank - null제외, 공백x, 화이트스페이스 허용x - String에 적용함

    @Pattern - 정규표현식으로
    @Email - 기본으로 제공되는 이메일 검사
    @Size - 크기 검사.. 등등등
     */

    @NotBlank(message = "이름은 필수 입니다")
    private String name;
    @NotNull(message = "급여는 필수 입니다")
    private Integer salary;
    @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "010-0000-0000 유형입니다")
    private String phone;
    @NotBlank
    @Email(message = "이메일 형식이여야 합니다")
    private String email;

}
