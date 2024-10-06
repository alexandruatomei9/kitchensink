package com.example.kitchensink;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testFindByEmail() {
        Member member = new Member();
        member.setEmail("john@example.com");
        member.setName("John Doe");
        memberRepository.save(member);

        Member found = memberRepository.findByEmail("john@example.com");

        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("john@example.com");
    }
}
