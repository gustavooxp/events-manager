package com.senai.eventsmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthDTO {

    @NotBlank(message = "O email deve ser preenchido")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "A senha deve ser preenchida")
    private String senha;
}
