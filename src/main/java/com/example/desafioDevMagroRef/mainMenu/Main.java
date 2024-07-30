package com.example.desafioDevMagroRef.mainMenu;

import com.example.desafioDevMagroRef.model.Questions;
import com.example.desafioDevMagroRef.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private QuestionService questionService;
    public void showMenu() {
        var opcao = -1;

        while(opcao != 0) {
            System.out.println("""
                        \n
                        1 - Cadastrar o usuário
                        2 - Listar todos usuários cadastrados
                        3 - Cadastrar nova pergunta no formulário
                        4 - Deletar pergunta do formulário
                        5 - Pesquisar usuário por nome ou idade ou email
                        0 - sair\n""");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    addQuestion();
                    break;
                case 4:
                    removeQuestion();
                    break;
                case 5:
                    searchUser();
                    break;
                case 0:
                    System.out.println("saindo...");
                    break;
            }
        }
    }

    private void cadastrarUsuario() {
        List<Questions> questions = questionService.getAllQuestions();
        if(questions.isEmpty()) {
            System.out.println("Nenhuma pergunta encontrada");
        }else {
            for (Questions question : questions) {
                System.out.println(question.getId() + " - " + question.getQuestion());
            }
        }



    }

    private void listarUsuarios() {
    }

    private void addQuestion() {

    }

    private void removeQuestion() {
    }

    private void searchUser() {
    }
}
