package com.example.kitchensink.service;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void register(Member member) {
        memberRepository.save(member);
        //memberEventSrc.fire(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> findAllOrderedByName() {
        return memberRepository.findAllByOrderByNameAsc();
    }

    public Optional<Member> findByEmail(String email) {
        return Optional.of(memberRepository.findByEmail(email));
    }

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }
}
