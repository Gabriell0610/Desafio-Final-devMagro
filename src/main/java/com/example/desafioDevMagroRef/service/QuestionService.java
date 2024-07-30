package com.example.desafioDevMagroRef.service;

import com.example.desafioDevMagroRef.Respositories.QuestionRepository;
import com.example.desafioDevMagroRef.dto.QuestionDTO;
import com.example.desafioDevMagroRef.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Questions saveQuestion(QuestionDTO questionDto) {
       return questionRepository.save(new Questions(questionDto));
    }

    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }
}
