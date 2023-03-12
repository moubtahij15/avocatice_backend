package com.example.userservice.mappers;//package com.example.userservice.mappers;
//

import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.entities.Agent;
import com.example.userservice.entities.Avocat;
import com.example.userservice.entities.Client;
import com.example.userservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserInputDtoToUser(UserInputDto userInputDto);

    UserOutPutDto UserToUserOutPutDto(User user);


    Avocat UserInputDtoToAvocat(UserInputDto userInputDto);

    UserOutPutDto AvocatToUserOutPutDto(Avocat avocat);

    Agent UserInputDtoToAgent(UserInputDto userInputDto);

    UserOutPutDto AgentToUserOutPutDto(Agent agent);

    Client UserInputDtoToClient(UserInputDto userInputDto);

    UserOutPutDto clientToUserOutPutDto(Client client);


}

