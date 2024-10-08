package com.example.kitchensink.repository;

import com.example.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Member findByEmail(String email);

    List<Member> findAllByOrderByNameAsc();
}
