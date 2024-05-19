package com.tallyto.gestur.service.security;


import com.tallyto.gestur.dto.LoginRequestDTO;
import com.tallyto.gestur.dto.RegisterRequestDTO;
import com.tallyto.gestur.dto.TokenDTO;
import com.tallyto.gestur.enums.RoleEnum;
import com.tallyto.gestur.exception.BadRequestException;
import com.tallyto.gestur.exception.ResourceNotFoundException;
import com.tallyto.gestur.model.Role;
import com.tallyto.gestur.model.User;
import com.tallyto.gestur.repository.RoleRepository;
import com.tallyto.gestur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    public String register(RegisterRequestDTO dto) {
        User existentUser = userRepository.findByEmail(dto.email());
        if (existentUser != null)
            throw new BadRequestException("This email is already taken");

        Role role = roleRepository.findByDescription(RoleEnum.USER);

        User user = User.builder()
                .email(dto.email())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .roles(Set.of(role))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        userRepository.save(user);
        return "User successfully registered";
    }

    public TokenDTO login(LoginRequestDTO dto) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = userRepository.findByEmail(dto.email());

        if (user == null)
            throw new ResourceNotFoundException("Email/password incorrect or non-existent");

        return jwtTokenService.createAccessToken(user.getEmail(), user.getRoles());
    }
}
