package it.epicode.s6d5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    private String cognome;

    private String userName;

    private String fotoProfilo;

    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    List<Dispositivo> dispositivi;
}
