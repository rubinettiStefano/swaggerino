package com.generation.swaggerino.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.swaggerino.entities.User;
import com.generation.swaggerino.repositories.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Metodo per creare un nuovo utente
    @PostMapping
    public User createUser(@RequestBody User user) {
        userRepository.addUser(user);
        return user;
    }

    // Metodo per ottenere un utente per ID
    @GetMapping("/{id}")
    @Operation(description = "Leggo un utente dato il suo id")
    @ApiResponses(value = {
        @ApiResponse
        (
            description = "HO TROVATO YEEE",
            responseCode = "200",
            useReturnTypeSchema = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        ),
        @ApiResponse
        (
            description = "NON HO TROVATO NOOO ",
            responseCode = "404",
            content = @Content(mediaType = "text")
        )
    })
    public ResponseEntity<?> getUserById(@PathVariable @Parameter(description = "l'id, non vuoto, un numero positivo") int id) {
        User u = userRepository.findById(id);

        if(u!=null)
            return new ResponseEntity<User>(u,HttpStatus.OK);
        
            return new ResponseEntity<String>("Utente con id "+id+" non trovato",HttpStatus.NOT_FOUND);
    }

    // Metodo per ottenere tutti gli utenti
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Metodo per eliminare un utente per ID
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id) {
        userRepository.deleteUser(id);
        return "Utente con ID " + id + " eliminato con successo";
    }

    // Metodo per cercare un utente per username
    @GetMapping("/search")
    public User getUserByUsername(@RequestParam String username) {
        return userRepository.findByUsername(username);
    }

    // Altri metodi di manipolazione dei dati dell'utente possono essere aggiunti secondo necessità
}
