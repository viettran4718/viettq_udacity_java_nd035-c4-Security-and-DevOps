package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private String confirmPassword;

}
