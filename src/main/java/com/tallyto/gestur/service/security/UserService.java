package com.tallyto.gestur.service.security;


import com.tallyto.gestur.model.User;
import com.tallyto.gestur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found or non-existent");
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getIsEnabled(),
                user.getIsAccountNonExpired(),
                user.getIsCredentialsNonExpired(),
                user.getIsAccountNonLocked(),
                user.getAuthorities()
        );
    }

    private boolean isCurrentUser(String token, User user) {
        String formattedToken = jwtTokenService.formatToken(token);
        String email = jwtTokenService.decodedJWT(formattedToken).getSubject();
        return !email.equals(user.getEmail());
    }
}
