package com.example.userservice.services;


import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.entities.Client;
import com.example.userservice.enums.role;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.repositories.ClientRepository;
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
public class ClientServiceImpl implements userService {
    ClientRepository clientRepository;
    UserMapper userMapper;
    KeycloakUtils keycloakUtils;

    @Override
    public List<UserOutPutDto> allUsers() {
        return clientRepository.findAll()
                .stream()
                .map(userMapper::clientToUserOutPutDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutPutDto singleUser(String id) throws UserNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.clientToUserOutPutDto(client);
    }

    @Override
    public UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException {

        if (checkAvocatInputDtoFields(userInputDto)) throw new MissingFieldException("Missing Field");
        System.out.println("user Input" + userInputDto);
        userInputDto.setRole(role.user_app.toString());
        Client client = this.userMapper.UserInputDtoToClient(userInputDto);
        client.setId(keycloakUtils.createKeycloakUserWithRole(userInputDto));

        UserOutPutDto userOutPutDto = userMapper.UserToUserOutPutDto(clientRepository.save(client));
        return userOutPutDto;
    }

    @Override
    public void deleteUser(String id) throws UserNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        keycloakUtils.deleteUser(id);

        clientRepository.delete(client);
    }

    @Override
    public UserOutPutDto updateUser(String id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException {
        if (checkAvocatInputDtoFields(userInputDto))
            throw new MissingFieldException("Missing Field");
        clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Client client = userMapper.UserInputDtoToClient(userInputDto);
        client.setId(id);
        keycloakUtils.updateKeycloakUser(id, userInputDto);
        return userMapper.clientToUserOutPutDto(clientRepository.save(client));
    }
}
