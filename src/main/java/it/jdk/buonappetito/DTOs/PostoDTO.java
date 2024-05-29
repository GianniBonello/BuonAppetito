package it.jdk.buonappetito.DTOs;

import it.jdk.buonappetito.entities.Ristorante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostoDTO {

    private Long id;

    private Long idRistorante;
}
