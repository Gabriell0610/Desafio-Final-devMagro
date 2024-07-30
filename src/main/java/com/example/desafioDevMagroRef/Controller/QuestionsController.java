package com.example.desafioDevMagroRef.Controller;

import com.example.desafioDevMagroRef.Respositories.QuestionRepository;
import com.example.desafioDevMagroRef.dto.QuestionDTO;
import com.example.desafioDevMagroRef.model.Questions;
import com.example.desafioDevMagroRef.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionsController {

    @Autowired
    QuestionService questionService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> postQuestions(@RequestBody QuestionDTO questionDto) {
        questionService.saveQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Questions> getQuestions() {
        return questionService.getAllQuestions();
    }

}
