package com.example.userservice.web;

import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.services.AgentServiceImpl;
import com.example.userservice.services.AvocatServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AgentRestController {
    AgentServiceImpl agentService;


    @GetMapping("/agent-list")
    public List<UserOutPutDto> getAgentList() {
        return agentService.allUsers();
    }

    @PostMapping("/agent")
    public UserOutPutDto addAgent(@RequestBody UserInputDto userInputDto) throws UserCinExistException, MissingFieldException {
        return agentService.createUser(userInputDto);
    }


    @GetMapping("/agent/{id}")
    public UserOutPutDto getAgent(@PathVariable String id) throws UserNotFoundException {
        return agentService.singleUser(id);
    }

    @DeleteMapping("/agent/{id}")
    public void deleteAgent(@PathVariable String id) throws UserNotFoundException {
        agentService.deleteUser(id);
    }


    @PutMapping("/agent/{id}")
    public UserOutPutDto updateAgent(@PathVariable String id, @RequestBody UserInputDto userInputDto) throws UserNotFoundException, MissingFieldException {
        return agentService.updateUser(id, userInputDto);
    }
}
