package com.example.desafioDevMagroRef.api;

import com.example.desafioDevMagroRef.Controller.UserController;
import com.example.desafioDevMagroRef.dto.UserDTO;
import com.example.desafioDevMagroRef.model.User;
import com.example.desafioDevMagroRef.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    private UserService userService;

    MockMvc mockMvc;
    private User user;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testPostUser() throws Exception {
        UserDTO userDTO = new UserDTO("Gabriel", "gabe@gmail.com", 20, 1.87);

        mockMvc.perform(post("/user") //Simulando uma requisição POST no endpoint user
                .contentType(MediaType.APPLICATION_JSON) //Definindo o conteúdo da requisição como JSON
                .content(objectMapper.writeValueAsString(userDTO))) // Converte o DTO em um JSON
                .andExpect(status().isCreated());//Verificando se a resposta da requisição tem o status HTTP 201
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User();

        user.setAge(20);
        user.setEmail("gabe@gmail.com");
        user.setName("Gabriel");
        user.setHeigth(1.87);

        // Configuração do mock para retornar uma lista com o usuário
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/user") // Simula uma requisição GET para o endpoint /user
                .accept(MediaType.APPLICATION_JSON)) // Define o tipo de resposta esperado
                .andExpect(status().isOk()) // Verifica se o status HTTP é 200 OK
                .andExpect(jsonPath("$[0].name").value("Gabriel")); // Verifica o nome do usuário na resposta

    }


    @Test
    public void testRemoveUser() throws Exception {

        mockMvc.perform(delete("/user/{id}", 1L))
                .andExpect(status().isNoContent());

    }

}
