package com.borzdykooa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
Класс, соответствующий таблице trainer в базе данных
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "trainees")
@Entity
@Table(name = "trainer", schema = "spr_hib_declarative_transaction_management_database")
public class Trainer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "experience")
    private Integer experience;

    @OneToMany(mappedBy = "trainer")
    private Set<Trainee> trainees = new HashSet<>();

    public Trainer(String name, String language, Integer experience) {
        this.name = name;
        this.language = language;
        this.experience = experience;
    }
}
