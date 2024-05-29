package it.jdk.buonappetito.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RistoranteDTO {

    private Long id;

    private String nome;
    private String indirizzo;

    private Long idComune;

    private Long idProprietario;
    private List<Long> idPosti;

    private LocalTime orarioApertura;

    private LocalTime orarioChiusura;
}
