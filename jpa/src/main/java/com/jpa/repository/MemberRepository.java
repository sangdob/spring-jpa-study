package com.jpa.repository;

import com.jpa.entity.Member;
import com.jpa.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Override
    List<Member> findAll();
}
