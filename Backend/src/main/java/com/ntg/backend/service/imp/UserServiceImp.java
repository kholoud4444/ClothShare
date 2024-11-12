package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.UserMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.RegistrationDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.entity.User;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.UserRepo;
import com.ntg.backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper; // Repository for Volunteer
    private final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,UserMapper userMapper, UserRepo userRepo) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDetails getUserDetails(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("request", "id", userId));
        UserResponseDetails userResponseDetails = new UserResponseDetails();
        userMapper.mapToUserDto(user, userResponseDetails);
        return userResponseDetails;
//        return userMapper.toUserResponseDto(user);
    }

    @Override
    public void deleteUserById(long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(userId);

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

    public PageDto<UserResponseDetails> getAllUsersWithRole(String role, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<User> users = userRepository.findByRole(role, pageable);
        PageDto<UserResponseDetails> userPageDto = userMapper.userPageToDto(users);
        return userPageDto;



    }

    @Override
    public UserResponseDetails UpdateUser(long userId, UserResponseDetails userResponseDetails) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userMapper.updateUserDtoToEntity(userResponseDetails, user);

        UserResponseDetails savedUserResponseDetails = new UserResponseDetails();
        userMapper.mapToUserDto(user, savedUserResponseDetails);
        return savedUserResponseDetails;
    }
}




