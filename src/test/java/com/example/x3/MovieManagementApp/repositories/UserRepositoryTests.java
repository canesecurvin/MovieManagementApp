package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.User;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("JUnit test for save user operation")
    public void givenUserObject_whenSave_thenReturnSavedUser(){

        //given - data setup
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("jdoe@gmail.com")
                .displayName("jdoe")
                .password("1234")
                .build();


        //when - action
        User savedUser = userRepository.save(user);



        //then - assertion to verify
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).contains("@"); //change entity primary key to long
    }
}
