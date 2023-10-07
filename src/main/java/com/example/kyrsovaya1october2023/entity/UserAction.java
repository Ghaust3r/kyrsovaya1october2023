package com.example.kyrsovaya1october2023.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User action")
public class UserAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(targetEntity = User.class)
    private User user;
}


