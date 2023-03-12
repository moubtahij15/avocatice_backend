package com.example.userservice.services;

import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;

import java.util.List;

public interface userService {
    List<UserOutPutDto> allUsers(); //Get All

    UserOutPutDto singleUser(String id) throws UserNotFoundException; //Get Elem By Id

    UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException; //Create New Elem

    void deleteUser(String id) throws UserNotFoundException; //Delete Elem By Id

    UserOutPutDto updateUser(String id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException; //Update Elem
    //Search Elems By Keyword
}
