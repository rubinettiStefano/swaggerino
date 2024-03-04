package com.generation.swaggerino.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.generation.swaggerino.entities.User;

@Repository
public class UserRepository {

    private final Map<Integer, User> users = new HashMap<Integer, User>();
    private int nextId = 1;

    public UserRepository() {
        // Popoliamo la mappa con alcuni utenti fittizi per scopi dimostrativi
        users.put(1, new User(1, "pippo", "password1"));
        users.put(2, new User(2, "pluto", "password2"));
        users.put(3, new User(3, "paperino", "password3"));
        nextId = 4; // Impostiamo l'ID successivo come 4, in quanto i primi 3 sono già stati utilizzati
    }

    public User findById(int id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<User>(users.values());
    }


    public User findByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Ritorna null se l'utente non è trovato
    }

    public void addUser(User user) {
        user.setId(nextId++);
        users.put(user.getId(), user);
    }

    public void deleteUser(int id) {
        users.remove(id);
    }

    // Altri metodi di manipolazione dei dati dell'utente possono essere aggiunti secondo necessità
}