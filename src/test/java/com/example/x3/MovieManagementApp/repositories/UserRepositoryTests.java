package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.User;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    private User user;
    @BeforeEach
    void init(){
        user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("jdoe@gmail.com")
                .displayName("jdoe")
                .password("1234")
                .build();
    }

    @Test
    @DisplayName("Test for save user operation")
    void givenUserObject_whenSave_thenReturnSavedUser(){
        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test for find all operation")
    void givenMultipleUsers_whenFindAll_thenReturnUserList(){

        User user2 = User.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("janedoe@gmail.com")
                .displayName("janeDoe")
                .password("1234")
                .build();

        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = (List<User>) userRepository.findAll();

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test for get user by email operation")
    void givenUserEmail_whenFindByEmail_thenReturnUserObject(){
        userRepository.save(user);
        User userDB = userRepository.findByEmail(user.getEmail()).get();

         assertThat(userDB).isNotNull();
         assertThat(userDB.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Test for get user by display name operation")
    void givenUserDisplayName_whenFindByDisplayName_thenReturnUserObject(){
        userRepository.save(user);
        User userDB = userRepository.findByDisplayName(user.getDisplayName()).get();

        assertThat(userDB).isNotNull();
        assertThat(userDB.getDisplayName()).isEqualTo(user.getDisplayName());
    }

    @Test
    @DisplayName("Test for get user by display name or email operation")
    void givenUserDisplayNameOrEmail_whenFindByDisplayNameOrEmail_thenReturnUserObject(){
        User savedUser = userRepository.save(user);

        Optional<User> userDB = userRepository.findByDisplayNameOrEmail(user.getDisplayName(), user.getEmail());

        assertThat(userDB.isPresent()).isTrue();
        assertThat(userDB.get().getEmail()).isEqualTo(user.getEmail());
        assertThat(userDB.get().getDisplayName()).isEqualTo(user.getDisplayName());
        assertThat(userDB.get().getId()).isEqualTo(savedUser.getId());
    }

    @Test
    @DisplayName("Test for update user operation")
    void givenUserObject_whenUpdateUser_thenReturnUpdatedUser(){
        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).get();
        savedUser.setEmail("fp@devfep.com");
        savedUser.setFirstName("Felix");
        User updatedUser = userRepository.save(savedUser);

        assertThat(updatedUser.getEmail()).isEqualTo("fp@devfep.com");
        assertThat(updatedUser.getFirstName()).isEqualTo("Felix");
    }


    @Test
    @DisplayName("Test for delete user operation")
    void givenUserObject_whenDelete_thenDeleteUser(){
        userRepository.save(user);

        userRepository.delete(user);
        Optional<User> userOptional = userRepository.findById(user.getId());

        assertThat(userOptional).isEmpty();
    }

}
