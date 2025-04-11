package com.coding404.myweb.command;

import lombok.*;

import java.sql.ClientInfoStatus;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoMemberVO {

    private int mid;
    private String name;
    //1:N조인에서는 list을 선언

    List<DemoOrderVO> orderList;


}
