package kz.singularity.solidbankapp1.security.service;

import kz.singularity.solidbankapp1.security.model.Role;
import kz.singularity.solidbankapp1.security.model.User;
import kz.singularity.solidbankapp1.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("username exists already");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    public UserDetailsService userDetailsService(){
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return getByUsername(username);
    }

    public boolean isAuthorized(String ownerId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return String.valueOf(user.getId()).equals(ownerId);
    }

}