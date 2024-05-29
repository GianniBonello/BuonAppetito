package it.jdk.buonappetito.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PietanzaDTO {

    private Long id;

    private String nome;

    private Double prezzo;
}
