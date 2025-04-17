package com.example.jpa.repository;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.MemberMemoDTO;
import com.example.jpa.entity.Memo;

import java.util.List;

public interface MemoCustomRepository {

    int updateTest(String writer, Long mno);
    //매니 투 원
    List<Memo> mtoJoin1(long mno);
    //매니투원조인 - 연관관계가 없는 경우 on절
    List<Memo> mtoJoin3(String name);
    //원투매니조인
    Member otmJoin1(String id);
    //DTO로 반환받기
    List<MemberMemoDTO> getList(String id);

    List<MemberMemoDTO> getList2(String text);

    /// /////
    //쿼리dsl
    Memo selectDsl(); //단일행
    List<Memo> selectDsl2(); //복수행
    List<Memo> selectDsl3(String searchType, String searchName); //동적쿼리


}
