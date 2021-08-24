package com.albert.management.service;

import java.util.Optional;

import com.albert.management.dto.MemberDTO;
import com.albert.management.repository.MemberRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    public MemberDTO save(MemberDTO member) {
        return memberRepository.save(member);
    }

    public void delete(MemberDTO memberDTO) {
        memberRepository.delete(memberDTO);
    }

    public Optional<MemberDTO> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Page<MemberDTO> findAllByPageable(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

}
