package it.epicode.s6d5.model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DispositivoRequest {

    @NotNull(message = "cognonome obbligatorio")
    @NotEmpty(message = "campo vuoto")
    private Tipologia tipologia;

    @NotNull(message = "cognonome obbligatorio")
    @NotEmpty(message = "campo vuoto")
    private Disponibilita disponibilita;

    @NotNull(message = "autore obbligatorio")
    private Integer idDipendente;

}
