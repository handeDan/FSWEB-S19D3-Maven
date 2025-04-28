package com.workintech.s19d2.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Getter
  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "member_roles",
      joinColumns = @JoinColumn(name = "member_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles = new ArrayList<>();

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).toList();
  }

  public String getUsername() {
    return email;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }
}
