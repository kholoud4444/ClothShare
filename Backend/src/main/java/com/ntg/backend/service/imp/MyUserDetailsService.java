package com.ntg.backend.service.imp;

import com.ntg.backend.entity.User;
import com.ntg.backend.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    public MyUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(username);

        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}

