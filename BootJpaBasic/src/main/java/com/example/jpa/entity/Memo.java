package com.example.jpa.entity;


import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity //JPS가 관리함
@Table(name="Memo") //테이블명
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Memo {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment를 대신함...
    //시퀀스에 대한 전략
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
//    @SequenceGenerator(name="my_seq", sequenceName = "my_sequence", allocationSize = 1)
    private long mno;
    @Column(nullable = false, length = 100)
    private String writer;
    @Column(columnDefinition = "varchar(200) default 'y'")
    private String text;

    //매니 투 원 - N쪽테이블에 멤버를 선언
    @ManyToOne
    @JoinColumn(name = "member_id") //멤버테이블의 id를 내 테이블의 member_id컬럼이랑 연결함.
    private Member member;

}
