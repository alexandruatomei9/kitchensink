package com.example.kitchensink.controller;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Optional<Member> member = memberService.findById(id);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email")
    public ResponseEntity<Member> getMemberByEmail(@RequestParam String email) {
        Optional<Member> member = memberService.findByEmail(email);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Member> getAllMembers() {
        return memberService.findAllOrderedByName();
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("newMember", newMember);
            model.addAttribute("members", memberService.findAll());
            return "index";
        }

        memberService.register(newMember);
        model.addAttribute("newMember", new Member());
        model.addAttribute("registrationSuccess", "Registered!");
        model.addAttribute("members", memberService.findAll());
        return "index";
    }
}
