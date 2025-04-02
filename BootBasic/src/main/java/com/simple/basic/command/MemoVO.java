package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoVO {

   private Integer mno;
   @Size(min=5, message = "5글자이상입니다")
   private String memo;
   @Pattern(regexp = "[0-9]{3}[0-9]{4}[0-9]{4}", message = "010-0000-0000형식입니다")
   private String phone;
   @Pattern(regexp = "[0-9]{4}", message = "비밀번호는 4글자입니다")
   private String pw;
   private String secret;

}
