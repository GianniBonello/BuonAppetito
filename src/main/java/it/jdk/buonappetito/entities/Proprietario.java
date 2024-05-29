package it.jdk.buonappetito.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Proprietario extends Utente {

    @OneToOne
    private Ristorante ristorante;
}
