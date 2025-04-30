package com.workintech.s19d2.controller;

import com.workintech.s19d2.dto.RegistrationMember;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import com.workintech.s19d2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  @Autowired private MemberRepository memberRepository;

  @Autowired private AuthenticationService authenticationService;

  @Autowired private RoleRepository roleRepository;

  @PostMapping("/register")
  public ResponseEntity<Member> register(@RequestBody RegistrationMember registrationMember) {
    // Rol bilgisi sabit örnek olarak "MEMBER" verildi
    Member registered = authenticationService.register(registrationMember.email(), registrationMember.password());
    return ResponseEntity.ok(registered);
  }

  @PostMapping("/register2")
  public ResponseEntity<String> register2(@RequestBody RegistrationMember registrationMember) {
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
