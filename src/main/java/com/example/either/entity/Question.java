package com.example.either.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 200)
    private String optionA;

    @Column(nullable = false, length = 200)
    private String optionB;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer){
        answers.add(answer);
        answer.setQuestion(this);
    }

}
