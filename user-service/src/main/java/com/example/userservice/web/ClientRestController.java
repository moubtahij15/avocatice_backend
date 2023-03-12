package com.example.userservice.web;

import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.services.AvocatServiceImpl;
import com.example.userservice.services.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class ClientRestController {
    ClientServiceImpl clientService;


    @GetMapping("/client-list")
    public List<UserOutPutDto> getClientList() {
        return clientService.allUsers();
    }

    @PostMapping("/client")
    public UserOutPutDto addClient(@RequestBody UserInputDto userInputDto) throws UserCinExistException, MissingFieldException {
        return clientService.createUser(userInputDto);
    }


    @GetMapping("/client/{id}")
    public UserOutPutDto getClient(@PathVariable String id) throws UserNotFoundException {
        return clientService.singleUser(id);
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable String id) throws UserNotFoundException {
        clientService.deleteUser(id);
    }


    @PutMapping("/client/{id}")
    public UserOutPutDto updateClient(@PathVariable String id, @RequestBody UserInputDto userInputDto) throws UserNotFoundException, MissingFieldException {
        return clientService.updateUser(id, userInputDto);
    }
}
