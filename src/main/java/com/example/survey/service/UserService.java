package com.example.survey.service;

import com.example.survey.domain.Role;
import com.example.survey.domain.Status;
import com.example.survey.domain.User;
import com.example.survey.dto.PromoUserDto;
import com.example.survey.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createNewUser(PromoUserDto user) {
        User domainUser = new User();
        domainUser.setEmail(user.getEmail());
        domainUser.setName(user.getName());
        domainUser.setPassword(generateRandomString());
        domainUser.setRole(Role.ROLE_USER);
        domainUser.setPhone("0");
        domainUser.setStatus(Status.ACTIVE);
        return userRepository.save(domainUser);
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public boolean isUserAllowedWithdraw(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new IllegalStateException();
        return user.getIin() != null;
    }
}
