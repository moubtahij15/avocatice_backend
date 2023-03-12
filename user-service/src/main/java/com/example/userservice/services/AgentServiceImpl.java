package com.example.userservice.services;


import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.entities.Agent;
import com.example.userservice.enums.role;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.repositories.AgentRepository;
import com.example.userservice.utils.KeycloakUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.userservice.utils.UserUtils.checkAvocatInputDtoFields;


@Service
@Transactional
@AllArgsConstructor
//@NoArgsConstructor
public class AgentServiceImpl implements userService {
    AgentRepository agentRepository;
    UserMapper userMapper;
    KeycloakUtils keycloakUtils;

    @Override
    public List<UserOutPutDto> allUsers() {
        return agentRepository.findAll()
                .stream()
                .map(userMapper::AgentToUserOutPutDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutPutDto singleUser(String id) throws UserNotFoundException {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.AgentToUserOutPutDto(agent);
    }

    @Override
    public UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException {

        if (checkAvocatInputDtoFields(userInputDto)) throw new MissingFieldException("Missing Field");
        System.out.println("user Input" + userInputDto);
        userInputDto.setRole(role.agent_app.toString());
        Agent agent = this.userMapper.UserInputDtoToAgent(userInputDto);
        agent.setId(keycloakUtils.createKeycloakUserWithRole(userInputDto));
        UserOutPutDto userOutPutDto = userMapper.UserToUserOutPutDto(agentRepository.save(agent));
        System.out.println("user mapper" + agent);
        return userOutPutDto;
//        return null;
    }

    @Override
    public void deleteUser(String id) throws UserNotFoundException {
        Agent client = agentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        keycloakUtils.deleteUser(id);
        keycloakUtils.deleteUser(id);
        agentRepository.delete(client);
    }

    @Override
    public UserOutPutDto updateUser(String id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException {
        if (checkAvocatInputDtoFields(userInputDto))
            throw new MissingFieldException("Missing Field");
        agentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Agent agent = userMapper.UserInputDtoToAgent(userInputDto);
        agent.setId(id);
        keycloakUtils.updateKeycloakUser(id, userInputDto);
        return userMapper.AgentToUserOutPutDto(agentRepository.save(agent));
    }
}
