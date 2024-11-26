package com.ntg.backend.controller;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")

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

        @GetMapping("/getAllByRole/{role}")
        public ResponseEntity<PageDto<UserResponseDetails>> getAllUsersByRole(@PathVariable("role") String role
                , @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo
                , @RequestParam (value = "pageSize",defaultValue = "5",required = false) int pageSize)
        {
            PageDto<UserResponseDetails> userResponsePagination = userServiceImp.getAllUsersWithRole(role,pageNo,pageSize);
            return new ResponseEntity<>(userResponsePagination, HttpStatus.OK);
        }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDetails> updateUser(@RequestBody UserResponseDetails userResponseDetails, @PathVariable("id") long id)
    {
        UserResponseDetails updatedVolunteerDetails = userServiceImp.UpdateUser(id, userResponseDetails);
        return new ResponseEntity<>(updatedVolunteerDetails,HttpStatus.OK);
    }

}
