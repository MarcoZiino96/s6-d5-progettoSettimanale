package it.epicode.s6d5.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DipendenteRequest {


    @NotNull(message = "nome obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String nome;


    @NotNull(message = "cognonome obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String cognome;


    @NotNull(message = "userName obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String userName;

    @Email(message = "Inserire email valida")
    @NotEmpty(message = "Campo Vuoto")
    private String email;
}
