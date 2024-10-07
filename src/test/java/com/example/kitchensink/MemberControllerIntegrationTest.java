package com.example.kitchensink;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Transactional was not working, when trying to findById it was returning 404 - find out why
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void shouldFindMemberByEmail() throws Exception {
        Member member = new Member();
        member.setEmail("jane@example.com");
        member.setName("Jane Doe");
        member.setPhoneNumber("0123456789");
        memberRepository.save(member);

        mockMvc.perform(get("/members/email")
                        .param("email", "jane@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jane@example.com"))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.phoneNumber").value("0123456789"));
    }

    @Test
    public void shouldFindMemberById() throws Exception {
        Member member = new Member();
        member.setEmail("jane@example.com");
        member.setName("Jane Doe");
        member.setPhoneNumber("0123456789");
        memberRepository.save(member);

        mockMvc.perform(get("/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jane@example.com"))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.phoneNumber").value("0123456789"));
    }

    @Test
    public void shouldFindNoMembersInitially() throws Exception {
        mockMvc.perform(get("/members/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void shouldFindTwoMembers() throws Exception {
        Member firstMember = new Member();
        firstMember.setEmail("jane@example.com");
        firstMember.setName("Jane Doe");
        firstMember.setPhoneNumber("0123456789");
        memberRepository.save(firstMember);

        Member secondMember = new Member();
        secondMember.setEmail("john@example.com");
        secondMember.setName("John Doe");
        secondMember.setPhoneNumber("9876543210");
        memberRepository.save(secondMember);

        mockMvc.perform(get("/members/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Jane Doe"))
                .andExpect(jsonPath("$[0].email").value("jane@example.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("0123456789"))
                .andExpect(jsonPath("$[1].name").value("John Doe"))
                .andExpect(jsonPath("$[1].email").value("john@example.com"))
                .andExpect(jsonPath("$[1].phoneNumber").value("9876543210"));
    }
}
