package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Получение пользователя по ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Создание нового пользователя
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Обновление пользователя
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername()); // Используем setUsername вместо setName
        existingUser.setEmail(user.getEmail()); // Если нужно обновить email
        return userRepository.save(existingUser);
    }

    // Удаление пользователя
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}
