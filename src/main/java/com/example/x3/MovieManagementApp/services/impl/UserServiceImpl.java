package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.config.JwtConfig.JwtProvider;
import com.example.x3.MovieManagementApp.dtos.UserDtos.*;
import com.example.x3.MovieManagementApp.entities.User;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;



    @Override
    public ResponseEntity<?> createNewUser(UserSignUpDto userSignUpDto) {
        if (userRepository.existsByDisplayName(userSignUpDto.getDisplayName())) {
            return new ResponseEntity<>("That display name is taken. Try another.", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(userSignUpDto.getEmail())) {
            return new ResponseEntity<>("There's an account with us with this email address. Sign in instead", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(userSignUpDto.getFirstName());
        user.setLastName(userSignUpDto.getLastName());
        user.setDisplayName(userSignUpDto.getDisplayName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> loginUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (userRepository.existsByEmail(userLoginDto.getEmail())) {
            Long userId = userRepository.findByEmail(userLoginDto.getEmail()).get().getId();
            String displayName = userRepository.findById(userId).get().getDisplayName();
            String email = userRepository.findById(userId).get().getEmail();
            String firstName = userRepository.findById(userId).get().getFirstName();
            String lastName = userRepository.findById(userId).get().getLastName();
            String generatedToken = jwtProvider.generateToken(authentication);

            UserRestDto userRest = UserRestDto.builder()
                    .id(userId)
                    .displayName(displayName)
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .accessToken(generatedToken)
                    .tokenType("Bearer")
                    .expiresIn(86400)
                    .build();

            return new ResponseEntity<>(userRest, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> updateBasicInfo(UserBasicUpdateRequestDto userBasicUpdateRequestDto, Long id) {

        if (id != null && userRepository.existsById(id)){

            Long requestUserId = id;
            String requestUserEmail = userBasicUpdateRequestDto.getEmail();
            String requestUserDisplayName = userBasicUpdateRequestDto.getDisplayName();
            String requestUserFirstName = userBasicUpdateRequestDto.getFirstName();
            String requestUserLastName = userBasicUpdateRequestDto.getLastName();

            Optional<User> userDB = userRepository.findById(requestUserId);
            User updatedUser = new User();
            updatedUser.setId(id);
            updatedUser.setPassword(userDB.get().getPassword());

            if(userRepository.existsByEmail(requestUserEmail) && userRepository.findByEmail(requestUserEmail).get().getId() != id){
                throw new IllegalArgumentException("This email address already has an account with us!");
            } else {
                updatedUser.setEmail(requestUserEmail);
            }

            if(userRepository.existsByDisplayName(requestUserDisplayName) && userRepository.findByDisplayName(requestUserDisplayName).get().getId() != id){
                throw new IllegalArgumentException("This display name is already taken");
            } else {
                updatedUser.setDisplayName(requestUserDisplayName);
            }

            updatedUser.setFirstName(requestUserFirstName);
            updatedUser.setLastName(requestUserLastName);

            userRepository.save(updatedUser);

            UserUpdateResponseDto userUpdateResponseDto = UserUpdateResponseDto.builder()
                                                        .displayName(updatedUser.getDisplayName())
                                                        .email(updatedUser.getEmail())
                                                        .firstName(updatedUser.getFirstName())
                                                        .lastName(updatedUser.getLastName())
                                                        .updateMessage("User details successfully updated")
                                                        .build();

            return new ResponseEntity<>(userUpdateResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> updatePassword(UserPasswordRequestDto userPasswordRequestDto, Long id) {
        if (id != null && userRepository.existsById(id)){
            Optional<User> userDB = userRepository.findById(id);
            User updatedUser = new User();

            if(passwordEncoder.matches(userPasswordRequestDto.getOldPassword(), userDB.get().getPassword())){
                updatedUser.setId(id);
                updatedUser.setEmail(userDB.get().getEmail());
                updatedUser.setDisplayName(userDB.get().getDisplayName());
                updatedUser.setPassword(passwordEncoder.encode(userPasswordRequestDto.getNewPassword()));
                updatedUser.setFirstName(userDB.get().getFirstName());
                updatedUser.setLastName(userDB.get().getLastName());

                userRepository.save(updatedUser);
            }
            return new ResponseEntity<>("User password has been updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
