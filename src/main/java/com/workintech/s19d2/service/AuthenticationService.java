package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final MemberRepository memberRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AuthenticationService(
      MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Member register(String email, String password) {
    if (memberRepository.findByEmail(email).isPresent()) {
      throw new RuntimeException("User with given email already exists");
    }

    Member member = new Member();
    member.setEmail(email);
    member.setPassword(passwordEncoder.encode(password));

    Role adminRole = roleRepository.findByAuthority("ADMIN").orElseThrow(() -> new RuntimeException("Role not found"));
    member.getRoles().add(adminRole);

    return memberRepository.save(member);
  }
}
