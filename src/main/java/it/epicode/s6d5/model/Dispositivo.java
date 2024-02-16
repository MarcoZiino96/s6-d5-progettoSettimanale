package it.epicode.s6d5.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    @Enumerated(EnumType.STRING)
    private Disponibilita disponibilita;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dipendente dipendente;

}
