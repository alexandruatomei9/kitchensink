package com.example.kitchensink.repository;

import com.example.kitchensink.model.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    List<Member> findAllByOrderByNameAsc();
}
