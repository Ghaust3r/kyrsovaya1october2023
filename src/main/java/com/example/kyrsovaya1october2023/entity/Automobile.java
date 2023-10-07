package com.example.kyrsovaya1october2023.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="AUTOMOBILES")
public class Automobile {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
private Long id;
@Column(name = "owner")
private String name;
@Column(name = "number")
private String number;
@Column(name = "release_date ")
private String date;
@Column(name = "model")
    private String model;

}


