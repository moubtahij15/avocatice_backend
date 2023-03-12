package com.example.userservice.utils;

import com.example.userservice.dtos.UserInputDto;

public class UserUtils {
    public static boolean checkAvocatInputDtoFields(UserInputDto userInputDto) {
        return userInputDto.getNom() == null || userInputDto.getNom().isEmpty()
                || userInputDto.getCin() == null || userInputDto.getCin().isEmpty()
                || userInputDto.getGender() == null || userInputDto.getGender().isEmpty()
                || userInputDto.getEmail() == null || userInputDto.getEmail().isEmpty()
                || userInputDto.getPassword() == null || userInputDto.getPassword().isEmpty()
                || userInputDto.getPrénom() == null || userInputDto.getPrénom().isEmpty()
                || userInputDto.getVille() == null || userInputDto.getVille().isEmpty()
                || userInputDto.getSpécialité() == null || userInputDto.getSpécialité().isEmpty()
                || userInputDto.getImage_url() == null || userInputDto.getImage_url().isEmpty()
                ;
    }
    public static boolean checkUserInputDtoFields(UserInputDto userInputDto) {
        return userInputDto.getNom() == null || userInputDto.getNom().isEmpty()
                || userInputDto.getCin() == null || userInputDto.getCin().isEmpty()
                || userInputDto.getGender() == null || userInputDto.getGender().isEmpty()
                || userInputDto.getEmail() == null || userInputDto.getEmail().isEmpty()
                || userInputDto.getRole() == null || userInputDto.getRole().isEmpty()
                || userInputDto.getPassword() == null || userInputDto.getPassword().isEmpty()
                || userInputDto.getPrénom() == null || userInputDto.getPrénom().isEmpty()
                || userInputDto.getVille() == null || userInputDto.getVille().isEmpty()

                ;
    }
}
