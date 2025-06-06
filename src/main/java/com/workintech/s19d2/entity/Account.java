package com.workintech.s19d2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String currency;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private String balance;
}
