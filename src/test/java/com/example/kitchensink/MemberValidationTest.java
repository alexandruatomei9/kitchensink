package com.example.kitchensink;

import com.example.kitchensink.model.Member;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberValidationTest {

    @Autowired
    private Validator validator;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhoneNumber("0123456789");
    }

    @Test
    public void shouldValidateValidMember() {
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).isEmpty();
    }

    @Test
    public void shouldNotValidateWhenNameIsBlank() {
        member.setName("");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("size must be between 1 and 25");
    }

    @Test
    public void shouldNotValidateWhenNameIsNull() {
        member.setName(null);
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be null");
    }

    @Test
    public void shouldNotValidateWhenNameContainsNumbers() {
        member.setName("A123");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Must not contain numbers");
    }

    @Test
    public void shouldNotValidateWhenEmailIsBlank() {
        member.setEmail("");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be empty");
    }

    @Test
    public void shouldNotValidateWhenEmailIsNull() {
        member.setEmail(null);
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(2);
        assertThat(violations).anyMatch(violation -> violation.getMessage().equals("must not be null"));
        assertThat(violations).anyMatch(violation -> violation.getMessage().equals("must not be empty"));
    }

    @Test
    public void shouldNotValidateWhenEmailIsInvalid() {
        member.setEmail("invalid-email");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must be a well-formed email address");
    }

    @Test
    public void shouldNotValidateWhenPhoneNumberIsNull() {
        member.setPhoneNumber(null);
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be null");
    }

    @Test
    public void shouldNotValidateWhenPhoneNumberIsEmpty() {
        member.setPhoneNumber("");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(2);
        assertThat(violations).anyMatch(violation -> violation.getMessage().equals("size must be between 10 and 12"));
        assertThat(violations).anyMatch(violation -> violation.getMessage().equals("numeric value out of bounds (<12 digits>.<0 digits> expected)"));
    }

    @Test
    public void shouldNotValidateWhenPhoneNumberIsInvalid() {
        member.setPhoneNumber("invalid-no");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("numeric value out of bounds (<12 digits>.<0 digits> expected)");
    }

    @Test
    public void shouldNotValidateWhenPhoneNumberIsShorterThanExpected() {
        member.setPhoneNumber("12");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("size must be between 10 and 12");
    }

    @Test
    public void shouldNotValidateWhenPhoneNumberIsLongerThanExpected() {
        member.setPhoneNumber("12345678912345");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertThat(violations).hasSize(2);
        assertThat(violations).anyMatch(violation -> violation.getMessage().equals("size must be between 10 and 12"));
        assertThat(violations).anyMatch(violation -> violation.getMessage().equals("numeric value out of bounds (<12 digits>.<0 digits> expected)"));
    }
}
