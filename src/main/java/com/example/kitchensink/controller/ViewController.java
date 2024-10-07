package com.example.kitchensink.controller;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberService.findAll());
        return "index";
    }

}
