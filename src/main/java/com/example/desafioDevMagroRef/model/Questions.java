package com.example.desafioDevMagroRef.model;

import com.example.desafioDevMagroRef.dto.QuestionDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    public Questions() {}

    public Questions(QuestionDTO question) {
        this.question = question.question();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
