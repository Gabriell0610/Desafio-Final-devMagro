package com.example.desafioDevMagroRef.Respositories;

import com.example.desafioDevMagroRef.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Long> {

}
