package com.ntg.backend.controller;
import com.ntg.backend.dto.requestDto.VolunteerDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.repository.UserRepo;
import com.ntg.backend.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<UserResponseDetails> getUserDetailsById(@PathVariable("id") long id )
    {
        UserResponseDetails userResponseDetails = userServiceImp.getUserDetails(id);
        return new ResponseEntity<>(userResponseDetails, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id)
    {
        userServiceImp.deleteUserById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @GetMapping("/allUsers/{role}")
    public ResponseEntity<List<UserResponseDetails>> getAllUsersByRole(@PathVariable("role") String role )
    {
        List<UserResponseDetails> userResponseDetails = userServiceImp.getAllUsersWithRole(role);
        return new ResponseEntity<>(userResponseDetails, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDetails> updateUser(@RequestBody UserResponseDetails userResponseDetails, @PathVariable("id") long id)
    {
        UserResponseDetails updatedVolunteerDetails = userServiceImp.UpdateUser(id, userResponseDetails);
        return new ResponseEntity<>(updatedVolunteerDetails,HttpStatus.OK);
    }

}
