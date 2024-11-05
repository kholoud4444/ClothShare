package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.UserMapper;
import com.ntg.backend.dto.RegistrationDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.User;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.repository.UserRepo;
import com.ntg.backend.repository.VolunteerRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp {
    private final UserRepo userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final VolunteerRepo volunteerRepository;
    private final UserMapper userMapper; // Repository for Volunteer


    private final NeedyRepo needyRepository;

    public UserServiceImp(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, VolunteerRepo volunteerRepository, UserMapper userMapper, NeedyRepo needyRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.volunteerRepository = volunteerRepository;
        this.userMapper = userMapper;
        this.needyRepository = needyRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(RegistrationDto registrationDto) {
        // Map DTO to the appropriate User subclass (Volunteer or Needy)
        User user = userMapper.toUser(registrationDto);

        // Encode the password before saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Save the user to the database and return it
        return userRepository.save(user);
    }


}
