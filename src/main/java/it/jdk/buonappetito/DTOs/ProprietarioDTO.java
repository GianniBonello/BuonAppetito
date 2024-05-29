package it.jdk.buonappetito.DTOs;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioDTO extends UtenteDTO{


    private Long idRistorante;

    public ProprietarioDTO(Long id, Long aLong) {
    }
}
