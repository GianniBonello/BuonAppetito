package it.jdk.buonappetito.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDTO{

    private Long id;

    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private Long idComune;
    private String indirizzo;

    private String email;
    private String password;
}
