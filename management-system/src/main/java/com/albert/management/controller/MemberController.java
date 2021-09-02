package com.albert.management.controller;

import java.util.List;
import java.util.Optional;

import com.albert.management.dto.MemberDTO;
import com.albert.management.repository.MemberRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final Logger log = LoggerFactory.getLogger(MemberController.class);
    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/member")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="member added successful"),
        @ApiResponse(responseCode="400", description="cannot contain id")
    })
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        if (memberDTO.getId() != null) {
            return ResponseEntity.status(400).header("err-msg", "A new member cannot already have an id").build();
        }
        return ResponseEntity.ok().body(memberRepository.save(memberDTO));
    }

    @PutMapping("/member")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="member added successful"),
        @ApiResponse(responseCode="400", description="must have an id"),
        @ApiResponse(responseCode="404", description="not existed id")
    })
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO) {
        if (memberDTO.getId() == null) {
            return ResponseEntity.status(400).header("err-msg", "An update member must have an id").build();
        } else if (!memberRepository.findById(memberDTO.getId()).isPresent()) {
            return ResponseEntity.status(404).header("err-msg", "Not existed id.").build();
        }
        return ResponseEntity.ok().body(memberRepository.save(memberDTO));
    }

    @DeleteMapping("/member/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        memberRepository.deleteById(id);
    }

    @DeleteMapping("/member")
    public void deleteMemberByEntityInBatch(@RequestBody List<MemberDTO> memberDTOs) {
        memberRepository.deleteInBatch(memberDTOs);
    }

    @GetMapping("/member/{id}")
    public Optional<MemberDTO> getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id);
    }

    @GetMapping("/member/bySortParams")
    public ResponseEntity<List<MemberDTO>> findAllBySortParams(
        @RequestParam(value = "sortby", defaultValue = "id") String sortBy,
        @RequestParam(value = "direction", defaultValue = "ASC") Direction direction
    ) {
        // 暫時不考慮排序的目標欄位是某存在，但如果搜尋的欄位名稱不存在時，則會報錯:
        // org.springframework.data.mapping.PropertyReferenceException
        return ResponseEntity.ok().body(memberRepository.findAll(Sort.by(direction, sortBy)));
    }

    @GetMapping("/member/byPageParams")
    public ResponseEntity<Page<MemberDTO>> findAllByPageRequestParams(
        @RequestParam(value = "sortby", defaultValue = "id") String sortBy,
        @RequestParam(value = "direction", defaultValue = "ASC") Direction direction,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok().body(memberRepository.findAll(PageRequest.of(page, size, Sort.by(direction, sortBy))));
    }

    @GetMapping("/member/bySort")
    public ResponseEntity<List<MemberDTO>> findAllBySort(Sort sort) {
        return ResponseEntity.ok().body(memberRepository.findAll(sort));
    }

    @GetMapping("/member/byPageable")
    public ResponseEntity<Page<MemberDTO>> findAllByPageable(Pageable pageable) {
        return ResponseEntity.ok().body(memberRepository.findAll(pageable));
    }

}
