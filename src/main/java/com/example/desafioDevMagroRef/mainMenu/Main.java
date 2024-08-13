package com.example.desafioDevMagroRef.mainMenu;

import com.example.desafioDevMagroRef.dto.QuestionDTO;
import com.example.desafioDevMagroRef.dto.UserDTO;
import com.example.desafioDevMagroRef.exception.*;
import com.example.desafioDevMagroRef.model.Questions;
import com.example.desafioDevMagroRef.model.User;
import com.example.desafioDevMagroRef.service.QuestionService;
import com.example.desafioDevMagroRef.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    private Scanner scanner = new Scanner(System.in);
    private List<User> users = new ArrayList<>();
    private List<Questions> questions = new ArrayList<>();

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

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
                        6 - Remover Usuário
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
                case 6:
                    removeUser();
                    break;
                case 0:
                    System.out.println("saindo...");
                    break;
            }
        }
    }

    private void listQuestions() {
        questions = questionService.getAllQuestions();
        if(questions.isEmpty()) {
            System.out.println("Nenhuma pergunta encontrada");
        }else {
            for(int i = 0; i < questions.size(); i++) {
                System.out.println((i + 1) + " - " + questions.get(i).getQuestion());
            }
        }
    }

    private void cadastrarUsuario() {
        listQuestions();
        try {
            var name = scanner.nextLine();
            if(name.length() < 10) {
                throw new InvalidNameException("O nome deve possuir mais de dez caracteres");
            }

            var email = scanner.nextLine();
            if(!email.contains("@")) {
                throw new InvalidEmailException("O email não está escrito corretamente");
            }
            if(userService.getUserByEmail(email).isPresent()) {
                throw new InvalidEmailException("Esse usuário já foi criado");
            }

            var age = scanner.nextInt();
            if (age < 18) {
                throw new InvalidAgeException("O usuário tem que ser maior de idade");
            }
            scanner.nextLine();

            var heigthInput = scanner.nextLine();
            if(heigthInput.contains(".")) {
                throw new InvalidHeigthException("A altura tem que ser escrita com vírgula");
            }
            scanner.nextLine();

            //Trocando a vírgula por ponto
            double height = Double.parseDouble(heigthInput.replace(",", "."));

            UserDTO newUser = new UserDTO(name, email, age, height);
            userService.saveUser(newUser);

            User user = new User(newUser);
            System.out.println("***** DADOS *****");
            System.out.println(user);
            System.out.println("*** Cadastro feito com sucesso ***");

        }catch (InvalidNameException | InvalidEmailException | InvalidAgeException | InvalidHeigthException e ) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private void listarUsuarios() {
        users = userService.getAllUsers();
        for(int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + " - " + users.get(i).getName());
        }
    }

    private void addQuestion() {
        System.out.println("Escreva mais uma pergunta");
        var questions = scanner.nextLine();

        QuestionDTO newQuestion = new QuestionDTO(questions);

        questionService.saveQuestion(newQuestion);
    }

    private void removeQuestion() {
        System.out.println("Perguntas existentes:");
        listQuestions();
        System.out.println("Digite o número da pergunta que deseja remover");
        var number = scanner.nextLong();
        scanner.nextLine();

        try {
            if(number == 1 || number == 2 || number == 3 || number == 4) {
                throw new RemoveQuestionsException("Não é possível remover as perguntas de 1 a 4");
            }
            int index = (int) number - 1; // Convertendo long para int e ajustando para o indice que começar no 0
            Questions questionsToRemove = questions.get(index); //Obter a pergunta pela posição na lista
            questionService.deleteQuestion(questionsToRemove.getId()); // Removendo do banco de dados pelo Id
            System.out.println("Pergunta removida com sucesso");
        }catch (RemoveQuestionsException e) {
            System.out.println("Erro ao remover pergunta: " + e.getMessage());
        }
    }

    private void searchUser() {
        System.out.println("Digite um trecho ou o nome completo do usuário, ou o email ou a idade");
        var excerpt = scanner.nextLine().toLowerCase();
        users = userService.getAllUsers();
        users.stream()
                .filter(u -> u.getName().toLowerCase().contains(excerpt) ||
                        u.getEmail().toLowerCase().contains(excerpt) ||
                        String.valueOf(u.getAge()).contains(excerpt)) // Adicionando a verificação da idade
                .forEach(u -> System.out.println(u + "\n"));
        System.out.println("*************");

    }

    private void removeUser() {
       try {
           System.out.println("usuários cadastrados");
           listarUsuarios();
           System.out.println("Digite o número do usuário que você queira deletar");
           var index = scanner.nextInt();
           scanner.nextLine();

           User foundUser = users.get(index - 1);
           userService.removeUser(String.valueOf(foundUser.getId()));
           System.out.println("Usuário removido com sucesso");
       }catch (RuntimeException e ) {
           System.out.println(e.getMessage());
       }

    }
}
