package com.workintech.s19d2.controller;

import com.workintech.s19d2.dto.RegistrationMember;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workintech/auth")
public class AuthController {

  @Autowired private MemberRepository memberRepository;

  @Autowired private RoleRepository roleRepository;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegistrationMember registrationMember) {
    Member member = new Member();
    member.setEmail(registrationMember.email());
    member.setPassword(registrationMember.password());

    Role userRole = roleRepository.findByAuthority("USER").orElse(null);
    Role adminRole = roleRepository.findByAuthority("ADMIN").orElse(null);

    member.getRoles().add(userRole);
    member.getRoles().add(adminRole);

    memberRepository.save(member);

    return ResponseEntity.ok("User registered successfully!");
  }
}
