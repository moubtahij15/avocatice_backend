package com.example.userservice.web;

import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.services.AvocatServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AvocatRestController {
    AvocatServiceImpl avocatService;


    @GetMapping("/avocat-list")
    public List<UserOutPutDto> getAvocatList() {
        return avocatService.allUsers();
    }

    @PostMapping("/avocat")
    public UserOutPutDto addAvocat(@RequestBody UserInputDto userInputDto) throws UserCinExistException, MissingFieldException {
        return avocatService.createUser(userInputDto);
    }


    @GetMapping("/avocat/{id}")
    public UserOutPutDto getAvocat(@PathVariable String id) throws UserNotFoundException {
        return avocatService.singleUser(id);
    }

    @DeleteMapping("/avocat/{id}")
    public void deleteAvocat(@PathVariable String id) throws UserNotFoundException {
        avocatService.deleteUser(id);
    }


    @PutMapping("/avocat/{id}")
    public UserOutPutDto updateClient(@PathVariable String id, @RequestBody UserInputDto userInputDto) throws UserNotFoundException, MissingFieldException {
        return avocatService.updateUser(id, userInputDto);
    }
}
