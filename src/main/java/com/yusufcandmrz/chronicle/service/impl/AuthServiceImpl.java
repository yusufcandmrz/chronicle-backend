package com.yusufcandmrz.chronicle.service.impl;

import com.yusufcandmrz.chronicle.dto.LoginRequest;
import com.yusufcandmrz.chronicle.dto.LoginResponse;
import com.yusufcandmrz.chronicle.dto.RegisterRequest;
import com.yusufcandmrz.chronicle.entity.AppUser;
import com.yusufcandmrz.chronicle.entity.Role;
import com.yusufcandmrz.chronicle.exception.DuplicateResourceException;
import com.yusufcandmrz.chronicle.notification.event.UserRegisteredEvent;
import com.yusufcandmrz.chronicle.notification.producer.UserRegisteredEventProducer;
import com.yusufcandmrz.chronicle.repository.AppUserRepository;
import com.yusufcandmrz.chronicle.security.JwtService;
import com.yusufcandmrz.chronicle.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRegisteredEventProducer userRegisteredEventProducer;

    @Override
    public void register(RegisterRequest request) {

        String username = request.getUsername();
        String email = request.getEmail();

        if (appUserRepository.existsByUsername(username)) {
            throw new DuplicateResourceException("Username is already in use.");
        }

        if (appUserRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Email is already in use.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        AppUser appUser = new AppUser(username, email, encodedPassword, Role.USER);

        appUserRepository.save(appUser);

        UserRegisteredEvent event = new UserRegisteredEvent(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail()
        );

        userRegisteredEventProducer.publish(event);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken
                        .unauthenticated(
                                request.getEmail(),
                                request.getPassword()
                        ));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);

        return new LoginResponse(accessToken);
    }
}
