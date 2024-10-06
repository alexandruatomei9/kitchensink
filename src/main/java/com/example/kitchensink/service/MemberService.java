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

    public void register(Member member) throws Exception {
        //log.info("Registering " + member.getName());
        //em.persist(member);
        //memberEventSrc.fire(member);
    }

    public List<Member> findAllOrderedByName() {
        return memberRepository.findAllByOrderByNameAsc();
    }

    public Optional<Member> findByEmail(String email) {
        return Optional.of(memberRepository.findByEmail(email));
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
}
