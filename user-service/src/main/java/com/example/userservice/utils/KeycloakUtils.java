package com.example.userservice.utils;

import com.example.userservice.dtos.UserInputDto;
import lombok.ToString;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class KeycloakUtils {

    @Value("${serverUrl}")
    String serverUrl;
    @Value("${realm}")
    String realm;
    @Value("${clientId}")
    String clientId;
    //    final String clientSecret ;
    @Value("${username_kc}")
    String userName;
    @Value("${password_kc}")
    String password;

    public Keycloak getAdminKeycloakUser() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType("password")
                .realm(realm)
                .clientId(clientId)
//              .clientSecret("38NhHZHruWl3Cp4jRYGz3qyCAEuQPFC8")
                .username(userName)
                .password(password)
                .build();
//                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

    public RealmResource getRealm() {
        return getAdminKeycloakUser().realm(realm);
    }

    public void setCredentials(String userId, String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(password);
        UserResource userResource = getRealm().users().get(userId);
        userResource.resetPassword(credentialRepresentation);
    }

    private void addUserRole(String userId, String roleUser) {
        RoleRepresentation role = getRealm().roles().get(roleUser).toRepresentation();
        UserResource userResource = getRealm().users().get(userId);
        userResource.roles().realmLevel().add(List.of(role));
    }

    public void deleteUser(String userId) {
        UserResource userResource = getRealm().users().get(userId);
        userResource.remove();

    }

    public void sendMailVerification(String userId) {
        UserResource userResource = getRealm().users().get(userId);
        userResource.sendVerifyEmail();

    }

    public void sendResetPasword(String userId) {
        UserResource userResource = getRealm().users().get(userId);
        userResource.executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public String createKeycloakUserWithRole(UserInputDto userInputDto) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setLastName(userInputDto.getNom());
        userRepresentation.setFirstName(userInputDto.getPrénom());
        userRepresentation.setUsername(userInputDto.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userInputDto.getEmail());

        try {
            Response response = getRealm().users().create(userRepresentation);
            String userId = CreatedResponseUtil.getCreatedId(response);
            System.out.println("idUser: " + userId);
            this.setCredentials(userId, userInputDto.getPassword());
            System.out.println("from create kc : " + userInputDto.getRole());
            addUserRole(userId, userInputDto.getRole());
            return userId;
        } catch (Exception e) {
            System.out.println(e);
            return null;

        }

    }

    public String updateKeycloakUser(String userId, UserInputDto userInputDto) {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setLastName(userInputDto.getNom());
        userRepresentation.setFirstName(userInputDto.getPrénom());
        userRepresentation.setUsername(userInputDto.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userInputDto.getEmail());
        this.setCredentials(userId, userInputDto.getPassword());

        try {
            UserResource userResource = getRealm().users().get(userId);
            userResource.update(userRepresentation);
            return userId;
        } catch (Exception e) {
            System.out.println(e);
            return null;

        }

    }


}
