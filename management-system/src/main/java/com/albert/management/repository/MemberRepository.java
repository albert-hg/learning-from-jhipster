package com.albert.management.repository;

import com.albert.management.dto.MemberDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    
}
