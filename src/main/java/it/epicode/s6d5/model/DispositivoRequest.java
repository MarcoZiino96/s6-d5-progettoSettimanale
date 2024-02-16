package it.epicode.s6d5.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DispositivoRequest {

    @NotNull(message = "cognonome obbligatorio")
    private Tipologia tipologia;

    @NotNull(message = "cognonome obbligatorio")
    private Disponibilita disponibilita;


    private Integer idDipendente;

    @NotNull(message = "marca obbligatoria")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String marca;

}
